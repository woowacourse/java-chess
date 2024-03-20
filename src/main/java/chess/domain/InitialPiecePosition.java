package chess.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InitialPiecePosition {

    private final Map<Position, Piece> initialPiecePositions;

    public InitialPiecePosition() {
        Map<Position, Piece> initialPiecePositions = new HashMap<>();
        initialPiecePositions.putAll(getWhitePieces());
        initialPiecePositions.putAll(getBlackPieces());
        this.initialPiecePositions = initialPiecePositions;
    }

    private Map<Position, Piece> getWhitePieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.WHITE, 1));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.WHITE, 2));
        return initialWhitePiecePositions;
    }

    private Map<Position, Piece> getBlackPieces() {
        Map<Position, Piece> initialWhitePiecePositions = new HashMap<>();
        initialWhitePiecePositions.putAll(getNotPawnsPieces(Color.BLACK, 8));
        initialWhitePiecePositions.putAll(getPawnsPieces(Color.BLACK, 7));
        return initialWhitePiecePositions;
    }

    private Map<Position, Piece> getNotPawnsPieces(Color color, int row) {
        return Map.of(new Position(row, 1), new Piece(PieceType.ROOK, color),
                new Position(row, 2), new Piece(PieceType.KNIGHT, color),
                new Position(row, 3), new Piece(PieceType.BISHOP, color),
                new Position(row, 4), new Piece(PieceType.QUEEN, color),
                new Position(row, 5), new Piece(PieceType.KING, color),
                new Position(row, 6), new Piece(PieceType.BISHOP, color),
                new Position(row, 7), new Piece(PieceType.KNIGHT, color),
                new Position(row, 8), new Piece(PieceType.ROOK, color));
    }


    private Map<Position, Piece> getPawnsPieces(Color color, int row) {
        return Map.of(new Position(row, 1), new Piece(PieceType.PAWN, color),
                new Position(row, 2), new Piece(PieceType.PAWN, color),
                new Position(row, 3), new Piece(PieceType.PAWN, color),
                new Position(row, 4), new Piece(PieceType.PAWN, color),
                new Position(row, 5), new Piece(PieceType.PAWN, color),
                new Position(row, 6), new Piece(PieceType.PAWN, color),
                new Position(row, 7), new Piece(PieceType.PAWN, color),
                new Position(row, 8), new Piece(PieceType.PAWN, color));

    }

    public boolean isFirstMove(Position position, Piece piece) {
        Piece initialPiece = initialPiecePositions.getOrDefault(position, new Piece(PieceType.EMPTY, Color.NONE));
        return initialPiece.equals(piece);
    }

    public Map<Position, Piece> getInitialPiecePositions() {
        return Collections.unmodifiableMap(initialPiecePositions);
    }
}
