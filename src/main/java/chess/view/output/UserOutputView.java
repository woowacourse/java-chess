package chess.view.output;

public class UserOutputView {
    public void printCommands(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 회원가입 : register 이름 - 예) register charlie");
        System.out.println("> 로그인 : login 이름 - 예) login charlie");
        System.out.println("> 로그아웃 : logout");
        System.out.println("> 메인 화면 : end");
    }

    public void printRegisterSuccess(final String name) {
        System.out.println(name + " 회원 가입 완료");
    }

    public void printLoginSuccess(final String name) {
        System.out.println(name + " 로그인 완료!");
    }

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
