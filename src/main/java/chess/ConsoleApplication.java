package chess;

import chess.controller.ChessController;

public class ConsoleApplication {
    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }
}

// TODO: 테스트 코드작성, 턴 제한, status 명령, / 게임 종료 조건, 최종 점수 계산
// TODO BONUS :  폰 테스트케이스 추가