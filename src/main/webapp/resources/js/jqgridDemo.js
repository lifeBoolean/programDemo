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
            repeatitems:false
        },
        loadonce: true		// 설정해야 페이지를 넘김, 잘 작동함 꼭 설정,데이터 전체를 한번에 로딩

    });
	$("#List").jqGrid('setFrozenColumns');    ﻿﻿﻿
    $("#List").trigger('reloadGrid');
	
	
	
	// 삭제 로직
	$('#deleteBtn').click(function() {
		var id = $("#list").getGridParam('selarrrow');    // 멀티셀렉트에 선택된 행만 가져오기
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
			            
			            $("#List").trigger('reloadGrid');

			    	},
			        error:function(request,status,error){
			            console.log('code: '+request.status+"\n"+'message: '+request.responseText+"\n"+'error: '+error);
			        }
				   	   
                })
//				.done(function( msg ) {
//				    console.log("done");
//				    alert("s :" + textStatus);
//                    $("#list").jqGrid('setGridParam', {
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



	
   

})


