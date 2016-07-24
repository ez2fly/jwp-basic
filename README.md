#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 
서블릿 전체의 초기화를 담당하는 ContextLoaderListener 의 contextInitialized 호출됨
초기화 함수 내에서 db 생성쿼리를 통해 db 생성
이후 각 서블릿에서 init 호출, 현재 프로젝트에서는 서블릿이 DispatcherServlet 하나이므로 이 서블릿의 init() 호출됨
init() 내에서 컨트롤러의 url 맵핑을 담당하는 RequestMapping 인스턴스 생성 및 initMappting() 호출됨
initMapping() 내에서 각 컨트롤러 인스턴스 생성 및 url 맵핑 하여 멤버 Map 변수에 저장 (+생성자 호출)
#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 
기본 url 로 호출시 기본으로 "/" url 을 찾게 됨
현재 RequestMapping 의 url 맵핑을 보면 "/" 맵핑은 HomeController 임
HomeController 에서는 index.jsp 를 forward 
forward 시에 db 에서 Question 정보를 모두 가져와 attribute 로 넘김
index.jsp 에서는 넘겨받은 attribute 정보를 통해 질문목록을 보여줌
#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 
현재 MVC 모델로 서블릿이 하나의 인스턴스 이다.
ShowController 도 하나의 인스턴스로 있는 상태에서 그 인스턴스의 멤벼변수도 마찬가지로 하나인데,
멀티스레드 상에서 처리된다면 ShowController 의 멤버변수의 상태값에 대한 독립성을 보장하지 못한다.
예를들어 멀티스레드에서 공유되는 변수로써 동기화처리가 필요한 상태가 되야 하는데 그게 처리 안되어 있음.(mutex 등으로 하나의 스레드만 접근하도록 하는 처리)
따라서 멤버변수를 실제 작동하는(별개의 스레드) execute() 함수 내에서 개별로 생성하여 처리한다.
(어차피 코드의 흐름상 상태값을 가지고 있을 필요가 없는 변수라 지역변수로 처리한다.)
