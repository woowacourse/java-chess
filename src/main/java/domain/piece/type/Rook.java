package domain.piece.type;

import java.util.ArrayList;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public class Rook extends Piece {
    private static final List<Direction> movableDirection = List.of(Direction.EAST, Direction.WEST,
            Direction.SOUTH, Direction.NORTH);

    public Rook(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> currentCoordinate = currentSquare.toCoordinate();
        Integer file = currentCoordinate.get(FILE);
        Integer rank = currentCoordinate.get(RANK);

        for (Direction direction : movableDirection) {
            ArrayList<Square> squares = new ArrayList<>();
            for (int i = 1; i < 8; i++) {
                int fileCoordinate = file + (i * direction.getFile());
                int rankCoordinate = rank + (i * direction.getRank());
                if (isInCoordinateRange(fileCoordinate, rankCoordinate)) {
                    continue;
                }
                squares.add(new Square(fileCoordinate, rankCoordinate));
            }
            if (squares.contains(targetSquare)) {
                return squares;
            }
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }
}
