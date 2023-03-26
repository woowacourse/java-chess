package chess.view;

import chess.domain.game.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class OutputView {
    private static final int BOARD_LINE_SIZE = 8;

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 상태 : status");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printChessBoard(final List<Piece> pieces) {
        for (int i = 1; i < pieces.size() + 1; i++) {
            final Piece piece = pieces.get(i - 1);
            String initial = getPieceInitial(piece);
            initial = upperIfBlack(piece, initial);
            System.out.print(initial);
            printNewLine(i);
        }
    }

    private static String getPieceInitial(final Piece piece) {
        if (piece.isSameCamp(Camp.WHITE)) {
            return piece.pieceType().whiteInitial();
        }
        if (piece.isSameCamp(Camp.BLACK)) {
            return piece.pieceType().blackInitial();
        }
        return PieceType.EMPTY.blackInitial();
    }

    private static String upperIfBlack(final Piece piece, String initial) {
        if (piece.camp() == Camp.BLACK) {
            initial = initial.toUpperCase();
        }
        return initial;
    }

    private static void printNewLine(final int i) {
        if (i % BOARD_LINE_SIZE == 0) {
            System.out.println();
        }
    }

    public static void printStatus(final double whiteScore, final double blackScore, final Camp winner) {
        System.out.println("White 진영 점수: " + whiteScore);
        System.out.println("Black 진영 점수: " + blackScore);
        System.out.println("게임 결과: " + winner.getName());
    }
}
