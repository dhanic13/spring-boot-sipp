angular.module('modulApp', ['modul.service', 'ui', 'ui.bootstrap'])

/*
.config(function ($stateProvider) {
    alert('runn..');
    $stateProvider
        .state('pertamina_list', {
            url:'/pertamina_list',
            templateUrl: "/master/pertamina_list"
        }).state('pertamina_edit', {
            url:'/pertamina_edit/:id',
            templateUrl: "/master/pertamina_form"
        }).state('pertamina_add', {
            url:'/pertamina_add',
            templateUrl: "/master/pertamina_form"    
        });
})   */ 

.controller('MenubarController', function($http, $scope){
        $scope.userinfo = {};
        $http.get('/sipp/homepage/userinfo').success(function(data){
            $scope.userinfo = angular.uppercase(data.user);
        });
})

.controller('MasterController', function($scope){
    $scope.daftarEmail = [
        'endy.muhardin@gmail.com',
        'endy@artivisi.com',
        'endymuhardin@yahoo.com'
    ];
    
    $scope.tambahEmail = function(){
        $scope.daftarEmail.push($scope.email);
        //$scope.email = "";
    };
    
    $scope.hapusEmail = function(x){
        var lokasiIndex = $scope.daftarEmail.indexOf(x);
        if (lokasiIndex > -1) {
            $scope.daftarEmail.splice(lokasiIndex, 1);
        }
    };
})

.controller('MateriController', function($http, $scope){
    $scope.dataMateri = {};
    
    $scope.simpanMateri = function(){
        $http.post('/api/materi', $scope.materi).then(sukses, gagal);
        
        function sukses(response){
            $scope.updateDataMateri();
        }
        function gagal(response){
            console.log(response);
            alert('Error : '+response);
        };
    };
    
    $scope.hapusMateri = function(x){
        $http.delete('/api/materi/'+x.id).then(sukses, gagal);
        function sukses(response){
            $scope.updateDataMateri();
        }
        function gagal(response){
            console.log(response);
            alert('Error : '+response);
        };
    };
    
    $scope.editMateri = function(x){
        if(x.id == null){
            return; 
        }
        $http.get('/api/materi/'+x.id).then(sukses, gagal);
        
        function sukses(response){
            $scope.materi = response.data;
        };
        
        function gagal(response){
            console.log(response);
            alert('Error : '+response);
        };
    };
    
    $scope.updateDataMateri = function(){
        $http.get('/api/materi').then(sukses, gagal);
        
        function sukses(response){
            $scope.dataMateri = response.data;
            console.log($scope.dataMateri);
        };
        
        function gagal(response){
            console.log(response.status +'; '+response.header);
            alert('Error : '+response);
        };
    };
    
    $scope.updateDataMateri();
})


