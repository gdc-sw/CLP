(function() {
  'use strict';

    angular
        .module('sisdisdocCliente')
        .controller('ProfesorEditController', ProfesorEditController);

    /** @ngInject */
    function ProfesorEditController(API_URL, $http, $log, toastr, $state) {
        var vm = this;
        vm.submit = submit;
        vm.usuario = JSON.parse(localStorage.getItem("usuario"));

        $http.get(API_URL+'profesores/'+vm.usuario.codigo).then(function(res){
            vm.profesor = res.data;
        }, error);

        function error(err){
            toastr.error(err.statusText);
        }

        function submit(){
            if(validateEmail(vm.profesor.correo)){
                if(validatTelefono(vm.profesor.telefono)) {
                    $http.put(API_URL + "profesores/" + vm.profesor.codigo, vm.profesor).then(success, error);
                }else
                    toastr.error("Ingrese un numero de telefono valido");
            } else
                toastr.error("Ingrese un correo valido");
        }
        function success(res){
            vm.usuario.editoPerfil = true;
            localStorage.setItem("usuario", JSON.stringify(vm.usuario));
            toastr.success("Se guardaron sus datos personales");
            $state.go('index.panel-principal');
        }

        function validateEmail(email) {
            console.log(email);
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        }

        function validatTelefono(phone){
            var regex = /^[0-9\-\+]{9,15}$/;
            return regex.test(phone);
        }


    }
})();
