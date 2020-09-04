//	jqgrid 에러 해결코드 : Cannot read property 'msie' of undefined
jQuery.browser = {};

$(document).ready(function(){	

	$("#list").jqGrid({    	
    	url: '/demo/board/viewGrid',
        mtype: 'POST',
        datatype: "JSON",
//        postData : postData,
        colNames:['시퀀스','제목', '작성자', '등록일','조회수'],
        colModel:[
            {name:'idx', index:'idx', align:'center', editable:true, width:100, sorttype:'number'},
            {name:'title', index:'title', align:'center, editable:true', edittype:"text", resizable:false},
            {name:'writer', index:'writer', align:'center, editable:true'},
            {name:'regDate', index:'regDate', align:'center, editable:true'},
            {name:'hit', index:'hit', align:'center', editable:true}
        ],
        pager: '#pager',
        rowNum:10,
        rowList:[10,20,30],
        rownumbers: true,			// row의 숫자를 표시해준다.
        sortname:'idx',				// 처음 그리드를 불러올 때에 정렬 기준 컬럼
        sortorder:"DESC",			// 정렬 기준
        sortable: false,			// colmodel 에서 sortable 기능 사용하려면 true!
        viewrecords: true,			// 그리드가 보여줄 총 페이지 현재 페이지등의 정보를 노출
        multiselect: true,			// 멀티 셀렉트 박스가 첫번째 컬럼에 생김
        editurl: "URL.action",		// 셀이 수정될 때 수정 요청을 받아서 처리할 URL
        cellEdit: true,				// 셀 수정 기능을 사용하려면 true!
//        sortname: "idx",
//        sortorder: "desc",
//        sortable: true,  //컬럼을 자쥬자재로 자리 이동할 수 있음
//        viewrecords: true,
//        gridview: true,
//        autoencode: true,
//        multiselect: true, //셀렉트박스 적용
//        height: '100%',
        caption: "My first grid"
//        loadonce: true,		// 설정해야 페이지를 넘김, 잘 작동함 꼭 설정,데이터 전체를 한번에 로딩
//        jsonReader: {
//            repeatitems:false
//        }		

    });
//	var rowid = $("#mainGrid").getGridParam("selrow");
//	console.log("rowid: " + rowid);	
//	
//	jQuery("#list").trigger('reloadGrid');       // colModel option에 frozen:true 적용해야함
//	jQuery("#list").jqGrid('navGrid','#pager'                  
//		,{ excel:true,add:true,edit:true,view:true,del:true,search:true,refresh:true}
//		   ,{ closeAfterAdd:true,reloadAfterSubmit:false,closeOnEscape:true} // Edit Optio
//		   ,{ closeAfterAdd:true,reloadAfterSubmit:false,closeOnEscape:true} // Add Option
//		   ,{ reloadAfterSubmit:false,closeOnEscape:true} // Delete Option
//		   ,{ multipleSearch:true, multipleGroup:true, showQuery: true,
//		      closeOnEscape:true, onSearch:function(){  // Search Option
//		 }},{ closeOnEscape:true} //View Parameter                
//	 );
//	jQuery("#list").jqGrid('bindKeys', {"onEnter":function( rowid ) ﻿{
//		alert("You enter a row with id:"+rowid)} 
//	});
//	jQuery("#list").jqGrid('navButtonAdd','#pager',{caption:"Excel",          
//		onClickButton: function () { jQuery("#list").jqGrid('excelExport', { url: '' });
// ﻿		//엑셀 다운로드 버튼 생성 -  URL에 실제 다운로드 구현할 액션주소 적용             ﻿
//	}});
//	jQuery("#list").jqGrid('filterToolbar',
//		﻿{﻿ stringResult: true, searchOnEnter: true, defaultSearch: "cn" });
//	jQuery("#List").jqGrid('setGroupHeaders',{ // 헤더부분 그룹핑 기능 추가
//        useColSpanStyle:true,
//        groupHeaders:[
//                      {startColumnName:
//                       'ProductGroup',numberOfColumns:3, titleText:'<center><em>Group One</em></center>'},
//                      {startColumnName:
//                       'Created', numberOfColumns: 2, titleText: '<center><em>Group Two</em></center>'}
//                      ]
//    });



//    jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
   

})


