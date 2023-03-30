package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;

public class PieceFactory {

    private static final Piece WHITE_KING = new King(Team.WHITE);
    private static final Piece BLACK_KING = new King(Team.BLACK);
    private static final Piece WHITE_QUEEN = new Queen(Team.WHITE);
    private static final Piece BLACK_QUEEN = new Queen(Team.BLACK);
    private static final Piece WHITE_ROOK = new Rook(Team.WHITE);
    private static final Piece BLACK_ROOK = new Rook(Team.BLACK);
    private static final Piece WHITE_BISHOP = new Bishop(Team.WHITE);
    private static final Piece BLACK_BISHOP = new Bishop(Team.BLACK);
    private static final Piece WHITE_KNIGHT = new Knight(Team.WHITE);
    private static final Piece BLACK_KNIGHT = new Knight(Team.BLACK);
    private static final Piece WHITE_PAWN = new Pawn(Team.WHITE);
    private static final Piece BLACK_PAWN = new Pawn(Team.BLACK);
    private static final Piece EMPTY = new Empty(Team.EMPTY);

    public static Map<Position, Piece> createPieces() {
        Map<Position, Piece> board = new HashMap<>();

        setWhite(board);
        setPawn(board, 2, WHITE_PAWN);
        setBlack(board);
        setPawn(board, 7, BLACK_PAWN);
        setEmpty(board);

        return board;
    }

    private static void setWhite(Map<Position, Piece> board) {
        board.put(new Position(1, 1), WHITE_ROOK);
        board.put(new Position(1, 8), WHITE_ROOK);
        board.put(new Position(1, 2), WHITE_KNIGHT);
        board.put(new Position(1, 7), WHITE_KNIGHT);
        board.put(new Position(1, 3), WHITE_BISHOP);
        board.put(new Position(1, 6), WHITE_BISHOP);
        board.put(new Position(1, 4), WHITE_QUEEN);
        board.put(new Position(1, 5), WHITE_KING);
    }

    private static void setBlack(Map<Position, Piece> board) {
        board.put(new Position(8, 1), BLACK_ROOK);
        board.put(new Position(8, 8), BLACK_ROOK);
        board.put(new Position(8, 2), BLACK_KNIGHT);
        board.put(new Position(8, 7), BLACK_KNIGHT);
        board.put(new Position(8, 3), BLACK_BISHOP);
        board.put(new Position(8, 6), BLACK_BISHOP);
        board.put(new Position(8, 4), BLACK_QUEEN);
        board.put(new Position(8, 5), BLACK_KING);
    }

    private static void setPawn(Map<Position, Piece> board, int rank, Piece pawn) {
        for (int file = 1; file <= 8; file++) {
            board.put(new Position(rank, file), pawn);
        }
    }

    private static void setEmpty(Map<Position, Piece> board) {
        for (int rank = 3; rank <= 6; rank++) {
            iterateFile(board, rank);
        }
    }

    private static void iterateFile(Map<Position, Piece> board, int rank) {
        for (int file = 1; file <= 8; file++) {
            board.put(new Position(rank, file), EMPTY);
        }
    }
}