.controller('CustomerController',  ['$scope', 'CustomerService', function($scope, CustomerService){
    $scope.hslcek = true;
    $scope.maxSize = 10;
    $scope.CurrentPage = 1;
        
    $scope.reloadCustomerpage = function(){
        $scope.dataCustomer = CustomerService.query($scope.CurrentPage-1, function(){
            $scope.totalItems = $scope.dataCustomer.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        });
        if ($scope.totalItems == null) {
            $scope.totalItems = $scope.dataCustomer.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        }
    };  
    
    $scope.reloadCustomerpage(); 
    
    $scope.editCustomer = function(x){ 
        if(x.id == null){
            $scope.stsEntry = "";
            return; 
        }
        CustomerService.get(x.id, "E").success(function(data){
            $scope.stsEntry = "E";
            $scope.customer = data;
            $scope.posEdit = data.kdcustomer;
        })    
    };
            
    $scope.baru = function(){
        $scope.customer = null;
        $scope.stsEntry = "T";
        $scope.posEdit = "";
    };
    
    $scope.iscustomerAvailable = function(value){
        if ($scope.stsEntry == "T" || $scope.stsEntry == "E") {
            var varValue = angular.uppercase(value);
            var editCus = $scope.posEdit.trim();
            
            $scope.prosesGet = "";
            if($scope.stsEntry == "E") {
                if (editCus == varValue) {    
                    $scope.prosesGet = "1";
                } 
            }
            if (($scope.prosesGet == "") && (varValue != "")) {
                CustomerService.getKode(varValue).then(sukses,gagal);
                function sukses(response){
                    var dtKdcus = response.data.kdcustomer;
                    if (dtKdcus.trim() == varValue) {
                        $scope.hslcek = false;
                    }
                };
                function gagal(response){
                    if (response.status != "404") {
                       console.log(response); 
                       alert('Error : '+response);
                    }   
                    $scope.hslcek = true;
                };
                return $scope.hslcek;
            } else {
                $scope.hslcek = true;
            }
        }    
        else { $scope.hslcek = true; }    
        return $scope.hslcek;
    };
    
    $scope.simpanCustomer = function(){
        CustomerService.save($scope.customer).success(function(){ 
            $scope.reloadCustomerpage();
            if ($scope.stsEntry == "E"){ 
                $('#dialogSimpan').modal('hide');
                $('#dialogCustomer').modal('hide'); 
                $scope.stsEntry == "";
            }    
            else{
                $('#dialogSimpan').modal('hide');
                $('#kdcustomer').focus();
                $scope.baru();
            }
        });
    };
    
    $scope.DelPos = function(x){
        $scope.posEdit = x.id;
    };
    
    $scope.hapusCustomer = function(){
        if($scope.posEdit == null){
            return;
        }
        CustomerService.remove($scope.posEdit).success(function(){
            $scope.reloadCustomerpage();
            $('#dialogHapus').modal('hide');
            $scope.posEdit = "";
        }) 
    };
    
    $scope.batal =  function(){
        $scope.baru();
        $scope.stsEntry == "";
        $('#dialogCustomer').modal('hide');
    };
    
    $scope.filterValue = function($event){
        if (String.fromCharCode($event.keyCode) != ".") {
            if(isNaN(String.fromCharCode($event.keyCode))){
               $event.preventDefault();
            }
        }    
    };
    
}])

.controller('SupplierController', ['$scope', 'SupplierService', function($scope, SupplierService){
    $scope.hslcek = true;
    $scope.maxSize = 10
    $scope.CurrentPage = 1;
        
    $scope.reloadSupplierpage = function(){
        $scope.dataSupplier = SupplierService.query($scope.CurrentPage-1, function(){
            $scope.totalItems = $scope.dataSupplier.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        });
        if ($scope.totalItems == null) {
            $scope.totalItems = $scope.dataSupplier.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        }

    };  
    
    $scope.reloadSupplierpage(); 
    
    $scope.editSupplier = function(x){ 
        if(x.id == null){
            $scope.stsEntry = "";
            return; 
        }
        SupplierService.get(x.id).success(function(data){ 
            $scope.stsEntry = "E";
            $scope.supplier = data;
            $scope.posEdit = data.kdsupplier;
        });
    };
            
    $scope.baru = function(){
        $scope.supplier = null;
        $scope.stsEntry = "T";
        $scope.posEdit = "";
    };
    
    $scope.issupplierAvailable = function(value){
        if ($scope.stsEntry == "T" || $scope.stsEntry == "E") {
            var varValue = angular.uppercase(value);
            var editSup = $scope.posEdit.trim();
            
            $scope.prosesGet = "";
            if($scope.stsEntry == "E") {    
                if (editSup == varValue) {
                    $scope.prosesGet = "1";
                } 
            }    
            if (($scope.prosesGet == "") &&  (varValue != "")) {
                SupplierService.getKode(varValue).then(sukses, gagal);
                function sukses(response){
                    var dtKdsup = response.data.kdsupplier;
                    if (dtKdsup.trim() == varValue) {
                        $scope.hslcek = false;
                    }
                };
                function gagal(response){
                    if (response.status != "404") {
                       console.log(response); 
                       alert('Error : '+response);
                    }   
                    $scope.hslcek = true;
                };
                return $scope.hslcek;
            } else {
                $scope.hslcek = true;
            }    
        }
        else { $scope.hslcek = true; }    
        return $scope.hslcek;
    };
    
    $scope.simpanSupplier = function(){
        SupplierService.save($scope.supplier).success(function(){ 
            $scope.reloadSupplierpage();
            if ($scope.stsEntry == "E"){ 
                $('#dialogSimpan').modal('hide');
                $('#dialogSupplier').modal('hide'); 
                $scope.stsEntry == "";
            }    
            else{
                $('#dialogSimpan').modal('hide');
                $('#kdsupplier').focus();
                $scope.baru();
            }
        });
    };
    
    $scope.DelPos = function(x){
        $scope.posEdit = x.id;
    };
    
    $scope.hapusSupplier = function(){
        if($scope.posEdit == null){
            return;
        }
        SupplierService.remove($scope.posEdit).success(function(){
            $scope.reloadSupplierpage();
            $('#dialogHapus').modal('hide');
            $scope.posEdit = "";
        }) 
    };
    
    $scope.batal =  function(){
        $scope.baru();
        $scope.stsEntry = "";
        $('#dialogSupplier').modal('hide');
    };
    
    $scope.filterValue = function($event){
        if(isNaN(String.fromCharCode($event.keyCode))){
            $event.preventDefault();
        }
    };
}])

