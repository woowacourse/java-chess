package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.game.Status;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class OutputView {
    private static final String STATUS_FORMAT = "WHITE: %.1f BLACK: %.1f - %s진영 승\n";

    public static void printGameStart() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public static void printBoard(Board board) {
        for (Rank rank : board.getReverseRanks()) {
            for (Piece piece : rank.getPieces()) {
                System.out.print(drawPieceByColor(piece));
            }
            System.out.println();
        }
    }

    private static String drawPieceByColor(Piece piece) {
        String shape = drawPiece(piece);
        if (piece.isBlack()) {
            shape = shape.toUpperCase();
        }
        return shape;
    }

    private static String drawPiece(Piece piece) {
        if (piece instanceof Pawn) {
            return "p";
        }
        if (piece instanceof Rook) {
            return "r";
        }
        if (piece instanceof Knight) {
            return "n";
        }
        if (piece instanceof Bishop) {
            return "b";
        }
        if (piece instanceof King) {
            return "k";
        }
        if (piece instanceof Queen) {
            return "q";
        }
        return ".";
    }

    public static void printStatus(Status status) {
        System.out.printf(STATUS_FORMAT, status.getWhiteScore(), status.getBlackScore(), status.winner());
    }
}
