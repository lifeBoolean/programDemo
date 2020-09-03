$(document).ready(function(){
	
	$("#memberSubmit").click(function(){
		if($("#userId").val() == "") {
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
	})
	
	
	
	

	
})


var obj = {"userId": "kim", "userName": "hans"}; 
	function test() {
		$.ajax({ 
			url: "test",
			type: "post", 
			data: JSON.stringify(obj), 
			dataType: "json", 
			contentType: "application/json", 
			success: function(data) { 
				console.log(data); 
			}, 
			error: function(errorThrown) { 
				console.log(errorThrown.statusText);
			} 
		});
	}
