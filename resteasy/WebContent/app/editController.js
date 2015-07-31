var app = angular.module('myApp');

app.controller('editController',['$scope', '$stateParams', function($scope, $stateParams) {
	console.log($stateParams.item);
	$scope.item = $stateParams.item;
}]);

