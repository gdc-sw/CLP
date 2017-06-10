(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .controller('DisponiblidadCursosController', DisponiblidadCursosController);

    /** @ngInject */
    function DisponiblidadCursosController(API_URL, escuelas, $http, $log, toastr, $state, $scope) {
        var vm = this;
        vm.agregar = agregar;
        vm.cursoSelected = null;
        vm.cursos = [];
        vm.ciclo = {idciclo:2 };
        vm.disponibilidad = {
            escuela: null,
            cursos: []
        };
        vm.escuelas = escuelas;
        vm.remover = remover;
        vm.submit = submit;
        vm.usuario = JSON.parse(localStorage.getItem("usuario"));

        $http.get(API_URL+'profesores/'+vm.usuario.codigo).then(function(res){
            vm.profesor = res.data;
        }, error);

        $scope.$watch('ctrl.disponibilidad.escuela', watchEscuela, true);


        function agregar(){
            var indexof = vm.disponibilidad.cursos.indexOf(vm.cursoSelected);
            if(vm.cursoSelected !== null ) {
                if(vm.disponibilidad.cursos.length>0){
                    if(indexof == -1){
                        vm.disponibilidad.cursos.push(vm.cursoSelected);
                    }
                } else {
                    vm.disponibilidad.cursos.push(vm.cursoSelected);
                }
            }
        }
        function remover(index){
            vm.disponibilidad.cursos.splice(index,1);
        }

        function error(err){
            toastr.error(err.data.error, err.statusText);
            $log.log(err.data.error);
        }

        function submit(){
            var cursosDisponibles=[];
            vm.disponibilidad.cursos.forEach(function(curso, key){
                //console.log(curso);
                cursosDisponibles.push({
                    idprofesor: vm.profesor.idprofesor,
                    idcurso: curso.idcurso,
                    idciclo: vm.ciclo.idciclo
                });
            });
            //$log.log(cursosDisponibles);
            $http.post(API_URL+'disponibilidades/cursos/'+vm.profesor.idprofesor, cursosDisponibles).then(success, error);
        }

        function success(res){
            vm.usuario.registroCursos = true;
            localStorage.setItem("usuario", JSON.stringify(vm.usuario));
            toastr.success("Se guardó disponibilidad con éxito", res.statusText);
            $state.go("index.panel-principal");
        }

        function watchEscuela(newEscuela, oldEscuela){
            if(newEscuela !== null){
                vm.cursos = newEscuela.cursos;
            }
        }
    }
})();
