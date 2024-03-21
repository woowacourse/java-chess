package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        initialize();
    }

    public Board(Map<Position, Piece> board) {
        this.board = board;
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
        board.put(new Position(row, Column.A), new Piece(PieceType.ROOK, color));
        board.put(new Position(row, Column.H), new Piece(PieceType.ROOK, color));

        board.put(new Position(row, Column.B), new Piece(PieceType.KNIGHT, color));
        board.put(new Position(row, Column.G), new Piece(PieceType.KNIGHT, color));

        board.put(new Position(row, Column.C), new Piece(PieceType.BISHOP, color));
        board.put(new Position(row, Column.F), new Piece(PieceType.BISHOP, color));

        board.put(new Position(row, Column.D), new Piece(PieceType.QUEEN, color));
        board.put(new Position(row, Column.E), new Piece(PieceType.KING, color));
    }

    public void movePiece(Position from, Position to) {
        Piece piece = board.get(from);
        board.put(to, piece);
        board.remove(from);
    }

    public boolean hasPiece(Position position) {
        return board.containsKey(position);
    }

    public Piece findPieceByPosition(Position position) {
        if (hasPiece(position)) {
            return board.get(position);
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public boolean isEmptySpace(Position position) {
        return !hasPiece(position);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
