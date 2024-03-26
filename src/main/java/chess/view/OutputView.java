package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.NoPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.slidingpiece.Bishop;
import chess.domain.piece.slidingpiece.Queen;
import chess.domain.piece.slidingpiece.Rook;
import chess.domain.position.Position;

public class OutputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";

    public void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.printf("> 게임 시작 : %s%n", START_COMMAND);
        System.out.printf("> 게임 종료 : %s%n", END_COMMAND);
        System.out.printf("> 게임 이동 : %s source위치 target위치 - 예. %s b2 b3%n", MOVE_COMMAND, MOVE_COMMAND);
    }

    public void printMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void printBoard(Board board) {
        for (int rank = 8; rank >= 1; rank--) {
            printOneRank(board, rank);
        }
        System.out.println();
    }

    private void printOneRank(Board board, int rank) {
        for (int file = 1; file <= 8; file++) {
            Piece piece = board.findPieceAt(Position.of(file, rank));
            System.out.print(pieceToString(piece));
        }
        System.out.println();
    }

    private String pieceToString(Piece piece) {
        String pieceText = "";
        if (piece instanceof Bishop) {
            pieceText = "B";
        }
        if (piece instanceof King) {
            pieceText = "K";
        }
        if (piece instanceof Knight) {
            pieceText = "N";
        }
        if (piece instanceof Pawn) {
            pieceText = "P";
        }
        if (piece instanceof Queen) {
            pieceText = "Q";
        }
        if (piece instanceof Rook) {
            pieceText = "R";
        }
        if (piece instanceof NoPiece) {
            pieceText = ".";
        }

        if (piece.isWhite()) {
            return pieceText.toLowerCase();
        }
        return pieceText;
    }
}