.controller('ItemController', ['$scope', 'ItemService', function($scope, ItemService){
    $scope.hslcek = true;
    $scope.maxSize = 10;
    $scope.CurrentPage = 1;
        
    $scope.reloadItempage = function(){
        $scope.dataItem = ItemService.query($scope.CurrentPage-1, function(){
            $scope.totalItems = $scope.dataItem.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        });
        if ($scope.totalItems == null) {
            $scope.totalItems = $scope.dataItem.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        }
    };  
    
    $scope.reloadItempage(); 
    
    $scope.editItem = function(x){ 
        if(x.id == null){
            $scope.stsEntry = "";
            return; 
        }
        ItemService.get(x.id).success(function(data){ 
            $scope.stsEntry = "E";
            $scope.item = data;
            $scope.posEdit = data.kditem;
        });
    };
            
    $scope.baru = function(){
        $scope.item = null;
        $scope.stsEntry = "T";
        $scope.posEdit = "";
    };
    
    $scope.isitemAvailable = function(value){
        if ($scope.stsEntry == "T" || $scope.stsEntry == "E") {
            var varValue = angular.uppercase(value);
            var editItem = $scope.posEdit.trim();
            
            $scope.prosesGet = "";
            if($scope.stsEntry == "E") {    
                if (editItem == varValue) {
                    $scope.prosesGet = "1";
                } 
            }    
            if (($scope.prosesGet == "") &&  (varValue != "")) {
                ItemService.getKode(varValue).then(sukses, gagal);
                function sukses(response){
                    var dtKditem = response.data.kditem;
                    if (dtKditem.trim() == varValue) {
                        $scope.hslcek = false;
                    }
                };
                function gagal(response){
                    console.log(response);
                    alert('Error : '+response);
                    $scope.hslcek = false;
                };
                function gagal(response){
                    if (response.status != "404") {
                       console.log(response); 
                       alert('Error : '+response);
                    }   
                    $scope.hslcek = true;
                };
                return $scope.hslcek;
            } else {
                $scope.hslcek = true;
            }    
        }
        else { $scope.hslcek = true; }    
        return $scope.hslcek;
    };
    
    $scope.simpanItem = function(){
        ItemService.save($scope.item).success(function(data){ 
            console.log(data); 
            $scope.reloadItempage();
            if ($scope.stsEntry == "E"){ 
                $('#dialogSimpan').modal('hide');
                $('#dialogItem').modal('hide'); 
                $scope.stsEntry == "";
            }    
            else{
                $('#dialogSimpan').modal('hide');
                $('#kditem').focus();
                $scope.baru();
            }
        });
    };
    
    $scope.DelPos = function(x){
        $scope.posEdit = x.id;
    };
    
    $scope.hapusItem = function(){
        if($scope.posEdit == null){
            return;
        }
        ItemService.remove($scope.posEdit).success(function(){
            $scope.reloadItempage();
            $('#dialogHapus').modal('hide');
            $scope.posEdit = "";
        }) 
    };
    
    $scope.batal =  function(){
        $scope.baru();
        $scope.stsEntry = "";
        $('#dialogItem').modal('hide');
    };
    
}])

