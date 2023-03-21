package domain.piece.type.unrestricted;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.type.Type;

public abstract class UnrestrictedPiece extends Piece {
    public UnrestrictedPiece(Camp camp, Type type) {
        super(camp, type);
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> gaps = calculateGap(currentSquare, targetSquare);
        Integer distance = calculateDistance(gaps);
        List<Integer> direction = calculateDirection(gaps, distance);
        validateMovable(gaps);
        return calculatePath(currentSquare, distance, direction);
    }

    protected boolean isNotForward(List<Integer> gap) {
        Integer fileGap = gap.get(FILE);
        Integer RankGap = gap.get(RANK);
        return fileGap != 0 && RankGap != 0;
    }


    protected boolean isNotDiagonal(List<Integer> gap) {
        return !Objects.equals(Math.abs(gap.get(FILE)), Math.abs(gap.get(RANK)));
    }

    @Override
    public boolean canMove(Map<Square, Camp> pathInfo, Square targetSquare) {
        Camp targetCamp = pathInfo.get(targetSquare);
        pathInfo.remove(targetSquare);
        return isDifferentCampOrEmptyOnTarget(targetCamp) && !isExistPieceOnPath(pathInfo);
    }
}
