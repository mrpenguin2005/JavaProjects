var app = angular.module('myApp', []);

app.controller('customersCtrl', function($scope, $http) {
	$http.get("http://localhost:8080/resteasy/wine/services/wine/user/2").success(
			function(response) {
				$scope.wines = response;
			});
});