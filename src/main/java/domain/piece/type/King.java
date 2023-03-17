package domain.piece.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public class King extends Piece {
    private static final List<Direction> movableDirection = Arrays.asList(Direction.values());

    public King(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> currentCoordinate = currentSquare.toCoordinate();
        Integer file = currentCoordinate.get(FILE);
        Integer rank = currentCoordinate.get(RANK);

        for (Direction direction : movableDirection) {
            ArrayList<Square> squares = new ArrayList<>();
            int fileCoordinate = file + direction.getFile();
            int rankCoordinate = rank + direction.getRank();
            if (isInCoordinateRange(fileCoordinate, rankCoordinate)) {
                squares.add(new Square(fileCoordinate, rankCoordinate));
            }
            if (squares.contains(targetSquare)) {
                return squares;
            }
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    @Override
    protected void validateMovable(List<Integer> gaps) {

    }
}
