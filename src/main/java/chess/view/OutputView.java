package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.MatchResult;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.command.StatusResult;
import chess.domain.piece.Piece;

public class OutputView {

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Board board) {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Piece piece = board.findPieceBy(new Position(column, row)).orElse(null);
                System.out.print(PieceOutputText.of(piece));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printStatus(StatusResult status) {
        System.out.println("블랙 진영의 점수는 " + status.getBlackScore().get() + "입니다.");
        System.out.println("화이트 진영의 점수는 " + status.getWhiteScore().get() + "입니다.");

        printWinner(findWinner(status));
    }

    private static MatchResult findWinner(StatusResult status) {
        double whiteScore = status.getWhiteScore().get();
        double blackScore = status.getBlackScore().get();

        if (whiteScore > blackScore) {
            return MatchResult.WHITE_WIN;
        }
        if (whiteScore < blackScore) {
            return MatchResult.BLACK_WIN;
        }
        return MatchResult.DRAW;
    }

    private static void printWinner(MatchResult result) {
        if (result.equals(MatchResult.BLACK_WIN)) {
            System.out.println("블랙 진영이 이기고 있습니다.");
        }
        if (result.equals(MatchResult.WHITE_WIN)) {
            System.out.println("화이트 진영이 이기고 있습니다.");
        }
        if (result.equals(MatchResult.DRAW)) {
            System.out.println("무승부입니다.");
        }
    }
}