.controller('PertaminaListController', ['$scope', 'PertaminaService', function($scope, PertaminaService){
    $scope.maxSize = 10;
    $scope.CurrentPage = 1;
    
    
    $scope.reloadPertaminapage = function(){
        $scope.dataPertamina = PertaminaService.query($scope.CurrentPage-1, function(){
            $scope.totalItems = $scope.dataPertamina.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        });
        if ($scope.totalItems == null) {
            $scope.totalItems = $scope.dataPertamina.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        }
    };  
    
    $scope.reloadPertaminapage(); 
    
    $scope.DelPos = function(x){
        $scope.posEdit = x.id;
    };    
    
    $scope.hapusPertamina = function(){
        if($scope.posEdit == null){
            return;
        }
        PertaminaService.remove($scope.posEdit).success(function(){
            $scope.reloadPertaminapage();
            $('#dialogHapus').modal('hide');
            $scope.posEdit = null;
        }); 
    };
}])    

.controller('PertaminaFormController', ['$scope', '$filter', 'PertaminaService', 'ItemService',
                    function($scope, $filter, PertaminaService, ItemService){
                        
    $scope.hslcek = true;
    $scope.pilihanItem = [{id: "", kditem: "", nmitem: ""}];
    $scope.kodeAvailable = false;
    
    $scope.editPertamina = function(x){ 
        if (x.id == null){
            $scope.stsEntry = "";
            return; 
        }
        PertaminaService.get(x.id).success(function(data){ 
            $scope.stsEntry = "E";
            $scope.posEdit = data.tgl;
            $scope.pertamina = angular.copy(data);
            $scope.pertamina.iditem = data.item.id;
            $scope.pertamina.tgl = $filter('date')($scope.pertamina.tgl, "dd-MM-yyyy");
            if ($scope.pertamina.tglakhir != null) {  
                $scope.pertamina.tglakhir = $filter('date')($scope.pertamina.tglakhir, "dd-MM-yyyy");
            }
            $scope.kditem = data.item.kditem;
            $scope.nmitem = data.item.nmitem;
            $scope.poskditem = data.item.kditem;
        });
    };
            
    $scope.baru = function(){
        $scope.pertamina = {id: "", tgl: "", tglakhir: "", harga: 0, iditem: ""};
        $scope.stsEntry = "T";
        $scope.kditem = "";
        $scope.posEdit = null;
        $scope.poskditem = "";
    };
    
    $scope.baru();
    
    $scope.isTglCek = function(value){
        if (angular.isDate(value)){
            var tmpVar = $filter('date')(value, "dd");
            
            $scope.pertamina.tglakhir = "";
            if (tmpVar != "01" && tmpVar != "15" ) {
                return false;  
            } 
            else {
                if (tmpVar == "01") { 
                    $scope.pertamina.tglakhir = "14-" + $filter('date')(value, "MM-yyyy"); 
                }  
                else {
                    tmpVar = $filter('date')(value, "MM");
                    var dtgl = "";
                    if (tmpVar == "02"){
                        dtgl = $filter('date')(value, "yyyy-MM") + "-29"; 
                        if (angular.isDate(new Date(dtgl))) {
                            $scope.pertamina.tglakhir = "29-" + $filter('date')(value, "MM-yyyy");
                        } else {
                            dtgl = $filter('date')(value, "yyyy-MM") + "-28"; 
                            if (angular.isDate(new Date(dtgl))) { 
                                $scope.pertamina.tglakhir = "28-" + $filter('date')(value, "MM-yyyy");
                            }
                        }
                    }  
                    else {
                        dtgl = $filter('date')(value, "yyyy-MM") + "-31"; 
                        if (angular.isDate(new Date(dtgl))) {
                            $scope.pertamina.tglakhir = "31-" + $filter('date')(value, "MM-yyyy");
                        } else {
                            dtgl = $filter('date')(value, "yyyy-MM") + "-30"; 
                            if (angular.isDate(new Date(dtgl))) { 
                                $scope.pertamina.tglakhir = "30-" + $filter('date')(value, "MM-yyyy");
                            }
                        }
                    }
                }
                return true;
            }
        } 
        else { return false; }    
    };
    
    $scope.istglAvailable = function(value){
        if ($scope.stsEntry == "T" || $scope.stsEntry == "E") {
            var varValue = $filter('date')(value, "yyyy-MM-dd");

            $scope.prosesGet = "";
            if($scope.stsEntry == "E") {    
                if ($scope.posEdit == varValue) {
                    $scope.prosesGet = "1";
                    $scope.hslcek = true;
                }
            }    
            if (($scope.prosesGet == "") &&  (varValue != "")) {
                PertaminaService.getTgl(varValue).then(sukses, gagal);
                function sukses(response){
                    var dtTgl = response.data.tgl;
                    if (dtTgl == varValue) {
                        $scope.hslcek = false;
                        
                    } else {$scope.hslcek = true;}
                };
                function gagal(response){
                    var sts = response.status
                    if (sts != "404" && sts != "400") {
                       console.log(response); 
                       alert('Error Respond : '+response + '  '+response.status);
                    }
                    $scope.hslcek = true;
                };
                return $scope.hslcek;
            } else {
                $scope.hslcek = true;
            }    
        }
        else { $scope.hslcek = true; }    
        return $scope.hslcek;
    };

    $scope.CariKodeAvailable = function(){
        $scope.nmitem = "";
        if ($scope.kditem != "" && $scope.kditem != null) {
            if ($scope.stsEntry == "E" && $scope.poskditem == angular.uppercase($scope.kditem)) {
               $scope.kodeAvailable = false;
            }
            ItemService.getKode(angular.uppercase($scope.kditem)).then(sukses, gagal);
            function sukses(response){
                $scope.pertamina.iditem = response.data.id;
                $scope.nmitem = response.data.nmitem;
                $scope.kodeAvailable = false;
            };
            function gagal(response){
                var sts = response.status
                if (sts != "404" && sts != "400") {
                   console.log(response); 
                   alert('Error Respond : '+response + '  '+response.status);
                }
                $scope.pertamina.iditem = "";
                $scope.kodeAvailable = true;
            };
        } 
        else { $scope.kodeAvailable = false; }
    }; 
    
    $scope.simpanPertamina = function(){
        var dTgl = $scope.pertamina.tgl;
        dTgl = $filter('date')(dTgl, "yyyy-MM-dd");
        $scope.pertamina.tgl = dTgl; 
        var cTgl = $scope.pertamina.tglakhir.split("-");
        var dTglAkhir = cTgl[2] + "-" + cTgl[1] + "-" + cTgl[0];
        $scope.pertamina.tglakhir = dTglAkhir;
        
        if ($scope.stsEntry == "E") {
           $scope.recPertamina = {id: $scope.pertamina.id, tgl: dTgl,
                                tglakhir: dTglAkhir, harga: $scope.pertamina.harga,
                                item: {id: $scope.pertamina.iditem}};
        }
        else {
            $scope.recPertamina = {tgl: dTgl, tglakhir: dTglAkhir,
                                harga: $scope.pertamina.harga,
                                item: {id: $scope.pertamina.iditem}};
        }  
        PertaminaService.save($scope.recPertamina).success(function(){ 
            if ($scope.stsEntry == "E"){ 
                $('#dialogSimpan').modal('hide');
                $scope.stsEntry == "";
            }    
            else{
                $('#dialogSimpan').modal('hide');
                $('#tgl').focus();
                $scope.baru();
            }
        });
    };
    
    $scope.reloadItem = function() {
        $scope.dataItem = ItemService.query();
    };
    
    $scope.selectItem = function(data){
        $scope.pilihanItem.id = data.id;
        $scope.pilihanItem.kditem = data.kditem;
        $scope.pilihanItem.nmitem = data.nmitem;
    };
    
    $scope.selectItemOk =  function(){
        $scope.pertamina.iditem = $scope.pilihanItem.id;
        $scope.kditem = $scope.pilihanItem.kditem;
        $scope.nmitem = $scope.pilihanItem.nmitem;
        $('#dialogItem').modal('hide');
    };
    
    $scope.batal =  function(){
        $scope.baru();
        $scope.stsEntry = "";
    };
    
    $scope.openTgl = function($event) {
        $scope.statusTgl.opened = true;
    };
    
    $scope.statusTgl = {
        opened: false
    };
    
    $scope.BrowseItem =function(){
        $scope.reloadItem();
        $('#dialogItem').modal('show');
    };
    
    $scope.KeyCek = function(event){
        if (event.keyCode === 113) {
            $scope.BrowseItem();
        } 
        else if (event.keyCode === 13 ) {
            $scope.CariKodeAvailable();
        }
        $scope.kodeAvailable = false;
        $scope.nmitem="";
        //alert('key code '+ event.keyCode );
    };
    
    $scope.filterValue = function($event){
        if(isNaN(String.fromCharCode($event.keyCode))){
            $event.preventDefault();
        }
    };
    
}])

