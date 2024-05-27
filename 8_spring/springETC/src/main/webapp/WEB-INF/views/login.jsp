<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
    // 네이버 로그인 관련 설정
    const clientId = "m1hCzwCK66Nrt_XkxJfr";
    const redirectURI = encodeURIComponent("http://localhost:8899/etc/naver-login");
    
    // SecureRandom 대신 JavaScript의 random 함수를 사용하여 state 값을 생성
    function generateState() {
        return Math.random().toString(36).substr(2);
    }
    const state = generateState();
    
    // API URL 구성
    const apiURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=`+ 
    		clientId + `&redirect_uri=` + redirectURI + `&state=`+ state;

    // state 값을 서버에 저장 (예: 로컬 스토리지 또는 쿠키)
    localStorage.setItem("state", state);

    // DOM 로드 후 로그인 링크 생성
    document.addEventListener("DOMContentLoaded", function() {
        const loginLink = document.getElementById("naverLoginLink");
        loginLink.href = apiURL;
    });
</script>

<!-- 네이버 로그인 아이콘에 a태그로 apiURL(https://nid.naver.com/oauth2.0/authorize?response_type=code)로 이동할 수 있게 함 -->
<a id="naverLoginLink"><img style="width: 100%; height: 50px;" src="resources/images/login_naver.png" alt="네이버 로그인 아이콘"></a>
</body>
</html>