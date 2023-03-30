package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {
    private static final Map<Color, Rank> FIRST_RANK_BY_COLOR = Map.of(Color.BLACK, Rank.SEVEN, Color.WHITE, Rank.TWO);
    private static final Map<Color, Integer> oneStraightRankDistanceByColor = Map.of(Color.BLACK, -1, Color.WHITE, 1);
    private static final Map<Color, Integer> twoStraightRankDistanceByColor = Map.of(Color.BLACK, -2, Color.WHITE, 2);

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public boolean isMovable(final Position start, final Position end, final Piece destinationPiece) {
        return isStraightMove(start, end) && isMovableStraightDestination(destinationPiece)
                || isDiagonalMovable(start,end) && isMovableDiagonalDestination(destinationPiece);
    }

    @Override
    public List<Position> createRoute(final Position start, final Position end) {
        int vectorX = Integer.compare(start.findGapOfColumn(end), 0);
        int vectorY = Integer.compare(start.findGapOfRank(end), 0);

        List<Position> route = new ArrayList<>();
        Position currentPosition = start;
        do {
            currentPosition = currentPosition.moveByUnitVectorING(vectorX, vectorY);
            route.add(currentPosition);
        } while (!currentPosition.equals(end));
        return route;
    }

    private boolean isMovableDiagonalDestination(final Piece destinationPiece) {
        return destinationPiece.isSameColor(color.getOpponent());
    }

    private boolean isStraightMove(final Position start, final Position end) {
        int x = start.findGapOfColumn(end);
        int y = start.findGapOfRank(end);

        if(isFirstMove(start)) {
            return x == 0 && (y ==oneStraightRankDistanceByColor.get(color) || y== twoStraightRankDistanceByColor.get(color)) ;
        }
        return x == 0 && y == oneStraightRankDistanceByColor.get(color);
    }

    private boolean isFirstMove(Position start) {
        return FIRST_RANK_BY_COLOR.get(color).equals(start.getRank());
    }

    private static boolean isMovableStraightDestination(final Piece destinationPiece) {
        return destinationPiece.isSameColor(Color.NONE);
    }

    public boolean isDiagonalMovable(final Position start, final Position end) {
        int absX = Math.abs(start.findGapOfColumn(end));
        int absY = Math.abs(start.findGapOfRank(end));
        return  absX == 1 && absY ==1;
    }

}
