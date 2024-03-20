package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        initialize();
    }

    private void initialize() {
        initializeBlackTeam();
        initializeWhiteTeam();
    }

    private void initializeBlackTeam() {
        initializePawn(Row.RANK7, Color.BLACK);
        initializeHighValuePiece(Row.RANK8, Color.BLACK);
    }

    private void initializeWhiteTeam() {
        initializePawn(Row.RANK2, Color.WHITE);
        initializeHighValuePiece(Row.RANK1, Color.WHITE);
    }

    private void initializePawn(Row row, Color color) {

        for (Column column : Column.values()) {
            Position position = new Position(row, column);
            if (color == Color.WHITE) {
                board.put(position, new Piece(PieceType.WHITE_PAWN, color));
                continue;
            }
            board.put(position, new Piece(PieceType.BLACK_PAWN, color));
        }
    }

    private void initializeHighValuePiece(Row row, Color color) {
        board.put(new Position(row, Column.a), new Piece(PieceType.ROOK, color));
        board.put(new Position(row, Column.h), new Piece(PieceType.ROOK, color));

        board.put(new Position(row, Column.b), new Piece(PieceType.KNIGHT, color));
        board.put(new Position(row, Column.g), new Piece(PieceType.KNIGHT, color));

        board.put(new Position(row, Column.c), new Piece(PieceType.BISHOP, color));
        board.put(new Position(row, Column.f), new Piece(PieceType.BISHOP, color));

        board.put(new Position(row, Column.d), new Piece(PieceType.QUEEN, color));
        board.put(new Position(row, Column.e), new Piece(PieceType.KING, color));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
