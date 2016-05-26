var app = angular.module('game-app', ['ngSanitize']);

app.controller('GameCtrl', function($scope, $http, $window)
{
	
	$scope.redirect = function(appId){
		/*service.set(appId);
		console.log('appID: '+appId);
		console.log('service: '+service.get());*/
		$window.location.href = '/details.html?appId='+appId;
	}
	
	$scope.videoUrl = '';
	
	$scope.getGameDetails = function(){
		var param = window.location.search.substring(1);
		var paramVars = param.split('=');
		var appId = paramVars[1];

		var soapMessage = '<?xml version="1.0" encoding="utf-8"?>\
            <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap12:Header>\
            </soap12:Header>\
            <soap12:Body>\
              <gameDetailsRequest xmlns="http://www.hu.nl/gameDetailsRequest">\
					<appId>'+appId+'</appId>\
				</gameDetailsRequest>\
            </soap12:Body>\
          </soap12:Envelope>';
		var headers = {'Content-Type': 'text/xml; charset=utf-8'};
		var url = "http://localhost:8080/ws-producer";
		$scope.Game = null;
		$http.post(url, soapMessage,{"headers":headers}).success(function(data){
			//console.log(data);
			var x2js = new X2JS();
            var json = x2js.xml_str2json( data );
            console.log(json);
            $scope.Game = json.Envelope.Body.gameDetailsResponse;
            loadPlayer($scope.Game.videoId);
		});
		

	}
	
	$scope.getGames = function(){
		$scope.Games = null;
		var soapMessage = '<?xml version="1.0" encoding="utf-8"?>\
            <soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/soap/envelope/">\
            <soap12:Header>\
            </soap12:Header>\
            <soap12:Body>\
              <gamesRequest xmlns="http://www.hu.nl/gamesRequest"/>\
            </soap12:Body>\
          </soap12:Envelope>';
		var headers = {'Content-Type': 'text/xml; charset=utf-8'};
		var url = "http://localhost:8080/ws-producer";
		$scope.Game = null;
		$http.post(url, soapMessage,{"headers":headers}).success(function(data){
			var x2js = new X2JS();
            var json = x2js.xml_str2json( data );
            
            var gameList = json.Envelope.Body.gameListResponse.gamesResponse;
            
            $scope.Games = gameList;
		});	
	}
})