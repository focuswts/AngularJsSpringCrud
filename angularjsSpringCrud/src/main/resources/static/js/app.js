var app = angular.module("firstApp", [ "ui.router" ]);

app.config([ "$stateProvider", "$urlRouterProvider",
		function($stateProvider, $urlRouterProvider) {
			$stateProvider.state("home", {
				url : "/",
				templateUrl : "/home.html",
				controller : ""
			}).state("listUsers", {
				url : "/api/user/list",
				templateUrl : "/userPages/listUser.html",
				controller : "userController"
			}).state("addUser", {
				url : "/api/user/add",
				templateUrl : "/userPages/addUser.html",
				controller : "userController"
			}).state("updateUser", {
				url : "/api/user/update",
				templateUrl : "/userPages/updateUser.html",
				params:{user:null},
				controller : "userController"
			});

			$urlRouterProvider.otherwise('home');

		} ]);
