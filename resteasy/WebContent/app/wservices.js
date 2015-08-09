var wineServices = angular.module('wineServices', [ 'ngResource' ]);

wineServices.factory('Wine', ['$resource','$http',
    function($resource,$http) {
	    var f = {};
	    f.obter = function(id) {
			return $resource('http://localhost:8080/resteasy/wine/services/wine/user/:userId', {userId:id}, {
				get: {method:'GET', params:{}, isArray:true}
			});
	    }
	    f.salvar = function(item) {
			$http.post('http://localhost:8080/resteasy/wine/services/wine',item);
	    }
	    return f;
}]);
