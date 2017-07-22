var app = angular.module( 'UserManage', [ 'ngRoute' ] );

app.value('urlBase', 'http://localhost:8080/')
	.controller("Controller", function($scope, $http, urlBase) {
		var self = this;
		self.tarefa = undefined;
		self.tarefas = [];
		self.exibeFoto = false;
		
		self.url = "http://s2.glbimg.com/75t0ep5hkLgj2rhlLqvhkJ5C_kM=/620x0/s.glbimg.com/jo/eg/f/original/2017/03/26/17438783_276703096103504_8974433572010065920_n.jpg";
		
		self.cadastraTarefa = function() {
			var nome = $scope.serFeito;
			self.tarefa = [];
			var metodo = "POST";
			$scope.serFeito = "";
			$http({
				method:metodo,
				url: urlBase + 'cadTarefa/' + nome,
				data: self.tarefa
			}).then(function sucessCallback(response) {
				self.atualizaListaTodos();
				$scope.serFeito.setText("");
			}, function errorCallback(response) {
				self.ocorreuErro();
			});
		};
		
		self.atualizaListaTodos = function () {
			$http({
				method: 'GET',
				url: urlBase + 'tarefas/'
			}).then(function successCallback(response) {
				self.tarefas = response.data;
				self.tarefa = undefined;
			}, function errorCallback(response) {
				self.ocorreuErro();
			})
		};
		
		self.atualizaListaPend = function () {
			$http({
				method: 'GET',
				url: urlBase + 'tarefasPend/'
			}).then(function successCallback(response) {
				self.tarefas = response.data;
				self.tarefa = undefined;
			}, function errorCallback(response) {
				self.ocorreuErro();
			})
		};
		
		self.atualizaListaConcluidos = function () {
			$http({
				method: 'GET',
				url: urlBase + 'tarefasConc/'
			}).then(function successCallback(response) {
				self.tarefas = response.data;
				self.tarefa = undefined;
			}, function errorCallback(response) {
				self.ocorreuErro();
			})
		};
		
		self.abreTarefa = function(tarefa) {
			
			$(tarefa.codigo).css("opacity","0");
			$(tarefa.codigo).css("width","100%");
		};
		
		self.teste = function() {
			alert("tesout");
		};
		
		self.ocorreuErro = function() {
//			alert("Erro ao se conector com o servidor.")
		};
		
		self.activate = function() {
			self.atualizaListaTodos();
		};
		
		self.activate();
	});

function umRouteConfig ( $routeProvider ) {
	// console.log( $routeProvider );
//	$routeProvider
//	.when( '/', {
//		controller: ListController,
//		templateUrl: 'list.html'
//	})
//	.when( '/update/:id/:age', {
//		controller: UpdateController,
//		templateUrl: 'detail.html'
//	})
//	.when( '/delete', {
//		
//	})
//	.otherwise({
//      redirectTo: '/'
//    });
}

app.config( umRouteConfig );

function ListController ( $scope, $http ) {
	$http.get( 'server/user.json' ).success( function ( data, status, headers, config ) {
		console.log( data );
		$scope.users = data;
	});
}

function UpdateController ( $scope, $http, $routeParams ) {
	var id = $routeParams.id;
	// var age = $routeParams.age;
	// console.log( id );
	$http.get( 'server/user.json' ).success( function ( data, status, headers, config ) {
		// console.log( data[ id ] );
		$scope.xiuUser = getObjById( id, data );	
	});
	
	$scope.update = function () {
		// console.log( $scope.xiuUser )
		$http.get( 'server/user.json', { params: $scope.xiuUser } );
	}
}

function getObjById ( id, obj ) {
	var len = obj.length;
	for(var i=0; i<len; i++){
		if( id == obj[i].id ){
			return obj[i];
		}		
	}
	return null;
}

