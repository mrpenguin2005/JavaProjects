'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ui.router',
  'wineServices'
])
.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
	//
	// For any unmatched url, redirect to /state1
	//$urlRouterProvider.otherwise("/state1");
	
	$stateProvider
    .state('search', {
      url: "/search",
      templateUrl: "wsearch.html",
      controller: 'wineController'
    })
    .state('edit', {
      url: "/edit",
      templateUrl: "wedit.html",
      controller: 'editController',
      params: {item: null}
    });
}]);


//Estado:
//	params : { nomeDoObjeto: null },
//	
//
//na chamada (link):
//	<a ui-sref="nomeDoEstado({ nomeDoObjeto: objetoNoControllerOrigem })">link</a>
//
//no coltroller destino:
//	$stateParams.nomeDoObjeto