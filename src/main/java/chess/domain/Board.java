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
        initializePawn(Column.RANK7, Color.BLACK);
        initializeHighValuePiece(Column.RANK8, Color.BLACK);
    }

    private void initializeWhiteTeam() {
        initializePawn(Column.RANK2, Color.WHITE);
        initializeHighValuePiece(Column.RANK1, Color.WHITE);
    }

    private void initializePawn(Column column, Color color) {

        for (Row row : Row.values()) {
            Position position = new Position(row, column);
            if (color == Color.WHITE) {
                board.put(position, new Piece(PieceType.WHITE_PAWN, color));
                continue;
            }
            board.put(position, new Piece(PieceType.BLACK_PAWN, color));
        }
    }

    private void initializeHighValuePiece(Column column, Color color) {
        board.put(new Position(Row.a, column), new Piece(PieceType.ROOK, color));
        board.put(new Position(Row.h, column), new Piece(PieceType.ROOK, color));

        board.put(new Position(Row.b, column), new Piece(PieceType.KNIGHT, color));
        board.put(new Position(Row.g, column), new Piece(PieceType.KNIGHT, color));

        board.put(new Position(Row.c, column), new Piece(PieceType.BISHOP, color));
        board.put(new Position(Row.f, column), new Piece(PieceType.BISHOP, color));

        board.put(new Position(Row.d, column), new Piece(PieceType.QUEEN, color));
        board.put(new Position(Row.e, column), new Piece(PieceType.KING, color));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
