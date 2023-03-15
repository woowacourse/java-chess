package domain.piece.type;

import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Piece;

public class Knight extends Piece {
    private static final List<List<Integer>> movableDirection = List.of(
            List.of(1, 2),
            List.of(1, -2),
            List.of(-1, 2),
            List.of(-1, -2),
            List.of(2, 1),
            List.of(2, -1),
            List.of(-2, 1),
            List.of(-2, -1)
    );

    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Integer> coordinateGap = List.of(
                currentSquare.toCoordinate().get(FILE) - targetSquare.toCoordinate().get(FILE),
                currentSquare.toCoordinate().get(RANK) - targetSquare.toCoordinate().get(RANK));
        if (movableDirection.contains(coordinateGap)) {
            return List.of(targetSquare);
        }
        return null;
    }
}
