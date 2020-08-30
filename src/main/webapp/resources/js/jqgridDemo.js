//	jqgrid 에러 해결코드 : Cannot read property 'msie' of undefined
jQuery.browser = {};

$(document).ready(function(){
	
//	jqgrid 에러 해결코드 : Cannot read property 'msie' of undefined
	jQuery.browser.msie = false;
    jQuery.browser.version = 0;
    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
        jQuery.browser.msie = true;
        jQuery.browser.version = RegExp.$1;
    }
//    jqgrid 에러 해결코드 END
     
    
//    var mydata = [   //그리드를 그릴 로컬 데이터 (json 형식)
//		{idx: "1", title: "제목1", writer: "홍길동", regDate: "2000-08-01", hit: 10},
//		{idx: "2", title: "제목2", writer: "홍길동", regDate: "2000-08-01", hit: 10}
//	],
	
	
	$grid = $("#list");	   
	$grid.jqGrid({    	
    	url: '/demo/board/viewGrid',
        mtype: 'POST',
        datatype: "JSON",
//		datatype: 'local',
//		data: mydata,
        colNames:['시퀀스','제목', '작성자', '등록일','조회수'],
        colModel:[
            {name:'idx', index:'idx', align:'center'},
            {name:'title', index:'title', align:'center'},
            {name:'writer', index:'writer', align:'center'},
            {name:'regDate', index:'regDate', align:'center'},
            {name:'hit', index:'hit', align:'center'},
        ],
        height: 200,
        jsonReader : {
        	repeatitems: false
        },
        pager: '#pager',
        rowNum:10,
        rowList:[10,20,30],
        sortname: 'invid',
        sortorder: 'desc',
        viewrecords: true,
        caption:"JQGRID TABLE",
        loadComplete:function(data){
	        console.log("data:"+data);
	    }
    });
    jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
//    jQuery("#jqGrid").trigger('reloadGrid');  
//    
//    jQuery("#jqGrid").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
    
       

//    'use strict';
//    var mydata = [   //그리드를 그릴 로컬 데이터 (json 형식)
//	{id: "1",name: "홍길동"},
//	{id: "2",name: "홍길동"},
//	{id: "3",name: "홍길동"},
//	{id: "4",name: "홍길동"},
//	{id: "5",name: "홍길동"}
//	],
//	
//	$grid = $("#list");
//	   
//	$grid.jqGrid({                 // 여기서부터 본격적인 그리드 작업
////		url: '/demo/board/jqgrid',
////		datatype: "json",
//		mtype: 'GET',
//		datatype: 'local',
//		data: mydata,
//		colNames: ['id', 'name'],
//		colModel: [ 
//			{name:'id', index: 'id',align: 'center', width: 200}, 
//			{name:'name', index: 'name', width: 500}
//		            ],
//		           
//		width: '100%',
//	    height: '100%',
//	    pager: '#pager',	           
//	    caption: '그리드 제목'
//    });        
       

})


