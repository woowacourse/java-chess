package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ChessBoard {

    private final Map<Position, Piece> pieces;

    public ChessBoard(Map<Position, Piece> pieces) {
        Objects.requireNonNull(pieces, "pieces는 null이 들어올 수 없습니다.");
        this.pieces = new HashMap<>(pieces);
    }

    public static ChessBoard createNewChessBoard() {
        return new ChessBoard(PieceFactory.createNewChessBoard());
    }

    public void movePiece(Position start, Position target) {
        Piece movedPiece = pieceByPosition(start).move(start, target, this);
        pieces.remove(start);
        pieces.put(target, movedPiece);
    }

    public boolean isPositionEmpty(Position position) {
        return !pieces.containsKey(position);
    }

    public Piece pieceByPosition(Position position) {
        if (!pieces.containsKey(position)) {
            throw new NoSuchElementException("해당 위치에 존재하는 기물이 없습니다.");
        }
        return pieces.get(position);
    }

    public Map<Color, Double> calcualteScoreStatus() {
        Map<Color, Double> result = new EnumMap<>(Color.class);

        for (Color color : Color.values()) {
            result.put(color, calculateColorScore(color));
        }
        return result;
    }

    private double calculateColorScore(Color color) {
        return calculateColorDefaultScore(color) - 0.5 * countDuplicatedPawn(color);
    }

    private double calculateColorDefaultScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private int countDuplicatedPawn(Color color) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameColor(color) && entry.getValue().isPawn())
                .filter(entry -> isSameColumnPawn(entry.getKey()))
                .count();
    }

    private boolean isSameColumnPawn(Position position) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isPawn())
                .anyMatch(entry -> !entry.getKey().equals(position) && entry.getKey().equalsColumn(position));
    }

    public Map<Position, Piece> getPieces() {
        return Map.copyOf(pieces);
    }
}
