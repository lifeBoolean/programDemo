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
     
    //jqGrid껍데기 생성
    $("#jqGrid").jqGrid({
    	url: '/demo/board/jqgrid',
        datatype: "json",
        mtype: 'POST',
        colNames:['시퀀스','제목', '작성자', '등록일','조회수'],
        colModel:[
            {name:'idx', index:'idx', align:'center'},
            {name:'title', index:'title', align:'center'},
            {name:'writer', index:'writer', align:'center'},
            {name:'regDate', index:'regDate', align:'center'},
            {name:'hit', index:'hit', align:'center'}
        ],
        height: 200,
        rowNum: 10,
//        rowList: [10,20,30],
//        pager: '#jqGridPager',
//        rownumbers  : true,        
        caption:"JQGRID TABLE",
//    	jsonReader: {
//            repeatitems:false
//       }
    });
    jQuery("#jqGrid").trigger('reloadGrid');  
    
    jQuery("#jqGrid").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});

})