.controller('PembelianListController', ['$scope', 'PembelianService', function($scope, PembelianService){
    $scope.maxSize = 10;
    $scope.CurrentPage = 1;
    
    $scope.reloadPembelianpage = function(){
        $scope.dataPembelian = PembelianService.query($scope.CurrentPage-1, function(){
            $scope.totalItems = $scope.dataPembelian.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        });
        if ($scope.totalItems == null) {
            $scope.totalItems = $scope.dataPembelian.totalPages;
            $scope.bigTotalItems = $scope.totalItems * $scope.maxSize;
        }
    };  
    
    $scope.DelPos = function(x){
        $scope.posEdit = x.id;
    }; 
    
    $scope.hapusPembelian = function(){
        if($scope.posEdit == null){
            return;
        }
        PembelianService.remove($scope.posEdit).success(function(){
            $scope.reloadPembelianpage();
            $('#dialogHapus').modal('hide');
            $scope.posEdit = null;
        }); 
    };

    $scope.reloadPembelianpage(); 
}])   

.controller('PembelianFormController', ['$scope', '$filter', 'PembelianService', 'SupplierService', 'ItemService',
                    function($scope, $filter, PembelianService, SupplierService, ItemService){
    $scope.editPembelian = function(x){ 
        if (x.id == null) {
            $scope.stsEntry = "";
            return; 
        }
        PembelianService.get(x.id).success(function(data){ 
            $scope.stsEntry = "E";
            $scope.posEdit = data.tgl;
            $scope.pembelian = data;
            $scope.pembelian.idsupplier = data.supplier.id;
            $scope.pembelian.tgl = $filter('date')($scope.pembelian.tgl, "dd-MM-yyyy");
            
	    $scope.kdsupplier = data.item.kdsupplier;
            $scope.nmsupplier = data.item.nmsupplier;
            $scope.poskdsupplier = data.item.kdsupplier;
        });
    };
            
    $scope.baru = function(){
        $scope.pembelian = [];
        $scope.stsEntry = "T";
        $scope.kditem = "";
        $scope.posEdit = null;
        $scope.poskdsupplier = "";
        $scope.pilihan = [{id: "", kode: "", nama: ""}];
        $scope.nmsupplier = "";
        $scope.kdSupAvailable = false;
        $scope.dataDetail = [];
    };
    
    $scope.baru();
    
    $scope.CariKodeSuppAvailable = function(){
        $scope.nmsupplier = "";
        if ($scope.kdsupplier != "" && $scope.kdsupplier != null) {
            if ($scope.stsEntry == "E" && $scope.poskdsupplier == value) {
                $scope.kdSupAvailable = false; 
            }
            SupplierService.getKode(angular.uppercase($scope.kdsupplier)).then(sukses, gagal);
            function sukses(response){
                $scope.pembelian.idsupplier = response.data.id;
                $scope.nmsupplier = response.data.nmsupplier;
                $scope.kdSupAvailable = false;
            };
            function gagal(response){
                var sts = response.status
                if (sts != "404" && sts != "400") {
                   console.log(response); 
                   alert('Error Respond : '+response + '  '+response.status);
                }
                $scope.pembelian.idsupplier = "";
                $scope.kdSupAvailable = true;
            };
        } 
        else { $scope.kdSupAvailable = false; }
    }; 
    
    $scope.CariKodeItemAvailable = function(){
        $scope.nmitem = "";
        if ($scope.kditem != "" && $scope.kditem != null) {
            if ($scope.stsDetail == "E" && $scope.posKodeDetail == $scope.kditem) {
                $scope.kdItemAvailable = false; 
            }
            ItemService.getKode(angular.uppercase($scope.kditem)).then(sukses, gagal);
            function sukses(response){
                $scope.iditem = response.data.id;
                $scope.nmitem = response.data.nmitem;
                $scope.kdItemAvailable = false;
                $('#qty').focus();
            };
            function gagal(response){
                var sts = response.status
                if (sts != "404" && sts != "400") {
                   console.log(response); 
                   alert('Error Respond : '+response + '  '+response.status);
                }
                $scope.iditem = "";
                $scope.kdItemAvailable = true;
            };
        } 
        else { $scope.kdItemAvailable = false; }
    };
    
    $scope.simpanPembelian = function(){
        /*
        var jwb = confirm("Data Benar Akan di SIMPAN.....?");
        if (jwb == false) { return; }
        var dTgl = angular.copy($scope.pembelian.tgl);
        dTgl = $filter('date')(dTgl, "yyyy-MM-dd");
        $scope.pembelian.tgl = dTgl; 
        
        //$scope.hitungTotal();
        //$scope.createDetail();
        
        if ($scope.stsEntry == "E") {
            $scope.dataPembelian = {id: $scope.pembelian.id, tgl: dTgl, ppn: 0, pbbkb:0,
                                  supplier: $scope.pembelian.idsupplier,
                                  detailPembelian: {}};
        }
        else {
            $scope.dataPembelian = {tgl: dTgl, ppn: 0, pbbkb:0,
                                  supplier: $scope.pembelian.idsupplier,
                                  detailPembelian: {}};
            
        } */
        $scope.newDetail = [];
        $scope.newDetail.push({item: {id: '47faef6a-a3a4-44e6-a045-a8278313708a'}, pembelian: {}, //id: 'pbl01'
            qty: 8000, harga: 7100, pph: 1500000, bph: 0, ppntrans: 0, pph22: 0});
        
        $scope.newDetail.push({item: {id: 'b4484a84-2958-4b3c-a1c6-6cee5b1ce957'}, pembelian: {},
            qty: 9000, harga: 7200, pph: 1800000, bph: 0, ppntrans: 0, pph22: 0});
        
        var dataPembelian = {nopo: '20150125', tgl: '2016-01-25', ppn: 1000000, pbbkb: 1100000,
                             supplier: {id: 'S002'}, detailPembelian: $scope.newDetail};
        
        //dataPembelian.detailPembelian.push($scope.newDetail);
        PembelianService.save(dataPembelian).success(function() { 
            $('#dialogSimpan').modal('hide');
            $('#nopo').focus();
            $scope.baru();
        });
    };
    
    /*
    $scope.hitungTotal = function() {
        for(var i = 0; i < $scope.dataDetail.length; i++){
            var u = $scope.dataDetail[i].pph;
            if(u.name === value){
                return false;
            }
        }
    }; */
         
    $scope.createDetail =  function() {
        $scope.newDetail = [];
        for(var i = 0; i < $scope.dataDetail.length; i++){
            $scope.newDetail.push({iditem: $scope.dataDetail[i].iditem,
            qty: $scope.dataDetail[i].qty,
            harga: $scope.dataDetail[i].harga,
            pph: $scope.dataDetail[i].pph,
            bph: $scope.dataDetail[i].bph,
            ppntrans: $scope.dataDetail[i].ppntrans,
            pph22: $scope.dataDetail[i].pph22});
        }
    };     
    
    $scope.saveDetail =  function(){
        var jumlah = (Number($scope.qty) * Number($scope.harga)) + Number($scope.pph) + Number($scope.bph) + Number($scope.ppntrans) + Number($scope.pph22);
        if ($scope.stsDetail == "T") {
            $scope.dataDetail.push({iditem: $scope.iditem, kditem: $scope.kditem, nmitem: $scope.nmitem, qty: $scope.qty,
                harga: $scope.harga, pph: $scope.pph, bph: $scope.bph, ppntrans: $scope.ppntrans, pph22: $scope.pph22, jumlah: jumlah});
            $scope.addDetail();
            $('#kditem').focus();
        }
        else {
            var dataDetail = $scope.dataDetail[$scope.posDetail];  
            dataDetail.iditem = $scope.iditem;
            dataDetail.kditem = $scope.kditem;
            dataDetail.nmitem = $scope.nmitem;
            dataDetail.qty = $scope.qty;
            dataDetail.harga = $scope.harga;
            dataDetail.pph = $scope.pph;
            dataDetail.bph = $scope.bph;
            dataDetail.ppntrans = $scope.ppntrans;
            dataDetail.pph22 = $scope.pph22;
            dataDetail.jumlah = jumlah; 
            $('#dialogDetail').modal('hide');
        }
    };
    
    $scope.editDetail =  function(data){
        $scope.iditem = data.iditem;
        $scope.kditem = data.kditem;
        $scope.nmitem = data.nmitem;
        $scope.qty = data.qty;
        $scope.harga = data.harga;
        $scope.pph = data.pph;
        $scope.bph = data.bph;
        $scope.ppntrans = data.ppntrans;
        $scope.pph22 = data.pph22;
        $scope.stsDetail = "E";
        $scope.posKodeDetail = data.kditem;
        $scope.posDetail = $scope.dataDetail.indexOf(data);
    };
    
    $scope.addDetail =  function(){
        $scope.iditem = "";
        $scope.kditem = "";
        $scope.nmitem = "";
        $scope.qty = null;
        $scope.harga = null;
        $scope.pph = null;
        $scope.bph = 0;
        $scope.ppntrans = 0;
        $scope.pph22 = 0;
        $scope.jumlah = 0;
        $scope.stsDetail = "T";
    };
    
    $scope.delDetail = function(data) {  
       var index = $scope.dataDetail.indexOf(data);  
       $scope.dataDetail.splice(index, 1);  
    };
    
    $scope.batal =  function(){
        $scope.baru();
        $scope.stsEntry = "";
    };
    
    $scope.openTgl = function($event) {
        $scope.statusTgl.opened = true;
    };
    
    $scope.statusTgl = {
        opened: false
    };
    
    $scope.reloadItem = function() {
        $scope.dataItem = ItemService.query();
    };
    
    $scope.selectItem = function(data){
        $scope.pilihan.id = data.id;
        $scope.pilihan.kode = data.kditem;
        $scope.pilihan.nama = data.nmitem;
    };
    
    $scope.selectItemOk =  function(){
        $scope.iditem = $scope.pilihan.id;
        $scope.kditem = $scope.pilihan.kode;
        $scope.nmitem = $scope.pilihan.nama;
        $('#dialogItem').modal('hide');
        $('#qty').focus();
    };
    
    $scope.reloadSupplier = function() {
        $scope.dataSupplier = SupplierService.query();
    };
    
    $scope.selectSupplier = function(data){
        $scope.pilihan.id = data.id;
        $scope.pilihan.kode = data.kdsupplier;
        $scope.pilihan.nama = data.nmsupplier;
    };
    
    $scope.selectSupplierOk =  function(){
        $scope.pembelian.idsupplier = $scope.pilihan.id;
        $scope.kdsupplier = $scope.pilihan.kode;
        $scope.nmsupplier = $scope.pilihan.nama;
        $('#dialogSupplier').modal('hide');
    };
    
    $scope.BrowseSupplier = function(){
        $scope.reloadSupplier();
        $('#dialogSupplier').modal('show');
    };
    
    $scope.BrowseItem = function(){
        $scope.reloadItem();
        $('#dialogItem').modal('show');
    };
    
    $scope.KeyNumber = function($event){
        if (String.fromCharCode($event.keyCode) != ".") {
            if(isNaN(String.fromCharCode($event.keyCode))){
               $event.preventDefault();
            }
        }    
    };
    
    $scope.KeyCekSupplier = function(event){
        if (event.keyCode === 113) {
            $scope.BrowseSupplier();
        } 
        else if (event.keyCode === 13 ) {
            $scope.CariKodeSuppAvailable();
        }
        $scope.kdSupAvailable = false;
        $scope.nmsupplier="";
        //alert('key code '+ event.keyCode );
    };
    
    $scope.KeyCekItem = function(event){
        if (event.keyCode === 113) {
            $scope.BrowseItem();
        } 
        else if (event.keyCode === 13 ) {
            $scope.CariKodeItemAvailable();
        }
        $scope.kdItemAvailable = false;
        $scope.nmitem="";
    };
    
    $scope.filterValue = function($event){
        if(isNaN(String.fromCharCode($event.keyCode))){
            $event.preventDefault();
        }
    };
    
    
}])

;