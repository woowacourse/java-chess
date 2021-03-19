package chess.domain.piece;

import chess.domain.Color;

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

    public void removePieceByPosition(Position target) {
        Piece piece = findByPosition(target);
        if (piece instanceof Empty) {
            throw new IllegalArgumentException("[ERROR] target에 제거하고자 하는 기물이 없습니다.");
        }
        currentPieces.remove(piece);
    }

    public boolean isAliveAllKings() {
        return 2 == (int) currentPieces.stream().filter(piece -> piece instanceof King).count();
    }


    public double sumScoreByColor(Color color) {
        int count = 0;
        for (int i = 0; i < Position.ROW.length(); i++) {
            int index = i;
            int temp = (int) currentPieces.stream()
                    .filter(piece -> piece instanceof Pawn)
                    .filter(piece -> piece.getColor().isSame(color))
                    .filter(piece -> piece.getPosition().getX() == Position.ROW.charAt(index))
                    .count();
            if (temp >= 2) {
                count += temp;
            }
        }
        double subtractCount = 0.5;

        return currentPieces.stream()
                .filter(piece -> piece.getColor().isSame(color))
                .mapToDouble(piece -> piece.getScore().getValue())
                .sum() - (count * subtractCount);
    }
}
