package chess.view.output;

public class MainOutputView {

    public void printCommands(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 계정 관리 : user");
        System.out.println("> 방 관리 : room");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 종료 : end");
    }

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
