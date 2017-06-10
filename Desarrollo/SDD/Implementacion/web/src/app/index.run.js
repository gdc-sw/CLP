(function() {
  'use strict';

  angular
    .module('sisdisdocCliente')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, $rootScope, $state, toastr) {

        $log.debug('runBlock end');

        $rootScope.$on('$stateChangeStart',stateChangeStart);

        function stateChangeStart(e, toState, toParams, fromState, fromParams){
            var profesor = JSON.parse(localStorage.getItem('usuario'));
            if(profesor !== null && toState.name !=='login') {
                var editoPerfil = Boolean(profesor.editoPerfil),
                    registroCursos = Boolean(profesor.registroCursos),
                    registroDisponibilidadHoraria = Boolean(profesor.registroDisponibilidadHoraria);

                if (toState.module === "private" && !profesor.isLoggedIn) {
                    e.preventDefault();
                    toastr.error("Debe iniciar sesión");
                    $state.go('login');
                } else if (toState.module === "private" && profesor.isLoggedIn) {
                    if (toState.name === "index.editar-profesor") {
                        if (editoPerfil) {
                            toastr.warning("Ya editó perfil");
                            e.preventDefault();
                        }
                    } else if (toState.name === "index.disponibilidad-cursos") {
                        if (registroCursos) {
                            toastr.warning("Ya registró cursos");
                            e.preventDefault();
                        }
                    } else if (toState.name === "index.disponibilidad-horaria") {
                        if (registroDisponibilidadHoraria) {
                            toastr.warning("Ya registró disponibilidad");
                            e.preventDefault();
                        }
                    } else if (toState.name === "index.disponibilidad-cursos") {
                        if (registroCursos) {
                            toastr.warning("Ya registró cursos");
                            e.preventDefault();
                        }
                    }
                }
            } else if(profesor !== null && toState.name ==='login'){
                if(profesor.isLoggedIn){
                    e.preventDefault();
                    $state.go('index.panel-principal');
                }

            } else if(profesor ===null && toState.name !=='login'){
                e.preventDefault();
                toastr.error("Debe iniciar sesión");
                $state.go('login');
            }
        }

        function stateException(){
            this.message = "error en el flujo";
        }
  }

})();
