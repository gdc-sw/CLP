(function() {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .controller('ContainerController', ContainerController);

    /** @ngInject */
    function ContainerController(API_URL, $http, $log, toastr, $stateParams, $scope) {
        var vm = this;

    }
})();
