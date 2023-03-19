package domain.piece.type;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Pawn extends Piece {
    private boolean isFirstMove = true;
    private boolean isGoingForward;

    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> gaps = calculateGap(currentSquare, targetSquare);
        Integer distance = calculateDistance(gaps);
        List<Integer> direction = calculateDirection(gaps, distance);
        validateMovable(gaps);
        changeFirstMoveState();
        changeGoingForwardState(gaps);
        if (distance == 1) {
            return List.of(targetSquare);
        }
        return calculatePath(currentSquare, distance, direction);
    }

    private void changeGoingForwardState(List<Integer> gaps) {
        isGoingForward = !Objects.equals(Math.abs(gaps.get(FILE)), Math.abs(gaps.get(RANK)));
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {
        Integer rankGap = gaps.get(RANK);
        int fileGapAbs = Math.abs(gaps.get(FILE));
        int rankGapAbs = Math.abs(rankGap);
        validateFileGap(fileGapAbs);
        validateDiagonalMove(fileGapAbs, rankGapAbs);
        validateForwardMove(fileGapAbs, rankGapAbs);
        validateRankGap(rankGap);
        validateDoubleStep(rankGapAbs);
    }

    private void validateRankGap(int rankGap) {
        if ((isWhite() && rankGap < 0) || !isWhite() && rankGap > 0) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private void validateFileGap(int fileGapAbs) {
        if (fileGapAbs != 0 && fileGapAbs != 1) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private void validateDiagonalMove(int fileGapAbs, int rankGapAbs) {
        if (fileGapAbs == 1 && rankGapAbs != 1) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private void validateForwardMove(int fileGapAbs, int rankGapAbs) {
        if (fileGapAbs == 0 && rankGapAbs > 2) {
            throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
        }
    }

    private void validateDoubleStep(int rankGapAbs) {
        if (rankGapAbs == 2 && !isFirstMove) {
            throw new IllegalArgumentException("더블 스텝은 첫 움직임에만 가능합니다.");
        }
    }

    private void changeFirstMoveState() {
        if (isFirstMove) {
            isFirstMove = false;
        }
    }

    public boolean canMove(Map<Square, Camp> pathInfo, Square targetSquare) {
        Camp targetCamp = pathInfo.get(targetSquare);
        pathInfo.remove(targetSquare);
        boolean existPieceOnPath = isExistPieceOnPath(pathInfo);
        if (isGoingForward) {
            return targetCamp.equals(Camp.NONE) && !existPieceOnPath;
        }
        return isDifferentCamp(targetCamp);
    }
}
