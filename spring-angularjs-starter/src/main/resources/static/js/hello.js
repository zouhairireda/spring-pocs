var module = angular.module('hello', []);

module.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    };
}]);

module.service('fileUpload', ['$https:', function ($https:) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', file);
     
        $https:.post(uploadUrl, fd, {
           transformRequest: angular.identity,
           headers: {'Content-Type': undefined}
        })
     
        .success(function(){
        });
     
        .error(function(){
        });
     }
}]);

module.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
    
//	$http.get('/resource/').success(function(data) {
//		$scope.greeting = data;
//	});
	
	$scope.uploadFile = function(){
       var file = $scope.myFile;
       
       console.log('file is ' );
       console.dir(file);
       
       var uploadUrl = "/fileUpload";
       fileUpload.uploadFileToUrl(file, uploadUrl);
    };
}]);