/**
 * Created by kevin on 05/02/2017.
 */
(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .factory('Profesores', ['$resource', 'API_URL', function($resource, API_URL) {
            return $resource(API_URL+'profesores/:codigo', {}, {
                find : {method: 'GET', isArray: true}
            });
        }]);

})();
