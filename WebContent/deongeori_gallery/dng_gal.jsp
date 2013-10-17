<%@ page contentType="text/html; charset=UTF-8"%>
<form method="post" action="dng_gal_ok.do">
<div id="article">
  
  <div id="article_c">
    <h2 class="gallery_title">게시물 글쓰기</h2>
    <table id="gallery_t">   
     <tr>
        <th> 글내용 </th>
        <td> 
           <textarea name="dng_gal_content" id="dng_gal_content"
              class="input_box" rows="8" cols="50"></textarea>
           <!-- textarea 태그는 여러 줄 입력상자를 만들어준다.
                rows 속성은 행, cols 속성은 열을 만듬 -->   
        </td>
     </tr> 
     <tr>
        <th> 사진검색 </th>
        <td><a href="thumbnail.jsp"></a> <input type="file" name="dng_gal_img" 
              id="dng_gal_img"/><p>
        </td>
     </tr>
     <tr>
        <th> 비밀번호 </th>
        <td> <input type="password" name="dng_gal_pwd" 
              id="dng_gal_pwd" class="input_box" size="14"/>
        </td>
     </tr>
    </table>    
     <div id="gallerywrite_menu">
     <input type="submit" value="입력" class="input_b" />
     &nbsp;
     <input type="reset" value="취소" class="input_b"
         onclick="$('#dng_gal_content').focus();" />
      <!-- [취소] 버튼 클릭 시 초기화 되면서 글쓴이 입력창으로 포커스 이동 -->   
     </div>   
     </div>
</div>
 </form> 
