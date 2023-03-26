package chess.view.output;

public class UserOutputView {

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printRegisterSuccess(final String name) {
        System.out.println(name + " 회원 가입 완료");
    }

    public void printLoginSuccess(final String name) {
        System.out.println(name + " 로그인 완료!");
    }
}
