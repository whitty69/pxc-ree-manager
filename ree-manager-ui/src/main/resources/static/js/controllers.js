angular.module('REEManagerApp.controllers',[])
    .controller('home', function($http, $route, $rootScope) {
        var self = this;
        self.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };
        if ($rootScope.authenticated){
            console.log('setting user');
            console.log($rootScope.user.principal);
            self.principal = $rootScope.user.principal;
        }

    })
    .controller('systems', function($http) {
        var self = this;
        var systemListUrl = 'http://localhost:9000/pxc/ree/system/list/';

        $http.get(systemListUrl)
            .then(function(response) {
                self.systems = response.data;
            })
            .catch(function(response) {
                console.error('retrieval error', response.status, response.data);
            })
            .finally(function() {
                console.log("finally called - do something?");
            });
    })

    .controller('navigation', function($rootScope, $http, $location, $route) {

        var self = this;
        var authenticate = function(callback) {

            $http.get('user').then(function(response) {
                if (response.data.name) {
                    $rootScope.authenticated = true;
                    $rootScope.user = response.data;
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }, function() {
                $rootScope.authenticated = false;
                callback && callback();
            });

        }

        authenticate();

        self.credentials = {};
        self.login = function() {
            $http.post('login', $.param(self.credentials), {
                headers : {
                    "content-type" : "application/x-www-form-urlencoded"
                }
            }).then(function() {
                authenticate(function() {
                    if ($rootScope.authenticated) {
                        console.log("Login succeeded")
                        $location.path("/");
                        self.error = false;
                        $rootScope.authenticated = true;
                    } else {
                        console.log("Login failed with redirect")
                        $location.path("/login");
                        self.error = true;
                        $rootScope.authenticated = false;
                    }
                });
            }, function() {
                console.log("Login failed")
                $location.path("/login");
                self.error = true;
                $rootScope.authenticated = false;
            })
        };

        self.logout = function() {
            $http.post('logout', {}).finally(function() {
                $rootScope.authenticated = false;
                $location.path("/");
            });
        }

    });

