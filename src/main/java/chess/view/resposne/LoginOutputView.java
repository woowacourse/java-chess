package chess.view.resposne;

import chess.controller.user.LoginOutput;

public class LoginOutputView implements LoginOutput {

    @Override
    public void printLoginSuccess() {
        System.out.println("로그인에 성공했습니다");
        System.out.println("현재 유저의 게임들을 보려면 games 를 입력해주세요");
    }
}
