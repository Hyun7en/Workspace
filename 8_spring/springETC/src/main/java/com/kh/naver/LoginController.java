package com.kh.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class LoginController {

	@RequestMapping("/naver")
	public String naverLogin() {
		return "login";
	}

	@RequestMapping("/naver-login")
	public String naverLoginCallback(HttpServletRequest request) {
		String clientId = "m1hCzwCK66Nrt_XkxJfr"; // 애플리케이션 클라이언트 아이디값
		String clientSecret = "VL6jGHORyT"; // 애플리케이션 클라이언트 시크릿값
		String code = request.getParameter("code");
		String state = request.getParameter("state");

		try {
			String redirectURI = URLEncoder.encode("http://localhost:8899/etc/naver-login", "UTF-8");
			String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
			apiURL += "client_id=" + clientId;
			apiURL += "&client_secret=" + clientSecret;
			apiURL += "&redirect_uri=" + redirectURI;
			apiURL += "&code=" + code;
			apiURL += "&state=" + state;

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();

			if (responseCode == 200) {
				// 정상적으로 정보를 받아왔을 때 result에 정보가 저장됨
				String result = res.toString();
				JsonObject totalObj = JsonParser.parseString(result).getAsJsonObject();
//				System.out.println(totalObj);
//				
//				System.out.println(totalObj.get("access_token"));
//				System.out.println(totalObj.get("access_token").getAsString());
//				System.out.println(totalObj.get("refresh_token"));
//				System.out.println(totalObj.get("token_type"));
//				System.out.println(totalObj.get("expires_in"));
				
				String token = totalObj.get("access_token").getAsString(); // 액세스토큰
				String header = "Bearer "+token;
				
				apiURL = "https://openapi.naver.com/v1/nid/me";
				
				Map<String, String> requestHeaders = new HashMap<String, String>();
				requestHeaders.put("Authorization", header);
				
				String responseBody = get(apiURL,requestHeaders);
				
				JsonObject memberInfo =  JsonParser.parseString(responseBody).getAsJsonObject();
				JsonObject resObj = memberInfo.getAsJsonObject("response");
				
				System.out.println(resObj);
				
				// 받아온 email과 데이터베이스의 email을 비교하여 가입유무 확인 후
				// 가입되어있다면 로그인, 아니라면 회원가입창으로 이동
				
//				Member isMember = memberService.checkMember(resObj.get("email").getAsString());
//				System.out.println(isMember);
//				
//				if(isMember != null) {
//					Member loginUser = memberService.loginMemberId(isMember);
//					
//					session.setAttribute("loginUser", loginUser);
//					return "redirect:/";
//				}
//				
//				session.setAttribute("memberInfo", responseBody);
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return "login";

	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");

			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
