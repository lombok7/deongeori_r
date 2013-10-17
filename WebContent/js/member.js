/*member.js*/
/* 기본 회원정보 체크 */
function join_check(){
	if($.trim($("#email").val())==""){
		alert("이메일 주소를 입력하세요!");
		$("#email").val("").focus();
		return false;
	}
	if($.trim($("#nick").val())==""){
		alert("회원닉네임을 입력하세요!");
		$("#nick").val("").focus();
		return false;
	}
	if($.trim($("#name").val())==""){
		alert("회원이름을 입력하세요!");
		$("#name").val("").focus();
		return false;
	}
	if($.trim($("#join-pass").val())==""){
		alert("비밀번호를 입력하세요!");
		$("#join-pass").val("").focus();
		return false;
	}
	if($.trim($("#join-pass2").val())==""){
		alert("비밀번호확인을 입력하세요!");
		$("#join-pass2").val("").focus();
		return false;
	}
	if($.trim($("#join-pass").val()) 
			!= $.trim($("#join-pass2").val())){
		alert("비밀번호가 일치하지 않습니다!");
		$("#join-pass").val("");//비번 입력창 초기화
		$("#join-pass2").val("");
		$("#join-pass").focus();//비번입력창으로 초기화
		return false;
	}
	if(($("#cfe").val()=="n") || ($("#cfn").val()=="n")){
		alert("중복체크를 완료하세요.");
		return false;
	}	
	
	
}

/*아이디 검사 및 중복체크 */
function id_check(){
	/*$("#idcheck").hide();//아이디 영역을 숨긴다.*/
	var memid=$("#email").val();
	//1.입력글자 길이 체크
	
	if($.trim($("#email").val()).length < 1){
		var newtext='<font color="red">이메일주소를 입력하세요.</font>';
		$("#idcheck").text('');//아이디 영역을 초기화
		$("#idcheck").show();//아이디 영역을 보이게 한다.
		$("#idcheck").append(newtext);//idcheck 영역에 문자열을
		//추가
		$("#mjjang_id").val('').focus();
		return false;
	};
	/*
	if($.trim($("#mjjang_id").val()).length > 12){
		var newtext='<font color="red">아이디는 12자 이하이어야 합니다.</font>';
		$("#idcheck").text('');//아이디 영역을 초기화
		$("#idcheck").show();//아이디 영역을 보이게 한다.
		$("#idcheck").append(newtext);//idcheck 영역에 문자열을
		//추가
		$("#mjjang_id").val('').focus();
		return false;
	};
	//2.입력글자 확인
	if(!(validate_userid(memid))){//유효성검증
		var newtext='<font color="red">아이디는 영문소문자,숫자,_ 조합만 가능합니다.</font>';
		 $("#idcheck").text('');
		  $("#idcheck").show();
		  $("#idcheck").append(newtext);
		  $("#mjjang_id").val('').focus();
		  return false;
	};*/
	
	//아이디 중복확인
    $.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
        type:"POST",//자료를 보내는 법
        url:"./member/member_idcheck.jsp", //서버 파일 주소 와 경로   
        data: {"memid":memid},  //memid피라미터에 변수값을 저장해서넘김
        datatype:"post",//서버 타입을 post 타입으로 받아옴.
        success: function (data) {//서버 결과값은 data변수에 저장
      	  if(data==1){//중복 이면
      		var newtext='<font color="red">중복 이메일</font>';
      		$("#idcheck").text('');
        	$("#idcheck").show();
        	$("#idcheck").append(newtext);
          	$("#email").val('').focus();
          	return false;
	     
      	  }else{//중복이 아니면
      		var newtext='<font color="blue">사용 가능 이메일</font>';
      		$("#idcheck").text('');
      		$("#idcheck").show();
      		$("#idcheck").append(newtext);
      		$("#nick").focus();
      		$("#cfe").val('y');
      	  }  	    	  
        },
    	  error:function(){
    		  alert("data error");
    	  }
      });//$.ajax	
};
/*아이디 중복 체크 끝*/

