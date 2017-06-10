/**
 * Created by kevin on 05/02/2017.
 */
(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .controller('DisponiblidadHorariaController', DisponiblidadHorariaController);

    /** @ngInject */
    function DisponiblidadHorariaController(API_URL, $http, $log, toastr, $scope, $state, intervalos) {
        var vm = this;
        vm.cursosDisponibles = [];
        vm.disponibilidades = [];
        vm.horas = 0;
        vm.imprimirDisponibilidad = imprimirDisponibilidad;
        vm.intervalos = intervalos;
        vm.usuario = JSON.parse(localStorage.getItem("usuario"));

        $http.get(API_URL+'profesores/'+vm.usuario.codigo).then(successGetProfesor, errorGetProfesor);

        $http.get(API_URL+'disponibilidades/'+vm.usuario.idprofesor).then(successGetDisponibilidad, errorGetDisponibilidad);

        $http.get(API_URL+'disponibilidades/cursos/'+vm.usuario.idprofesor).then(successGetCursosDisponibles, errorGetCursosDisponibles);

        function errorGetCursosDisponibles(err){
            $log.log(err);
            $log.log(err.statusText);
        }

        function errorGetDisponibilidad(err){
            $log.log(err.statusText);
        }

        function errorGetProfesor(err){
            toastr.error(err.statusText);
        }


        function imprimirDisponibilidad(data){

            var divToPrint=document.getElementById(data);
            var htmlToPrint = '' +
                '<style type="text/css">' +
                '#table th, table td {' +
                'border:1px solid #000;' +
                'padding;0.5em;' +
                '}' +
                '.code-img{'+
                'width: 50px;}'+
                '</style>';
            htmlToPrint += divToPrint.outerHTML;
            var newWin= window.open("");
            newWin.document.write(divToPrint.outerHTML);
            newWin.print();
            newWin.close();
        }

        function successGetCursosDisponibles(res){
            vm.cursosDisponibles = res.data;
        }

        function successGetDisponibilidad(res){
            vm.disponibilidades = res.data;
            angular.forEach(vm.disponibilidad, function(intervalo, dia){
                vm.disponibilidades.forEach(function(disponibilidad,disponibilidad_key){
                    if(dia == disponibilidad.dia ){
                        angular.forEach(intervalo, function(v,k){
                            if(disponibilidad.idintervalo_hora == k){
                                vm.disponibilidad[dia][k] = true;
                            }
                        });
                    }
                });
            });

        }


        function successGetProfesor(res){
            vm.profesor = res.data;
        }

    }
})();
