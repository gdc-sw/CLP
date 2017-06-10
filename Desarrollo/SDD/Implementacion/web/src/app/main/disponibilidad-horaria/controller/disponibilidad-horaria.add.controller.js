(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .controller('DisponiblidadHorariaAddController', DisponiblidadHorariaAddController);

    /** @ngInject */
    function DisponiblidadHorariaAddController(API_URL, $http, $log, toastr, $scope, $state) {
        var vm = this;
        vm.disponibilidades = [];
        vm.horas = 0;
        vm.submit = submit;
        vm.usuario = JSON.parse(localStorage.getItem("usuario"));

        $http.get(API_URL+'profesores/'+vm.usuario.codigo).then(successGetProfesor, errorGetProfesor);
        $http.get(API_URL+'intervalo-horas').then(successGetHorarios, errorGetHorarios);

        $scope.$watch('ctrl.disponibilidad',watchDisponibilidad, true);

        function errorGetProfesor(err){
            toastr.error(err.statusText);
        }

        function errorGetHorarios(err){
            $log.log(err.statusText);
        }


        function errorPostDisponibilidades(res){
            toastr.error(res.data.error);
        }

        function submit(){
            $http.post(API_URL+'disponibilidades/'+vm.profesor.idprofesor, vm.disponibilidades).then(successPostDisponibilidades,
                errorPostDisponibilidades);
        }

        function successGetHorarios(res){
            vm.intervalos = res.data;
            $log.log(res);
        }

        function successGetProfesor(res){
            vm.profesor = res.data;
        }

        function successPostDisponibilidades(res){

            vm.usuario.registroDisponibilidadHoraria = true;
            localStorage.setItem("usuario", JSON.stringify(vm.usuario));
            toastr.success("Se guardó disponibilidad");
            $state.go('index.panel-principal');

        }

        /**
         * Esta funcion actualiza el contador cada vez que se marca un checkbox
         * @param newv
         * @param oldv
         */
        function watchDisponibilidad(newv, oldv){
            vm.horas = 0;
            vm.disponibilidades = [];
            angular.forEach(vm.disponibilidad, function(value, key){
                angular.forEach(value, function(v, k){
                    if(v){
                        vm.disponibilidades.push({
                            idprofesor: vm.profesor.idprofesor,
                            idintervalo_hora: k,
                            idciclo: 1,
                            dia: key
                        });
                        vm.horas++;
                    }
                });
            });
        }

  }
})();
