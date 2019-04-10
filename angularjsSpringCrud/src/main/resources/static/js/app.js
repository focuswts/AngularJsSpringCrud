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
			}).state("addUsersCSV", {
				url : "/api/user/addUsersCSV",
				templateUrl : "/userPages/addUsersCSV.html",
				controller : "userController"
			}).state("updateUser", {
				url : "/api/user/update",
				templateUrl : "/userPages/updateUser.html",
				params : {
					user : null
				},
				controller : "userController"
			});

			$urlRouterProvider.otherwise('home');

		} ]);

app.directive('fileModel', [ '$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
} ]);

app.service('fileUpload', [ '$http', function($http) {
	this.uploadFileToUrl = function(file, uploadUrl) {
		var fd = new FormData();
		fd.append('file', file);

		$http.post(uploadUrl, fd,{headers: {'Content-Type': undefined}}).then(
				function successCallback(response) {
					  console.log("CSV Enviado");
					 
					//  $location.url("/api/user/list");
					  }).catch(function(error){
							console.log("Erro Ao Salvar Usuário: " + error);
						});
	}
	
	
} ]);
