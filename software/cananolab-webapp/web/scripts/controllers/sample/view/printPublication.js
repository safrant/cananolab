'use strict';
angular.module('angularApp', [
        'ngRoute', 'ngSanitize', 'ngRoute'
    ])
    .controller('PrintSamplePublicationCtrl', function (utilsService, $rootScope, $scope, $http, $filter, $routeParams, sampleService) {
        $scope.sampleId = utilsService.getParameterFromURL('sampleId');

        $scope.loader = true;
        $http({
            method: 'GET',
            url: '/caNanoLab/rest/publication/summaryPrint?sampleId=' + $scope.sampleId
        }).
        then(function (data, status, headers, config) {
            data = data['data']
            $scope.data = data;
            $scope.sampleName = sampleService.sampleName($scope.sampleId);
            $scope.publicationCategories = data.publicationCategories;
            $scope.publicationBean = data.publicationBean;
            $scope.category2Publications = data.category2Publications;
            $scope.sampleName = $routeParams.sampleName;

            $scope.loader = false;
        }).
        catch(function (data, status, headers, config) {
            data = data['data']

            // called asynchronously if an error occurs
            // or server returns response with an error status.
            $scope.message = data;
            $scope.loader = false;

        });
    });