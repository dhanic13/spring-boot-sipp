/*
 * Copyright (C) 2011 ArtiVisi Intermedia <info@artivisi.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
var modulApp = angular.module('modulApp', ['ngRoute', 'ui', 'modul.controller'])
    
.config(['$routeProvider', function($routeProvider){
    alert('run.......');
    $routeProvider
        .when('/', {templateUrl: '/home.html'})
        .when('/login', {templateUrl: '/login.html'})
        .when('/401', {templateUrl: '/404.html', controller: 'LoginRedirectorController'})
        .when('/master/customer', {templateUrl: 'master/customer.html'})
        .when('/master/item', {templateUrl: 'master/item.html'})
        .when('/master/pertamina_list', {templateUrl: 'master/pertamina_list.html'})
        .when('/master/pertamina?:id', {templateUrl: 'master/pertamina_form.html'})
        .when('/master/pertamina_form', {templateUrl: 'master/pertamina_form.html'})
        .otherwise({templateUrl: '/404.html'});
}])

;
