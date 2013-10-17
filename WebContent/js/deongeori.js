function check() {
  if ($.trim($("#dng_name").val()) == "") {
      alert("덩어리 이름을 입력하세요!");
      $("#dng_name").focus().val("");
      return false;
  }
  
  if ($.trim($("#dng_pwd").val()) == "") {
      alert("덩어리 비밀번호를 입력하세요!");
      $("#dng_pwd").focus().val("");
      return false;
  }
}

function imgPreview(input) {    
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
            $("#dngimg").attr("src",e.target.result);
        };
        
        // readAsDataURL(file) : File 내용을 읽어 dataURL 형식의 문자열로 저장.
        reader.readAsDataURL(input.files[0]);
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

