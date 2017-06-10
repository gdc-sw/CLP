(function() {
  'use strict';

  angular
    .module('sisdisdocCliente')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController(API_URL, $http, $log, toastr, $state) {
    var vm = this;    
    vm.submit = submit;
    vm.user = {username:'',password:''};

    //$http.get(API_URL+'modalidades').then(success, error);

    function success(res){
        var editoPerfil = Boolean(res.data.editoPerfil),
            registroDisponibilidadHoraria = Boolean(res.data.registroDisponibilidadHoraria),
            registroCursos = Boolean(res.data.registroCursos),
            profesor = res.data;
        profesor.isLoggedIn = true;
        localStorage.setItem("usuario",JSON.stringify(profesor));
        toastr.success("Bienvenido");
        $state.go("index.panel-principal");
    }

    function error(err){
        console.log(err.data.error);
        toastr.error(err.data.error);
        $log.log(err);
    }

    function submit(){
        $http.post(API_URL+'authentication', vm.user).then(success, error);
    }
  }
})();
