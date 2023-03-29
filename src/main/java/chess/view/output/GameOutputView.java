package chess.view.output;

import chess.domain.board.GameResult;
import chess.domain.piece.Color;

public class GameOutputView {
    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
    }

    public void printCommands(final String user, final String room) {
        System.out.println("[로그인 한 유저: " + user + ", 선택한 방 이름: " + room + "]");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예) move b2 b3");
        System.out.println("> 게임 상태 : status");
        System.out.println("> 게임 종료 : end");
    }

    public void printBoard(final GameResult result) {
        System.out.println(BoardConverter.convert(result.getBoard()));
    }

    public void printStatus(final GameResult result) {
        System.out.println("흰색 점수: " + result.score(Color.WHITE));
        System.out.println("검은색 점수: " + result.score(Color.BLACK));
        System.out.println("현재 상태: " + generateWinnerMessage(result));
    }

    private String generateWinnerMessage(final GameResult result) {
        final Color winner = result.winner();
        if (winner == Color.WHITE) {
            return "백색 승";
        }
        if (winner == Color.BLACK) {
            return "검은색 승";
        }
        return "무승부";
    }

    public void printGameEnd() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
