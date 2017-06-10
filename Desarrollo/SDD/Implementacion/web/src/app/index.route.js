(function() {
  'use strict';

  angular
    .module('sisdisdocCliente')
    .config(routerConfig);

  /** @ngInject */
  function routerConfig($stateProvider, $urlRouterProvider) {
    /*
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'main'
      })
      */
        $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'app/main/login/view/login.html',
            controller: 'LoginController as ctrl',
            module:'public'
        })

        .state('index', {
            abstract: true,
            url: '',
            templateUrl: 'app/main/container/view/container.html',
            controller: 'ContainerController as ctrl'
        }).state('index.editar-profesor', {
            url: '/editar-profesor',
            templateUrl: 'app/main/profesor/view/profesor.edit.html',
            controller: 'ProfesorEditController as ctrl',
            module: 'private'
        })
        .state('index.disponibilidad-horaria', {
            url: '/nueva-disponibilidad-horaria',
            templateUrl: 'app/main/disponibilidad-horaria/view/disponibilidad-horaria.add.html',
            controller: 'DisponiblidadHorariaAddController as ctrl',
            module: 'private'
        })
        .state('index.ver-disponibilidad-horaria', {
            url: '/disponibilidad-horaria',
            templateUrl: 'app/main/disponibilidad-horaria/view/disponibilidad-horaria.html',
            controller: 'DisponiblidadHorariaController as ctrl',
            module: 'private',
            resolve: {
                intervalos : ['API_URL', '$http', function (API_URL, $http) {
                    return $http.get(API_URL+'intervalo-horas').then(function(res){
                        return res.data;
                    });
                }]
            }
        })
        .state('index.disponibilidad-cursos', {
            url: '/nueva-disponibilidad-cursos',
            templateUrl: 'app/main/disponibilidad-cursos/view/disponibilidad-cursos.html',
            controller: 'DisponiblidadCursosController as ctrl',
            module: 'private',
            resolve: {
                escuelas: ['Escuelas', function(Escuelas){
                    return Escuelas.all();
                }]
            }
        })
        .state('index.panel-principal', {
            url: '/panel-principal',
            templateUrl: 'app/main/panel-principal/views/panel-principal.html',
            controller: 'PanelPrincipalController as ctrl',
            params:{
                profesor: null
            },
            module: 'private'
        });

    $urlRouterProvider.otherwise('/login');
  }

})();
