(function () {
    'use strict';

    angular
        .module('sisdisdocCliente')
        .directive('logout', Logout);

    Logout.$inject = [ '$state'];

    /* @ngInject */
    function Logout( $state) {

        var directive = {
            link: link,
            restrict: 'A',
            scope: {}
        };
        return directive;

        function link($scope, element, attrs) {
            element.bind('click', click);

            function click(e) {
                e.preventDefault();
                localStorage.clear();
                $state.go('login');
            }
        }
    }

})();
