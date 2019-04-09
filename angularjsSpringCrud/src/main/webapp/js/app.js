var app = angular.module("firstApp", [ "ui.router" ]);

app.config([ "$stateProvider", "$urlRouterProvider",

function($stateProvider, $urlRouterProvider) {
	$stateProvider.state("home", {
		url : "/home",
		templateUrl : "/home.html",
		controller : ""
	}).state(
			"listUsers", {
				url : "/list/users",
				templateUrl : "/userPages/listUser.html",
				controller : "userController"
			}
	);

	$urlRouterProvider.otherwise('home');

} ]);
