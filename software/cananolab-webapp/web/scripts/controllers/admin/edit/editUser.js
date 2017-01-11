'use strict';
var app = angular.module('angularApp')

    .controller('EditUserCtrl', function (navigationService,groupService,$rootScope,$scope,$http,$location,$timeout,$routeParams,$upload) {
        $scope.userForm = {};
        $scope.userForm.roles = [];
        $scope.userRoles = [];
        $scope.userTag = $routeParams.username;
        $scope.messages = '';

        $scope.loadUserData = function() {
            if( $scope.userTag != null ) {
                $scope.loader = true;
                $http({method: 'GET', url: '/caNanoLab/rest/useraccount/read?username=' + $scope.userTag}).
                success(function(data, status, headers, config) {
                    var update = data;
                    delete update.accountNonExpired;
                    delete update.accountNonLocked;
                    delete update.admin;
                    delete update.authorities;
                    delete update.credentialsNonExpired;
                    delete update.curator;
                    delete update.displayName;
                    delete update.groups;
                    delete update.password;
                    delete update.public;
                    delete update.researcher;

                    $scope.userForm = data;
                    $scope.userRoles = data.roles;
                    $scope.loader = false;

                    var msg = 'Edit user information for "' + $scope.userForm.username + '" below';
                    $scope.messages = msg;

                }).
                error(function(data, status, headers, config) {
                });
            };
        };

        $scope.loader = false;     

        $scope.doSubmitData = function() {
            $scope.loader = true;

            var roles = [];
            angular.forEach($scope.userRoles,function(item) {
                if (item) {
                    roles.push(item);
                }
            });
            $scope.userForm.roles = roles;

            var enabled = true;
            $scope.userForm.enabled = enabled;

            $http({method: 'POST', url: '/caNanoLab/rest/useraccount/create',data: $scope.userForm}).
                success(function(data, status, headers, config) {
                    $scope.loader = false;
                    	var msg = 'User successfully saved with username "' + $scope.userForm.username + '"';
                    	$scope.messages = msg;
                        $location.path("/editUser").search({username: $scope.userForm.username}).replace();
                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.loader = false;
                    $scope.messages = 'Error creating new user with username "' + $scope.userForm.username + '"';
                });
        };

        $scope.doUpdateData = function() {
            $scope.loader = true;
            var roles = [];
            angular.forEach($scope.userRoles,function(item) {
                if (item) {
                    roles.push(item);
                }
            });
            $scope.userForm.roles = roles;

            var ndx = roles.indexOf("ROLE_ANONYMOUS");
                if (ndx > -1) {
                    roles.splice(ndx, 1);
                }

            $http({method: 'POST', url: '/caNanoLab/rest/useraccount/update',data: $scope.userForm}).
                success(function(data, status, headers, config) {
                    $scope.loader = false;
                        var msg = 'User successfully updated with username "' + $scope.userForm.username + '"';
                        $scope.messages = msg;
                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.loader = false;
                    $scope.messages = 'Error updating user with username "' + $scope.userForm.username + '"';
                });
        };

        $scope.doResetPwd = function() {
            $scope.loader = true;
            $http({method: 'POST', url: '/caNanoLab/rest/useraccount/resetpwd',data: $scope.resetPwd}).
                success(function(data, status, headers, config) {
                    $scope.loader = false;
                        var msg = 'User password successfully reset for username "' + $scope.userForm.username + '"';
                        $scope.messages = msg;
                        $location.path("/userResults").replace();
                }).
                error(function(data, status, headers, config) {
                    // called asynchronously if an error occurs
                    // or server returns response with an error status.
                    $scope.loader = false;
                    $scope.messages = 'Error resetting password for user with username "' + $scope.userForm.username + '"';
                });
        };

        $scope.cancelResetPwd = function() {
            $location.path("/userResults").replace();
        };

        $scope.resetForm = function() {
            $scope.userForm = {};
            $scope.defineRoleVariables();
        };
       
        $scope.goBack = function() {
            $location.path("/userResults").replace();
            $location.search('userTag', null);
        };

        $scope.loadUserData();
    });
