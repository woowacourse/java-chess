package domain.piece.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<Square> fetchMovableCoordinate(Square currentSquare, Square targetSquare) {
        List<Integer> currentCoordinate = currentSquare.toCoordinate();
        Integer file = currentCoordinate.get(FILE);
        Integer rank = currentCoordinate.get(RANK);

        List<Square> movableSquares = new ArrayList<>();

        for (Direction direction : movableDirection) {
            ArrayList<Square> squares = new ArrayList<>();
            for (int i = 1; i < 8; i++) {
                int fileCoordinate = file + (i * direction.getFile());
                int rankCoordinate = rank + (i * direction.getRank());
                if (fileCoordinate < 0 || fileCoordinate > 7 || rankCoordinate < 0 || rankCoordinate > 7) {
                    continue;
                }
                squares.add(new Square(fileCoordinate, rankCoordinate));
            }
            if (squares.contains(targetSquare)) {
                movableSquares = squares;
            }
        }
        return movableSquares;
    }
}
