var app = angular.module('myApp');

app.controller('wineController',['$scope','Wine', function($scope, Wine) {
	console.log('wineController...');
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

