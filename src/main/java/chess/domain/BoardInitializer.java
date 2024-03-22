package chess.domain;

import java.util.HashMap;

public class BoardInitializer {

    private BoardInitializer() {}

    public static Board initialize() {
        Board board = new Board(new HashMap<>());
        initializeBlackTeam(board);
        initializeWhiteTeam(board);
        return board;
    }

    private static void initializeBlackTeam(Board board) {
        initializePawn(board, Row.RANK7, Color.BLACK);
        initializeHighValuePiece(board, Row.RANK8, Color.BLACK);
    }

    private static void initializeWhiteTeam(Board board) {
        initializePawn(board, Row.RANK2, Color.WHITE);
        initializeHighValuePiece(board, Row.RANK1, Color.WHITE);
    }

    private static void initializePawn(Board board, Row row, Color color) {
        for (Column column : Column.values()) {
            Position position = new Position(row, column);
            if (color == Color.WHITE) {
                board.putPiece(position, new Piece(PieceType.WHITE_PAWN, color));
                continue;
            }
            board.putPiece(position, new Piece(PieceType.BLACK_PAWN, color));
        }
    }

    private static void initializeHighValuePiece(Board board, Row row, Color color) {
        board.putPiece(new Position(row, Column.A), new Piece(PieceType.ROOK, color));
        board.putPiece(new Position(row, Column.H), new Piece(PieceType.ROOK, color));

        board.putPiece(new Position(row, Column.B), new Piece(PieceType.KNIGHT, color));
        board.putPiece(new Position(row, Column.G), new Piece(PieceType.KNIGHT, color));

        board.putPiece(new Position(row, Column.C), new Piece(PieceType.BISHOP, color));
        board.putPiece(new Position(row, Column.F), new Piece(PieceType.BISHOP, color));

        board.putPiece(new Position(row, Column.D), new Piece(PieceType.QUEEN, color));
        board.putPiece(new Position(row, Column.E), new Piece(PieceType.KING, color));
    }
}
