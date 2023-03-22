package domain.board;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.square.Color;
import domain.square.Square;

import java.util.Map;

public final class Board {

    private static final int KING_COUNT = 2;

    private final Map<Coordinate, Square> squareLocations;

    public Board() {
        this.squareLocations = BoardInitialImage.getCachedBoard();
    }

    public Square findSquare(final Coordinate target) {
        return squareLocations.get(target);
    }

    public void replaceSquare(final Coordinate target, final Square square) {
        if (square.getPiece().isPawn()) {
            replaceSquareIfIsPawn(target, square);
        }
        squareLocations.put(target, square);
    }

    private void replaceSquareIfIsPawn(final Coordinate target, final Square square) {
        if (square.getColor() == Color.BLACK) {
            squareLocations.put(target, new Square(new BlackPawn(), Color.BLACK));
            return;
        }
        squareLocations.put(target, new Square(new WhitePawn(), Color.WHITE));
        System.out.println(123);
    }

    public void replaceWithEmptySquare(final Coordinate target) {
        squareLocations.put(target, Square.ofEmptyPiece());
    }

    public boolean isMovable(final Coordinate start, final Coordinate end) {
        validateOverBoardSize(start, end);

        return squareLocations.get(start).isPieceMovable(
                start,
                end,
                Situation.of(findSquare(start), findSquare(end))
        );
    }

    private void validateOverBoardSize(final Coordinate start, final Coordinate end) {
        if (squareLocations.containsKey(start) && squareLocations.containsKey(end)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 보드 좌표 범위를 벗어났습니다.");
    }

    public boolean isSquareEmptyAt(final Coordinate target) {
        return squareLocations.get(target)
                .getColor() == Color.NEUTRAL;
    }

    public boolean isSameColor(final Coordinate start, final Coordinate end) {
        Square startSquare = findSquare(start);
        Square endSquare = findSquare(end);

        return startSquare.hasPieceNotSameColorWith(endSquare);
    }

    public boolean allKingAlive() {
        return squareLocations.values().stream()
                .filter(Square::hasKing)
                .count() == KING_COUNT;
    }

    public Map<Coordinate, Square> getSquareLocations() {
        return squareLocations;
    }
}
