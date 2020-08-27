$(document).ready(function(){
	
	$("#memberSubmit").click(function(){
		if($("#userId").val() == "") {
			alert("아이디를 입력해주세요.");
			$("#userId").focus();
			return false;
		}
	})
	
})