/**
 * Created by kevin on 05/02/2017.
 */
(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .factory('Escuelas', ['$resource', 'API_URL', function($resource, API_URL) {
            return $resource(API_URL+'escuelas/:id', {}, {
                all    : {method: 'GET', isArray: true}
            });
        }]);

})();