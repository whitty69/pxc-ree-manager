angular
    .module("ree-app", ['REEManagerApp.controllers', 'ngRoute'])
    .config(function($routeProvider) {

	$routeProvider.when('/', {
        templateUrl : 'partials/home.html',
        controller : 'home',
        controllerAs: 'controller'
    }).when('/system', {
        templateUrl : 'partials/system.html',
        controller : 'systems',
        controllerAs: 'controller'
    }).when('/entry', {
        templateUrl : 'partials/entry.html',
        controller : 'systems',
        controllerAs: 'controller'
    }).when('/property', {
        templateUrl : 'partials/property.html',
        controller : 'systems',
        controllerAs: 'controller'
    }).when('/login', {
		templateUrl : 'partials/home.html',
		controller : 'navigation',
		controllerAs: 'controller'
	}).otherwise('/');

});