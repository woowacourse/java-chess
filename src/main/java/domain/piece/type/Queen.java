package domain.piece.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public class Queen extends Piece {
    private static final List<Direction> movableDirection = Arrays.asList(Direction.values());

    public Queen(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isQueen() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        for (Direction direction : movableDirection) {
            ArrayList<Square> squares = getAllPath(currentSquare, targetSquare, direction);
            if (squares.contains(targetSquare)) {
                return squares;
            }
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    private ArrayList<Square> getAllPath(Square currentSquare, Square targetSquare, Direction direction) {
        List<Integer> currentCoordinate = currentSquare.toCoordinate();
        Integer file = currentCoordinate.get(FILE);
        Integer rank = currentCoordinate.get(RANK);

        ArrayList<Square> squares = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            int fileCoordinate = file + (i * direction.getFile());
            int rankCoordinate = rank + (i * direction.getRank());
            if (isInCoordinateRange(fileCoordinate, rankCoordinate)) {
                Square pathSquare = new Square(fileCoordinate, rankCoordinate);
                squares.add(pathSquare);
                if (pathSquare.equals(targetSquare)) {
                    return squares;
                }
            }
        }
        return squares;
    }
}
