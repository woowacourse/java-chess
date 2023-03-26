package chess.view.output;

import chess.domain.board.GameResult;
import chess.domain.piece.Color;

public class GameOutputView {
    private static final String NEXT_LINE = System.lineSeparator();

    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
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

    public void printGameClear() {
        System.out.println("체스 게임을 초기화합니다." + NEXT_LINE);
        printGameStart();
    }

    public void printGameEnd() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public void printException(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
