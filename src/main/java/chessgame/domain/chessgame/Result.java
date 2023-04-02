package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;

import java.util.Map;

public class Result {

    public static final double DOUBLE_PAWN_SCORE = 0.5;
    public static final int DEFAULT_PAWN_COUNT = 1;

    public double calculateTeamAt(final Map<Coordinate, Piece> board, final Camp camp) {
        return board.keySet()
                    .stream()
                    .filter(coordinate -> board.get(coordinate)
                                               .isSameCamp(camp))
                    .mapToDouble(coordinate -> getScoreEachPiece(board, coordinate, camp))
                    .sum();
    }

    private double getScoreEachPiece(final Map<Coordinate, Piece> board,
                                     final Coordinate targetCoordinate,
                                     final Camp camp) {
        Piece targetPiece = board.get(targetCoordinate);
        if (targetPiece.isSameTypeWith(PieceType.PAWN)) {
            return getScoreByNumberOfPawnInSameColumn(board, targetCoordinate, camp);
        }
        return targetPiece.score();
    }

    private double getScoreByNumberOfPawnInSameColumn(final Map<Coordinate, Piece> board,
                                                      final Coordinate targetCoordinate,
                                                      final Camp camp) {
        long countPawnInSameColumn = board.keySet()
                                          .stream()
                                          .filter(coordinate ->
                                                  isPawnInSameColumn(board, coordinate, targetCoordinate, camp))
                                          .count();

        if (countPawnInSameColumn == DEFAULT_PAWN_COUNT) {
            return board.get(targetCoordinate)
                        .score();
        }
        return DOUBLE_PAWN_SCORE;
    }

    private boolean isPawnInSameColumn(final Map<Coordinate, Piece> board,
                                       final Coordinate checkCoordinate,
                                       final Coordinate targetCoordinate,
                                       final Camp camp) {
        Piece checkPiece = board.get(checkCoordinate);
        return checkPiece.isSameCamp(camp)
                && checkPiece.isSameTypeWith(PieceType.PAWN)
                && targetCoordinate.isSameColumn(checkCoordinate);
    }
}
