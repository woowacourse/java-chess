package chess.view;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
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
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                Piece piece = board.findPieceBy(Position.withFileAndRank(file, rank)).orElse(null);
                System.out.print(PieceOutputText.of(piece));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printStatus(StatusResult status) {
        System.out.println("블랙 진영의 점수는 " + status.getBlackScore().get() + "입니다.");
        System.out.println("화이트 진영의 점수는 " + status.getWhiteScore().get() + "입니다.");

        printWinner(status);
    }

    private static void printWinner(StatusResult status) {
        double whiteScore = status.getWhiteScore().get();
        double blackScore = status.getBlackScore().get();

        if (whiteScore > blackScore) {
            System.out.println("블랙 진영이 이기고 있습니다.");
        }
        if (whiteScore < blackScore) {
            System.out.println("화이트 진영이 이기고 있습니다.");
        }
        System.out.println("무승부입니다.");
    }
}
