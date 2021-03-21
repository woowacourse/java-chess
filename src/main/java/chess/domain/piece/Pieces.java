package chess.domain.piece;

import chess.domain.Color;

import java.util.*;

public class Pieces {

    public static final int KING_COUNT = 2;
    private List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Piece findByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> position.equals(piece.getPosition()))
                .findFirst()
                .orElse(new Empty());
    }

    public void removePieceByPosition(Position target) {
        Piece piece = findByPosition(target);
        pieces.remove(piece);
    }

    public boolean isAliveAllKings() {
        return KING_COUNT == (int) pieces.stream()
                .filter(piece -> piece instanceof King)
                .count();
    }


    public double sumScoreByColor(Color color) {
        int count = 0;
        for (int i = 0; i < Position.Xs.length(); i++) {
            int index = i;
            int temp = (int) pieces.stream()
                    .filter(piece -> piece instanceof Pawn)
                    .filter(piece -> piece.isSameTeam(color))
                    .filter(piece -> piece.getPosition().getX() == Position.Xs.charAt(index))
                    .count();
            if (temp >= 2) {
                count += temp;
            }
        }
        double subtractCount = 0.5;
        return pieces.stream()
                .filter(piece -> piece.isSameTeam(color))
                .mapToDouble(piece -> piece.getScore().getValue())
                .sum() - (count * subtractCount);
    }


}
