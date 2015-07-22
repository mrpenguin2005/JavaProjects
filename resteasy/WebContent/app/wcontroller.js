var app = angular.module('myApp', ['wineServices','ui.router']);

app.controller('wineController',['$scope','Wine', function($scope, Wine) {
	function winesUser() {
		Wine.obter($scope.userId).get({}, function(result) {
			$scope.wines = result;
		}); 
	}
//	Wine.obter($scope.userId).get({}, function(result) {
//		$scope.wines = result;
//	});	
	$scope.search = function() {
		if ($scope.userId) {
			winesUser();
		}
	}
}]);

app.config(['$stateProvider','$urlRouterProvider',function($stateProvider, $urlRouterProvider) {
	//
	// For any unmatched url, redirect to /state1
	//$urlRouterProvider.otherwise("/state1");
	
	$stateProvider
    .state('search', {
      url: "/search",
      templateUrl: "wsearch.html",
      controller: 'wineController'
    });
}]);
