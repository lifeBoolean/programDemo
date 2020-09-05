$(document).ready(function(){	

	$grid = $("#list");	   
	$grid.jqGrid({    	
    	url: '/demo/board/viewGrid',
        mtype: 'POST',
        datatype: "JSON",
        colNames:['시퀀스','제목', '작성자', '등록일','조회수', '상태'],
        colModel:[
            {name:'idx', index:'idx', width: 60, frozen:true},
            {name:'title', index:'title',frozen:true},
            {name:'writer', index:'writer'},
            {name:'regDate', index:'regDate'},
            {name:'hit', index:'hit'},
            {name:'status', index:'status'}
        ],
        pager: '#pager',
        rowNum:10,
        rowList:[10,20,30],
        rownumbers: true,			// row의 숫자를 표시해준다.
        sortname:'idx',				// 처음 그리드를 불러올 때에 정렬 기준 컬럼
        sortorder:"DESC",			// 정렬 기준
        sortable: true,			// colmodel 에서 sortable 기능 사용하려면 true!
        viewrecords: true,			// 그리드가 보여줄 총 페이지 현재 페이지등의 정보를 노출
        multiselect: true,			// 멀티 셀렉트 박스가 첫번째 컬럼에 생김
        editurl: "URL.action",		// 셀이 수정될 때 수정 요청을 받아서 처리할 URL
        cellEdit: true,				// 셀 수정 기능을 사용하려면 true!
        
        caption: "My first grid",
        
        
        autowidth:true,
        height: "auto",
        jsonReader: {
        	root : "rows",  // list 이름
            page :  "page",
            records:  "records",
            total : "total",
            repeatitems:false
        },
        loadonce: true,		// 설정해야 페이지를 넘김, 잘 작동함 꼭 설정,데이터 전체를 한번에 로딩
        loadComplete : function(data){
        	
        	

        	
        	var rowDatas = $grid.getRowData();
        	console.log("rowDatas: " + rowDatas.records);
        	var obj = $grid.getRowData(1);
        	console.log("rowDatas(idx): " + obj);

        	
        	for(var k in obj) {
        		console.log("rows: " + obj[k]);
        	}

        	
        	
        	
        	
        	console.log(data);
            // 그리드 데이터 총 갯수
        	let rowLength = data.rows.length;
        	console.log("rowLength: " + rowLength);
        	
            var allRowsInGrid = rowLength;
            console.log("allRowsInGrid: " + allRowsInGrid);
           
            // 데이터가 없을 경우 (먼저 태그 초기화 한 후에 적용)
            $("#NoData").html("");
            if(allRowsInGrid==0){
                $("#NoData").html("<br>데이터가 없습니다.<br>");
            }
            // 처음 currentPage는 널값으로 세팅 (=1)
            initPage($('#List'),allRowsInGrid,"");
           
        },
        onSelectRow : function(data){                            // 로우 클릭
        	var selRowId = $grid.getGridParam('selrow');
        	console.log("selRowId: " + selRowId);
        },onPaging: function (pgButton) {
            var gridPage = $("#jqGrid").getGridParam("page");
            var totalPage = $("#jqGrid").getGridParam("total");
     
	        console.log("page11111111: " + gridPage);
        }





    });
	$grid.jqGrid('setFrozenColumns');    ﻿﻿﻿
    $grid.trigger('reloadGrid');
	
	
	
	// 삭제 로직
	$('#deleteBtn').click(function() {
		var id = $grid.getGridParam('selarrrow');    // 멀티셀렉트에 선택된 행만 가져오기
		console.log("id: " + id);
		var data = '';
		var jsonStr = '';
		
		for(var k in id) {
			var rowdata = $('#list').getRowData(id[k]);
			console.log(rowdata);
			jsonStr = JSON.stringify(rowdata);
			data += jsonStr + ',';
			console.log(data);
		}
		console.log(data);

		var dotDelStr = data.substr(0, data.length -1);
		var jsonStrAll = '{"data":['+ dotDelStr +']}';
		console.log(jsonStrAll);
//		
//		var jsonStr = JSON.stringify(str);
//		console.log(jsonStr);

		
//		return false;
		
		if(id.length <= 0) {
			alert('삭제할 데이터를 선택하세요.');
			return false;
		} else {
			var result = confirm('선택한 data를 삭제하시겠습니까?'); 
			if(!result) {
				return false;
			} else {
				$.ajax({                                       //  삭제 데이터를 Ajax로 서버단에 보내주기
					url: "/demo/board/gridDelete",
					type: 'POST',
					dataType: "json",
					contentType : "application/json; charset:UTF-8",
				    data: jsonStrAll,
				    success:function(data){
				    	
			            console.log("success");	
			            console.log(data);			            
			            
			            $grid.jqGrid('setGridParam', {
                            url: '/demo/board/viewGrid',
                            datatype: "json"
                        }).trigger("reloadGrid");
			    	},
			        error:function(request,status,error){
			            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error);
			        }
				   	   
                })
//				.done(function( msg ) {
//				    console.log("done");
//				    alert("s :" + textStatus);
//                    $grid.jqGrid('setGridParam', {
//                        url: '/demo/board/viewGrid',
//                        mtype: 'POST',
//                        datatype: "JSON",
//                    }).trigger("reloadGrid");
//				})
//				.fail(function(xhr, status, errorThrown) {
//				    $("#text").html("오류가 발생했습니다.<br>")
//				    .append("오류명: " + errorThrown + "<br>")
//				    .append("상태: " + status);
//				})
//				.always(function(xhr, status) {
//				    console.log("완료되었습니다.");
//				});
			}
		}
		
		
	})
	
	//그리드 페이징
	function initPage(gridId,totalSize,currentPage1){
		console.log("gridId: " + gridId);
	   
	    // 변수로 그리드아이디, 총 데이터 수, 현재 페이지를 받는다
	    if(currentPage1==""){
	    	
	        var currentPage = $('#List').getGridParam('page');
	        console.log("page: " + currentPage);
	        console.log("page: " + currentPage);
	    }
	    // 한 페이지에 보여줄 페이지 수 (ex:1 2 3 4 5)
	    var pageCount = 10;
	    // 그리드 데이터 전체의 페이지 수
	    var totalPage = Math.ceil(totalSize/$('#List').getGridParam('rowNum'));
	    // 전체 페이지 수를 한화면에 보여줄 페이지로 나눈다.
	    var totalPageList = Math.ceil(totalPage/pageCount);
	    // 페이지 리스트가 몇번째 리스트인지
	    var pageList=Math.ceil(currentPage/pageCount);
	   
	    //alert("currentPage="+currentPage+"/ totalPage="+totalSize);
	    //alert("pageCount="+pageCount+"/ pageList="+pageList);
	   
	    // 페이지 리스트가 1보다 작으면 1로 초기화
	    if(pageList<1) pageList=1;
	    // 페이지 리스트가 총 페이지 리스트보다 커지면 총 페이지 리스트로 설정
	    if(pageList>totalPageList) pageList = totalPageList;
	    // 시작 페이지
	    var startPageList=((pageList-1)*pageCount)+1;
	    // 끝 페이지
	    var endPageList=startPageList+pageCount-1;
	   
	    //alert("startPageList="+startPageList+"/ endPageList="+endPageList);
	   
	    // 시작 페이지와 끝페이지가 1보다 작으면 1로 설정
	    // 끝 페이지가 마지막 페이지보다 클 경우 마지막 페이지값으로 설정
	    if(startPageList<1) startPageList=1;
	    if(endPageList>totalPage) endPageList=totalPage;
	    if(endPageList<1) endPageList=1;
	   
	    // 페이징 DIV에 넣어줄 태그 생성변수
	    var pageInner="";
	   
	    // 페이지 리스트가 1이나 데이터가 없을 경우 (링크 빼고 흐린 이미지로 변경)
	    if(pageList<2){
	       
	        pageInner+="<img src='firstPage2.gif'>";
	        pageInner+="<img src='prePage2.gif'>";
	       
	    }
	    // 이전 페이지 리스트가 있을 경우 (링크넣고 뚜렷한 이미지로 변경)
	    if(pageList>1){
	       
	        pageInner+="<a class='first' href='javascript:firstPage()'><img src='firstPage.gif'></a>";
	        pageInner+="<a class='pre' href='javascript:prePage("+totalSize+")'><img src='prePage.gif'></a>";
	       
	    }
	    // 페이지 숫자를 찍으며 태그생성 (현재페이지는 강조태그)
	    for(var i=startPageList; i<=endPageList; i++){
	        if(i==currentPage){
	            pageInner = pageInner +"<a href='javascript:goPage("+(i)+")' id='"+(i)+"'><strong>"+(i)+"</strong></a> ";
	        }else{
	            pageInner = pageInner +"<a href='javascript:goPage("+(i)+")' id='"+(i)+"'>"+(i)+"</a> ";
	        }
	       
	    }
	    //alert("총페이지 갯수"+totalPageList);
	    //alert("현재페이지리스트 번호"+pageList);
	   
	    // 다음 페이지 리스트가 있을 경우
	    if(totalPageList>pageList){
	       
	        pageInner+="<a class='next' href='javascript:nextPage("+totalSize+")'><img src='nextPage.gif'></a>";
	        pageInner+="<a class='last' href='javascript:lastPage("+totalPage+")'><img src='lastPage.gif'></a>";
	    }
	    // 현재 페이지리스트가 마지막 페이지 리스트일 경우
	    if(totalPageList==pageList){
	       
	        pageInner+="<img src='nextPage2.gif'>";
	        pageInner+="<img src='lastPage2.gif'>";
	    }  
	    //alert(pageInner);
	    // 페이징할 DIV태그에 우선 내용을 비우고 페이징 태그삽입
	    $("#paginate").html("");
	    $("#paginate").append(pageInner);
	   
	}
	
	// 그리드 첫페이지로 이동
	function firstPage(){
	       
	        $grid.jqGrid('setGridParam', {
	                            page:1
	                        }).trigger("reloadGrid");
	       
	}
	// 그리드 이전페이지 이동
	function prePage(totalSize){
	       
	        var currentPage = $('#list').getGridParam('page');
	        var pageCount = 10;
	       
	        currentPage-=pageCount;
	        pageList=Math.ceil(currentPage/pageCount);
	        currentPage=(pageList-1)*pageCount+pageCount;
	       
	        initPage("gridId",totalSize,currentPage);
	       
	        $grid.jqGrid('setGridParam', {
	                            page:currentPage
	                        }).trigger("reloadGrid");
	       
	}
	// 그리드 다음페이지 이동    
	function nextPage(totalSize){
	       
	        var currentPage = $('#list').getGridParam('page');
	        var pageCount = 10;
	       
	        currentPage+=pageCount;
	        pageList=Math.ceil(currentPage/pageCount);
	        currentPage=(pageList-1)*pageCount+1;
	       
	        initPage("gridId",totalSize,currentPage);
	       
	        $grid.jqGrid('setGridParam', {
	                            page:currentPage
	                        }).trigger("reloadGrid");
	}
	// 그리드 마지막페이지 이동
	function lastPage(totalSize){
	       
	        $grid.jqGrid('setGridParam', {
	                            page:totalSize
	                        }).trigger("reloadGrid");
	}
	// 그리드 페이지 이동
	function goPage(num){
	       
	        $grid.jqGrid('setGridParam', {
	                            page:num
	                        }).trigger("reloadGrid");
	       
	}



	
   

})


