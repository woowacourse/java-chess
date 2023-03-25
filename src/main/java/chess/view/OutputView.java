package chess.view;

import chess.domain.game.Camp;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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
        if (piece.getClass() == Bishop.class) {
            return "b";
        }
        if (piece.getClass() == King.class) {
            return "k";
        }
        if (piece.getClass() == Knight.class) {
            return "n";
        }
        if (piece.getClass() == Pawn.class) {
            return "p";
        }
        if (piece.getClass() == Queen.class) {
            return "q";
        }
        if (piece.getClass() == Rook.class) {
            return "r";
        }
        return ".";
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
