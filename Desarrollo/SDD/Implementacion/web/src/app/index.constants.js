/* global malarkey:false, moment:false */
(function() {
  'use strict';

  angular
    .module('sisdisdocCliente')
    .constant('malarkey', malarkey)
    .constant('moment', moment)
    .constant('API_URL', 'http://192.168.1.39:8080/sisdisdoc/api/');

})();
