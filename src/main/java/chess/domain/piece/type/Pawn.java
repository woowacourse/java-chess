package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

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
    public boolean isMovable(final Position start, final Position end, final Color colorOfDestination) {
        return isStraightMove(start, end) && isMovableStraightDestination(colorOfDestination)
                || isDiagonalMovable(start,end) && isMovableDiagonalDestination(colorOfDestination);
    }

    @Override
    public double getScore(final List<Piece> piecesInSameColumn) {
        long pawnCountInSameColumn = piecesInSameColumn.stream()
                .filter(piece -> piece.pieceType == this.pieceType)
                .count();

        if(pawnCountInSameColumn > 1) {
            return pieceType.getScore();
        }
        return pieceType.getScore()*2;
    }

    private boolean isMovableDiagonalDestination(final Color colorOfDestination) {
        return colorOfDestination.isSameColor(color.getOpponent());
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

    private static boolean isMovableStraightDestination(final Color colorOfDestination) {
        return colorOfDestination.isSameColor(Color.NONE);
    }

    public boolean isDiagonalMovable(final Position start, final Position end) {
        int absX = Math.abs(start.findGapOfColumn(end));
        int absY = Math.abs(start.findGapOfRank(end));
        return  absX == 1 && absY ==1;
    }

}
