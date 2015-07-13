var app = angular.module('myApp', []);

app.controller('customersCtrl', function($scope, $http) {
	$http.get("http://localhost:8080/resteasy/wine/data/wine/user").success(
			function(response) {
				$scope.wines = response;
			});
});