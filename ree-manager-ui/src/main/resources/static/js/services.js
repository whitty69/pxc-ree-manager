angular.module('REEManagerApp.services', []).
factory('UserService', function() {
    return {
        isLogged: false,
        username: '',
        roles: []
    };
});