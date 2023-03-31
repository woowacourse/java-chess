package chess.view.resposne;

import chess.controller.main.InitialOutput;

public class InitialOutputView implements InitialOutput {

    @Override
    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 로그인 : login 유저id - 예. login user1");
        System.out.println("> 게임 목록 : games");
        System.out.println("> 진행중인 게임 선택 : join 게임번호 - 예. join 1");
        System.out.println("> 게임 생성 : create");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
}
