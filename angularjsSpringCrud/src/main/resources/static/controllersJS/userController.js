app.controller("userController",function($scope,$http,$location,$state,$stateParams,fileUpload) {

	$scope.users;
 
	
	$scope.uploadFile = function(usersCSV){
	      var file = $scope.fileCSV;
          console.log('file is ' );
          console.dir(file);
          var uploadUrl = "/api/user/addUsersCSV";
          fileUpload.uploadFileToUrl(file, uploadUrl);
	}
	
	
	
	$scope.getUsers = function() {
		$http.get("/api/user/list").then(function(response) {
			$scope.users = response.data;
	console.log("Usuários Listados Com Sucesso!");
		}).catch(function(error){
			console.log("Erro Ao Listar Usuários: " + error);
		})
	};
	
	$scope.addUser = function(){
		console.log($scope.user);
$http.post('/api/user/add',$scope.user).then(
		function successCallback(response) {
  alert("Usuário Salvo Com Sucesso!");
  $location.url("/api/user/list");
  }).catch(function(error){
		console.log("Erro Ao Salvar Usuário: " + error);
	})
  }
	
	$scope.deleteUser = function(userId){
		console.log(userId);

		$http.delete('/api/user/delete/'+userId).then(
				function successCallback(response) {
		  alert("Usuário Excluído Com Sucesso!");
		  $location.url("/api/user/list");
		  $state.reload();
		  }).catch(function(error){
				console.log("Erro Ao Excluir Usuário: " + error);
			})
	}
	
	$scope.updateUserRedirect = function(user){
		$state.go('updateUser', {"user":user});
	}
	
	$scope.fillUpdateFields = function(){
		$scope.user = $stateParams.user;
	}
	
	$scope.updateUser = function(){
		var userUpdate = {
				id: $scope.user.id,
				name:$scope.user.name,
				password:$scope.user.password,
				email:$scope.user.email
		}
		
		console.log(userUpdate);
$http.put('/api/user/update/'+userUpdate.id,userUpdate).then(
		function successCallback(response) {
  alert("Usuário Salvo Com Sucesso!");
  $location.url("/api/user/list");
  }).catch(function(error){
		console.log("Erro Ao Salvar Usuário: " + error);
	})
  }
	
	

});