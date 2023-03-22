package domain.piece.slider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public abstract class Slider extends Piece {
    private final List<Direction> movableDirection;

    protected Slider(Camp camp, List<Direction> movableDirections) {
        super(camp);
        this.movableDirection = movableDirections;
    }

    @Override
    public List<Square> fetchMovableSquares(Square currentSquare, Square targetSquare) {
        int fileGap = fetchGap(currentSquare, targetSquare, FILE_INDEX);
        int rankGap = fetchGap(currentSquare, targetSquare, RANK_INDEX);
        int gap = Math.max(Math.abs(fileGap), Math.abs(rankGap));

        Direction direction = Direction.find(fileGap / gap, rankGap / gap);
        validateDirection(direction);

        return fetchPath(currentSquare, gap, direction);
    }

    protected int fetchGap(Square currentSquare, Square targetSquare, int fileOrRank) {
        List<Integer> currentCoordinate = currentSquare.toCoordinate();
        List<Integer> targetCoordinate = targetSquare.toCoordinate();
        return targetCoordinate.get(fileOrRank) - currentCoordinate.get(fileOrRank);
    }

    private void validateDirection(Direction direction) {
        if (movableDirection.contains(direction)) {
            return;
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    private List<Square> fetchPath(Square currentSquare, int gap, Direction direction) {
        List<Square> squares = new ArrayList<>();
        int currentFile = currentSquare.toCoordinate().get(FILE_INDEX);
        int currentRank = currentSquare.toCoordinate().get(RANK_INDEX);
        int fileDirection = direction.getFile();
        int rankDirection = direction.getRank();

        for (int i = 1; i <= gap; i++) {
            int fileCoordinate = currentFile + (i * fileDirection);
            int rankCoordinate = currentRank + (i * rankDirection);
            squares.add(new Square(fileCoordinate, rankCoordinate));
        }
        return squares;
    }

    @Override
    public boolean canMove(Map<Square, Piece> pathInfo, Square targetSquare) {
        Piece pieceOnTargetSquare = pathInfo.get(targetSquare);
        pathInfo.remove(targetSquare);
        return isDifferentCampOrEmptyOnTarget(pieceOnTargetSquare) && existNoPieceOnPath(pathInfo);
    }

    private boolean isDifferentCampOrEmptyOnTarget(Piece target) {
        return isOppositeCamp(target) || target.isEmpty();
    }

    protected boolean existNoPieceOnPath(Map<Square, Piece> pathInfo) {
        return pathInfo.values().stream()
            .allMatch(piece -> isEmpty());
    }
}
