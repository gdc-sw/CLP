(function() {
  'use strict';

  angular
    .module('sisdisdocCliente')
    .config(config);

  /** @ngInject */
  function config($logProvider, toastrConfig, $httpProvider) {
    // Enable log
    $logProvider.debugEnabled(true);

    // Set options third-party lib
    toastrConfig.allowHtml = true;
    toastrConfig.timeOut = 3000;
    toastrConfig.positionClass = 'toast-top-right';
    toastrConfig.preventDuplicates = false;
    toastrConfig.progressBar = false;

      //interceptor

      //$httpProvider.interceptors.push('httpRequestInterceptor');
  }

})();
