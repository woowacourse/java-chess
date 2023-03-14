package domain.piece.type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<List<Integer>> fetchMovableCoordinate(List<Integer> currentCoordinate) {
        Integer file = currentCoordinate.get(FILE);
        Integer rank = currentCoordinate.get(RANK);

        Set<List<Integer>> movableCoordinate = new HashSet<>();

        for (Direction direction : movableDirection) {
            for (int i = -7; i < 8; i++) {

                int fileCoordinate = file + (i * direction.getFile());
                int rankCoordinate = rank + (i * direction.getRank());

                if (i == 0 || fileCoordinate < 0 || fileCoordinate > 7 || rankCoordinate < 0 || rankCoordinate > 7) {
                    continue;
                }

                movableCoordinate.add(List.of(fileCoordinate, rankCoordinate));
            }
        }
        return movableCoordinate;
    }
}
