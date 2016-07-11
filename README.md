# jwp-basic
자바 웹 프로그래밍 기본 실습


# 160710__수업준비
서블릿 Class 작성패턴
// public class 작성 
// javax.servlet.HttpServlet 상속
// No-Argument 생성자 필요
// service 메소드 구현 (doGet, doPost)
// web.xml 에 등록, Servlet 3.0 이후부터 @WebServlet 어노테이션 사용 (url 매핑)

WebServerLauncher 에서 DataBase 더미데이터 셋팅이 안먹히는 이유??
사용자추가 화면에서 한글깨짐 현상 해결방법??
(list.jsp) 인덱스 변수 처리 방법

Redirect 와 Forward(Dispatch) 방식 차이??
http://gudghks0825.blog.me/220617456448
// Redirect 은 url 변경됨. 브라우저에 url 입력으로 이동하는 것과 같은 형태로써 request, response 객체는 새롭게 생기면서 이동한다.
// Forwarding 은 브라우저 상에서 url 이 변하지 않음. 웹컨테이너 내부에서 페이지 이동
http://blog.naver.com/nara0617/220757459723
// Forward 는 데이터를 내부에서 응답하는 jsp  에게 넘기는 작업
// Redirect 는 클라이언트에게 요청을 넘길 url 을 알려주오 다시 요청하게 하는 방식

Attribute 개념??
// Servlet 간 공유하는 객체. 실제는 헤더에 추가되는 내용인지??
// Request Scope		: HttpServletRequest, Request 살아있는 동안 공유  
// Session Scope		: HttpSession, 하나의 Client(WebBrowser)가 살아있는 동안 공유
// Application Scope	: ServletContext, Application이 시작해서 종료할때까지 공유

ServletConfig
// Servlet 객체의 설정정보
// WebContainer 가 생성하여 Servlet 객체 init() 호출시 주입
// Servlet 객체당 하나씩 생성
ServletContext
// WebApplication 의 설정정보
// WebApplication 이 처음 실행되는 시점에 WebContainer 가 생성
// WebApplication 당 하나씩 생성

servlet->web.xml->init parameter
// 변경가능성 있는 문자열을 web.xml 에 설정해놓고 Servlet/JSP 에서 호출해서 사용
// 소스코드 변경없이 web.xml 수정만으로 서버의 동작수정 가능


#160711__수업
서블릿의 예약된 디렉토리
// ./WEB-INF, ./WEB-INF/classes, ./WEB-INF/lib, (./WEB-INF/web.xml)
// 브라우저를 통해 접근 불가한 디렉토리 (Private), 숨길 파일들을 여기에 위치시킨다.
서블릿의 redirect, forward
// redirect (302 응답)
빈, EL
// EL 에서 객체 . 으로 접근하는 것은 객체에 접근하는것이 아니라 Getter/Setter 에만 접근 하는 것이다.
Let's Encrypt (https://letsencrypt.org)
// 무료로 ssl 인증서 제공, 3개월마다 갱신해야함, Chrome 스케줄로 갱신(?)
SetAttribute, GetAttribute
// 데이터를 공유하기 위해서는 Forward 방식을 사용해야함
// 서버 자체적으로 페이지를 옮기기 때문에 Attribute 를 서로 다른 jsp 에서 유지할수 있으나
// 클라이언트를 한번 갔다오는 Redirect 방식일 경우 유지할 수 없다. (무상태 프로토콜)
jsp 의 문제점 (html 에 java 코드 닮겨있는 형태)
// 디버깅 힘들다. 테스트코드 사용 안됨. 오직 수동테스트
// 실행을 해봐야 문제가 있는지 안다
// 코드분석도 힘들다.
MVC
// 따라서 mvc 가 나왔음