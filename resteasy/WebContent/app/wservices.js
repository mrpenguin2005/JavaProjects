var wineServices = angular.module('wineServices', [ 'ngResource' ]);

wineServices.factory('Wine', ['$resource',
    function($resource) {
	    var f = {};
	    f.obter = function(id) {
			return $resource('http://localhost:8080/resteasy/wine/services/wine/user/:userId', {userId:id}, {
				get: {method:'GET', params:{}, isArray:true}
			});
	    }
	    f.salvar = function(item) {
			return $resource('http://localhost:8080/resteasy/wine/services/wine/:id', {id:item.id}, {
				save: {method:'POST', params:{wine:item}, isArray:false}
			});
	    }
	    return f;
}]);
