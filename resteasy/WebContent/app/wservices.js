var wineServices = angular.module('wineServices', [ 'ngResource' ]);

wineServices.factory('Wine', ['$resource',
    function($resource) {
		return $resource('http://localhost:8080/resteasy/ooo/data/wine/user', {}, {
			query: {method:'GET', params:{}, isArray:true}
		});
}]);
