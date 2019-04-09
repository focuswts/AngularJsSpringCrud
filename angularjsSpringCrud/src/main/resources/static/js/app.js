var app = angular.module("firstApp", ["ui.router"]);

app.config([
  "$stateProvider",
  "$urlRouterProvider",

  function($stateProvider, $urlRouterProvider) {
    $stateProvider.state("home", {
      url: "/",
      templateUrl: "/index.html",
      controller: ""
    });

    
$urlRouterProvider.otherwise('home');

}
]);
