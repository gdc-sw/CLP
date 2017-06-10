/**
 * Created by kevin on 16/02/2017.
 */

(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .factory('httpRequestInterceptor', [function($q) {
            return {
                'responseError': function(rejection) {
                    // do something on error
                    //toastr("Hubo un error de conexion con el servidor, intentelo de nuevo", 'Error de conexión');
                    alert("Hubo un error de conexion con el servidor, intentelo de nuevo");
                    return $q.reject(rejection);
                }
            }
        }]);

})();

/*
.factory('httpRequestInterceptor', function ($q, $location) {
    return {
        'responseError': function(rejection) {
            // do something on error
            if(rejection.status === 404){
                $location.path('/404/');
            }
            return $q.reject(rejection);
        }
    };
});*/
