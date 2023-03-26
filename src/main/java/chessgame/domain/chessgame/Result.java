package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;

import java.util.Map;

public class Result {

    public double calculateTeamAt(Map<Coordinate, Piece> board, Camp camp) {
        return board.keySet()
                    .stream()
                    .filter(coordinate -> board.get(coordinate)
                                               .isSameCamp(camp))
                    .mapToDouble(coordinate -> getScoreEachPiece(board, coordinate, camp))
                    .sum();
    }

    private double getScoreEachPiece(Map<Coordinate, Piece> board,
                                     Coordinate targetCoordinate,
                                     Camp camp) {
        Piece targetPiece = board.get(targetCoordinate);
        if (targetPiece.isSameTypeWith(PieceType.PAWN)) {
            return getScoreByNumberOfPawnInSameColumn(board, targetCoordinate, camp);
        }
        return targetPiece.score();
    }

    private double getScoreByNumberOfPawnInSameColumn(Map<Coordinate, Piece> board,
                                                      Coordinate targetCoordinate,
                                                      Camp camp) {
        long countPawnInSameColumn = board.keySet()
                                          .stream()
                                          .filter(coordinate ->
                                                  isPawnInSameColumn(board, coordinate, targetCoordinate, camp))
                                          .count();

        if (countPawnInSameColumn == 1) {
            return board.get(targetCoordinate)
                        .score();
        }
        return 0.5;
    }

    private boolean isPawnInSameColumn(Map<Coordinate, Piece> board,
                                       Coordinate checkCoordinate,
                                       Coordinate targetCoordinate,
                                       Camp camp) {
        Piece checkPiece = board.get(checkCoordinate);
        return checkPiece.isSameCamp(camp)
                && checkPiece.isSameTypeWith(PieceType.PAWN)
                && targetCoordinate.isSameColumn(checkCoordinate);
    }
}
