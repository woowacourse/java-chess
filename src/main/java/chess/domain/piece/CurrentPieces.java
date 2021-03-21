package chess.domain.piece;

import chess.domain.Color;

import java.util.*;

public class CurrentPieces {

    private List<Piece> currentPieces;

    public CurrentPieces(List<Piece> currentPieces) {
        this.currentPieces = new ArrayList<>(currentPieces);
    }

    public List<Piece> getCurrentPieces() {
        return currentPieces;
    }

    public Piece findByPosition(Position position) {
        return currentPieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findFirst()
                .orElse(new Empty());
    }

    public void removePieceByPosition(Position target) {
        Piece piece = findByPosition(target);
        currentPieces.remove(piece);
    }

    public boolean isAliveAllKings() {
        return 2 == (int) currentPieces.stream().filter(piece -> piece instanceof King).count();
    }


    public double sumScoreByColor(Color color) {
        int count = 0;
        for (int i = 0; i < Position.Xs.length(); i++) {
            int index = i;
            int temp = (int) currentPieces.stream()
                    .filter(piece -> piece instanceof Pawn)
                    .filter(piece -> piece.getColor().same(color))
                    .filter(piece -> piece.getPosition().getX() == Position.Xs.charAt(index))
                    .count();
            if (temp >= 2) {
                count += temp;
            }
        }
        double subtractCount = 0.5;
        return currentPieces.stream()
                .filter(piece -> piece.getColor().same(color))
                .mapToDouble(piece -> piece.getScore().getValue())
                .sum() - (count * subtractCount);
    }
}
