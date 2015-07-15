var app = angular.module('myApp', ['wineServices']);

app.controller('wineController',['$scope','Wine', function($scope, Wine){
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
