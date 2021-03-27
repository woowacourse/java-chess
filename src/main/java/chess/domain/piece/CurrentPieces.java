package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Cross;
import chess.domain.piece.info.Position;

import java.util.*;
import java.util.stream.Collectors;

public class CurrentPieces {
    private static final String OBSTACLE_ERROR = "[ERROR] 기물을 뛰어 넘어 이동할 수 없습니다.";

    private List<Piece> currentPieces;

    public CurrentPieces(List<Piece> currentPieces) {
        this.currentPieces = new ArrayList<>(currentPieces);
    }

//    public static CurrentPieces generate() {
//        List<Piece> pieces = new ArrayList<>();
//        pieces.addAll(King.generate());
//        pieces.addAll(Knight.generate());
//        pieces.addAll(Queen.generate());
//        pieces.addAll(Rook.generate());
//        pieces.addAll(Bishop.generate());
//        pieces.addAll(Pawn.generate());
//        return new CurrentPieces(pieces);
//    }

    public List<Piece> getCurrentPieces() {
        return currentPieces;
    }

//    public Piece findByPosition(Position position) {
//        return currentPieces.stream()
//                .filter(piece -> position.equals(piece.getPosition()))
//                .findFirst()
//                .orElse(Empty.EMPTY);
//    }

    public boolean isAliveAllKings() {
        return (int) currentPieces.stream().filter(piece -> piece.isKing()).count() == 2;
    }

//    public double sumScoreByColor(Color color) {
//        Map<Character, Long> pawns = collectPawnCountsByRow(color);
//        int count = (int) pawns.keySet().stream()
//                .filter(character -> pawns.get(character) >= 2)
//                .mapToLong(character -> pawns.get(character))
//                .sum();
//        return currentPieces.stream()
//                .filter(piece -> piece.isSameColor(color))
//                .mapToDouble(piece -> piece.getScore().getValue())
//                .sum() - (count * 0.5);
//    }

//    private Map<Character, Long> collectPawnCountsByRow(Color color) {
//        return currentPieces.stream()
//                .filter(piece -> piece.isPawn())
//                .filter(piece -> piece.isSameColor(color))
//                .collect(Collectors.groupingBy(piece -> piece.getPosition().getX(), Collectors.counting()));
//    }

    public void removePieceIfNotEmpty(Piece targetPiece) {
        if (!(targetPiece.isEmpty())) {
            currentPieces.remove(targetPiece);
        }
    }

//    public boolean hasSamePositionPiece(Position target) {
//        return currentPieces.stream()
//                .anyMatch(piece -> piece.isSamePosition(target));
//    }
//
//    public void move() {
//
//    }

//    public void moveCross(Position target, CurrentPieces currentPieces) {
//        Cross cross = Cross.findCrossByTwoPosition(this.position, target);
//        currentPieces.hasPieceInPath(this.position, target, cross);
//        Piece targetPiece = currentPieces.findByPosition(target);
//        validateSameColor(targetPiece);
//        currentPieces.removePieceIfNotEmpty(targetPiece);
//    }

//    public void hasPieceInPath(Position source, Position target, Cross cross) {
//        int sourceX = source.getX();
//        int sourceY = source.getY();
//        int count = Math.max(Math.abs(source.subtractX(target)), Math.abs(source.subtractY(target)));
//        if (findByPosition(source).isPawn()) {
//            count++;
//        }
//        for (int i = 1; i < count; i++) {
//            validatePieceInPath(sourceX, sourceY, i, cross);
//        }
//    }
//
//    private void validatePieceInPath(int sourceX, int sourceY, int count, Cross cross) {
//        if (hasSamePositionPiece(
//                Position.of((char) (sourceX + (cross.getChangeValues()[0] * count)), (char) (sourceY + (cross.getChangeValues()[1] * count))))) {
//            throw new IllegalArgumentException(OBSTACLE_ERROR);
//        }
//    }
}
