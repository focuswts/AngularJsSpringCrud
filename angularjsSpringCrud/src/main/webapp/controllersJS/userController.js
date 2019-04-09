app.controller(
		"userController", function($scope) {

			var pessoas = [ {
				nome : 'Felipe',
				idade : 20

			}, {
				nome : 'Joao',
				idade : 30
			}

			];
			
			$scope.pessoas = pessoas;

		});