function nick_check(){
	/*$("#nickcheck").hide();//아이디 영역을 숨긴다.*/
	var memnick=$("#nick").val();
	//1.입력글자 길이 체크
	
	if($.trim($("#nick").val()).length < 3){
		var newtext='<font color="red">닉네임은 3자 이상이어야 합니다.</font>';
		$("#nickcheck").text('');//아이디 영역을 초기화
		$("#nickcheck").show();//아이디 영역을 보이게 한다.
		$("#nickcheck").append(newtext);//idcheck 영역에 문자열을
		//추가
		$("#nick").val('').focus();
		return false;
	};
	if($.trim($("#nick").val()).length > 7){
		var newtext='<font color="red">닉네임은 7자 이하이어야 합니다.</font>';
		$("#nickcheck").text('');//아이디 영역을 초기화
		$("#nickcheck").show();//아이디 영역을 보이게 한다.
		$("#nickcheck").append(newtext);//idcheck 영역에 문자열을
		//추가
		$("#nick").val('').focus();
		return false;
	};
	/*
	//2.입력글자 확인
	if(!(validate_userid(memid))){//유효성검증
		var newtext='<font color="red">아이디는 영문소문자,숫자,_ 조합만 가능합니다.</font>';
		 $("#idcheck").text('');
		  $("#idcheck").show();
		  $("#idcheck").append(newtext);
		  $("#mjjang_id").val('').focus();
		  return false;
	};*/
	
	//아이디 중복확인
    $.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
        type:"POST",//자료를 보내는 법
        url:"./member/member_nickcheck.jsp", //서버 파일 주소 와 경로   
        data: {"memnick":memnick},  //memid피라미터에 변수값을 저장해서넘김
        datatype:"post",//서버 타입을 post 타입으로 받아옴.
        success: function (data) {//서버 결과값은 data변수에 저장
      	  if(data==1){//중복 이면
      		var newtext='<font color="red">중복 닉네임</font>';
      		$("#nickcheck").text('');
        		$("#nickcheck").show();
        		$("#nickcheck").append(newtext);
          		$("#nick").val('').focus();
          		return false;
	     
      	  }else{//중복이 아니면
      		var newtext='<font color="blue">사용 가능 닉네임</font>';
      		$("#nickcheck").text('');
      		$("#nickcheck").show();
      		$("#nickcheck").append(newtext);
      		$("#name").focus();
      		$("#cfn").val('y');
      	  }  	    	  
        },
    	  error:function(){
    		  alert("data error");
    	  }
      });//$.ajax	
};

function validate_userid(memid)
{
  var pattern= new RegExp(/^[a-z0-9_]+$/);//영문소문자,숫자와_
  //만 아이디 입력창에 입력가능하게 한다.
  return pattern.test(memid);
};

function edit_pwd_check(id){
	var pwd=$("#edit_pwd").val();
	
	$.ajax({//$는  jQuery. 즉 jQuery로 아작스를 실행
        type:"POST",//자료를 보내는 법
        url:"./member/member_edit_pwd_check.jsp", //서버 파일 주소 와 경로   
        data: {"pwd":pwd,"id":id},  //memid피라미터에 변수값을 저장해서넘김
        datatype:"json",
        success: function (data) {//서버 결과값은 data변수에 저장
        	if(data.length>14){
        	json = eval("(" + data + ")");
            $.each(json.member, function(key,value){
            $("#dng_mem_email").val(value.email);
    		$("#dng_mem_nickname").val(value.nickname);
    		$("#dng_mem_profileimg").val(value.profileimg);
        	$("#cfp").val("y");
        });
        	}else{
        		alert("비밀번호가 틀렸습니다.");
        		$("#edit_pwd").val("").focus();
        	}
	},
	  error:function(){
		  alert("data error");
	  }
});

}


function edit_check(){
	if($("#cfp").val()=="n"){
		alert("비밀번호체크를 완료하세요.");
		return false;
	}
}

function imgPreview2(input) {    
    // 원칙적으로 보안상 로컬 파일 접근은 불허함.
    // HTML5 File API
    // File interface 를 사용하면  파일 이름이나  크기 등 기본적인 정보에 접근 할 수 있습니다.
    // File Reader interface는 파일의 내용을 읽을 수 있는 기능을 제공합니다.
    // 그럼. 보안은?
    // File interface는 Browser가 막 건드릴 수 있는 것이 아니라 
    // 파일 선택 폼이나  Drag & Drop을 통해서 사용자가 직접 선택한 파일에 한정.
    // 지원 브라우저 : chrome, firefox.
     
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        
        // 읽어들이기에 성공 했을 때 호출하는  event handler. load event 에 대응.
        reader.onload = function(e) {
            $("#pfimg").attr("src",e.target.result);
        };
        
        // readAsDataURL(file) : File 내용을 읽어 dataURL 형식의 문자열로 저장.
        reader.readAsDataURL(input.files[0]);
    }
}