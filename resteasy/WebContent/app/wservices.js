var wineServices = angular.module('wineServices', [ 'ngResource' ]);

wineServices.factory('Wine', ['$resource',
    function($resource) {
	    var f = {};
	    f.obter = function() {
			return $resource('http://localhost:8080/resteasy/wine/data/wine/user', {}, {
				get: {method:'GET', params:{}, isArray:true}
			});
	    }
	    
	    f.teste = function(str) {
	    	window.alert(str);
	    }
	    return f;
}]);
