package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

import java.util.*;
import java.util.stream.Collectors;

public class CurrentPieces {
    private List<Piece> currentPieces;

    public CurrentPieces(List<Piece> currentPieces) {
        this.currentPieces = new ArrayList<>(currentPieces);
    }

    public static CurrentPieces generate() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(King.generate());
        pieces.addAll(Knight.generate());
        pieces.addAll(Queen.generate());
        pieces.addAll(Rook.generate());
        pieces.addAll(Bishop.generate());
        pieces.addAll(Pawn.generate());
        return new CurrentPieces(pieces);
    }

    public List<Piece> getCurrentPieces() {
        return currentPieces;
    }

    public Piece findByPosition(Position position) {
        return currentPieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findFirst()
                .orElse(Empty.EMPTY);
    }

    public boolean isAliveAllKings() {
        return (int) currentPieces.stream().filter(piece -> piece instanceof King).count() == 2;
    }

    public double sumScoreByColor(Color color) {
        Map<Object, Long> pawns = collectPawnCountsByRow(color);
        int count = (int) pawns.keySet().stream()
                .filter(character -> pawns.get(character) >= 2)
                .mapToLong(character -> pawns.get(character))
                .sum();
        return currentPieces.stream()
                .filter(piece -> piece.getColor().isSame(color))
                .mapToDouble(piece -> piece.getScore().getValue())
                .sum() - (count * 0.5);
    }

    private Map<Object, Long> collectPawnCountsByRow(Color color) {
        return currentPieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .filter(piece -> piece.getColor().isSame(color))
                .collect(Collectors.groupingBy(piece -> piece.getPosition().getX(), Collectors.counting()));
    }

    public void removePieceIfNotEmpty(Piece targetPiece) {
        if (!(targetPiece instanceof Empty)) {
            currentPieces.remove(targetPiece);
        }
    }

    public boolean hasSamePositionPiece(Position target) {
        return currentPieces.stream()
                .anyMatch(piece -> piece.isSamePosition(target));
    }
}
