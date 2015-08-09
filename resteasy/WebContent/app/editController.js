var app = angular.module('myApp');

app.controller('editController',['$scope', '$stateParams', 'Wine', function($scope, $stateParams, Wine) {
	console.log('editController...');
	console.log($stateParams.item);
	$scope.item = $stateParams.item;
	
	$scope.submit = function(item) {
		console.log('Submit id   :'+item.id);
		console.log('Submit name :'+item.name);
		Wine.salvar(item).save({}, function(result) {
			console.log(result);
		});
	}
}]);

