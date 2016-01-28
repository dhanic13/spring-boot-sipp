angular.module('modul.service', ['ngResource'])

.factory('CustomerService', ['$resource', '$http', function($resource, $http){
        var service = {
            dspage: $resource('/sipp/customer/:id', {}, {queryPage: {method:'GET', isArray: false}}),
            query: function(p, callback){ 
                return this.dspage.queryPage({"page": p, "size": 10}, callback); 
            },
            get: function(obj, sts) { 
                 return $http.get('/sipp/customer/'+obj+'/'+sts); 
            },
            getKode: function(obj) { 
                return $http.get('/sipp/customer/'+obj); 
            },    
            save: function(obj){
                if(obj.id == null){
                    return $http.post('/sipp/customer', obj);
                } else {
                    return $http.put('/sipp/customer/'+obj.id, obj);
                }
            },
            remove: function(obj){
                return $http.delete('/sipp/customer/'+obj);
            }
        }    
        return service;
}])

.factory('SupplierService', ['$resource', '$http', function($resource, $http){
    var service = {
        ds: $resource('/sipp/supplier/:id', {}, {queryPage: {method:'GET', isArray: false}}),
        query: function(p, callback){ 
            return this.ds.queryPage({"page": p, "size": 10}, callback); 
        },
        get: function(obj) { 
            return $http.get('/sipp/supplier/'+obj+'/E'); },
        getKode: function(obj) { 
            return $http.get('/sipp/supplier/'+obj);
        }, 
        save: function(obj){
            if(obj.id == null){
                return $http.post('/sipp/supplier', obj);
            } else {
                return $http.put('/sipp/supplier/'+obj.id, obj);
            }
        },
        remove: function(obj){
            return $http.delete('/sipp/supplier/'+obj);
        }
    }    
    return service;
}])

.factory('MateriService', ['$resource', '$http', function($resource, $http){
    var service = {
        dsmateri: $resource('/api/materi/:id'),
        get: function(param, callback){ return this.dsmateri.get(param, callback) }, 
        //query: function(){ return this.dsmateri.query() },
        getAll: function() { $http.get('/api/materi') },
        save: function(obj){
            if(obj.id == null){
                return $http.post('/api/materi', obj);
            } else {
                return $http.put('/api/materi/'+obj.id, obj);
            }
        }, 
        remove: function(obj){
            if(obj.id != null){
                return $http.delete('/api/materi/'+obj);
            }
        }
    };

    return service;
}])

.factory('ItemService', ['$resource', '$http', function($resource, $http){
    var service = {
        ds: $resource('/sipp/item/:id', {}, {queryPage: {method:'GET', isArray: false}}),
        query: function(p, callback){ 
            return this.ds.queryPage({"page": p, "size": 10}, callback); 
        },
        queryAll: function(){ return this.ds.query() },
        get: function(obj) { 
            return $http.get('/sipp/item/'+obj+'/E'); },
        getKode: function(obj) { 
            return $http.get('/sipp/item/'+obj);
        }, 
        save: function(obj){
            if(obj.id == null){
                return $http.post('/sipp/item', obj);
            } else {
                return $http.put('/sipp/item/'+obj.id, obj);
            }
        },
        remove: function(obj){
            return $http.delete('/sipp/item/'+obj);
        }
    }    
    return service;
}])

.factory('PertaminaService', ['$resource', '$http', function($resource, $http){
    var service = {
        ds: $resource('/sipp/pertamina_list/:id', {}, {queryPage: {method:'GET', isArray: false}}),
        query: function(p, callback){ 
            return this.ds.queryPage({"page": p, "size": 10}, callback); 
        },
        get: function(obj) { 
            return $http.get('/sipp/pertamina_form/'+obj+'/E'); },
        getTgl: function(obj) { 
            return $http.get('/sipp/pertamina_form/'+obj);
        }, 
        getItem: function() { 
            return $http.get('/sipp/item/');
        },
        save: function(obj){
            if(obj.id == null){
                return $http.post('/sipp/pertamina_form', obj);
            } else {
                return $http.put('/sipp/pertamina_form/'+obj.id, obj);
            }
        },
        remove: function(obj){
            return $http.delete('/sipp/pertamina_list/'+obj);
        }
    }    
    return service;
}])

.factory('PembelianService', ['$resource', '$http', function($resource, $http){
    var service = {
        ds: $resource('/sipp/pembelian_list/:id', {}, {queryPage: {method:'GET', isArray: false}}),
        query: function(p, callback){ 
            return this.ds.queryPage({"page": p, "size": 10}, callback); 
        },
        get: function(obj) { 
            return $http.get('/sipp/pembelian_form/'+obj+'/E'); },
        getNopo: function(obj) { 
            return $http.get('/sipp/pembelian_form/'+obj);
        }, 
        getSupplier: function() { 
            return $http.get('/sipp/item/');
        },
        save: function(obj){
            if(obj.id == null){
                return $http.post('/sipp/pembelian_form', obj);
            } else {
                return $http.put('/sipp/pembelian_form/'+obj.id, obj);
            }
        },
        remove: function(obj){
            return $http.delete('/sipp/pembelian_list/'+obj);
        }
    }    
    return service;
}])


;    

