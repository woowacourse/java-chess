package domain.piece;

import domain.Player;
import domain.directions.Direction;
import domain.directions.DirectionsGenerator;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends SpecificLocationPiece {

    private int startLine;

    public Pawn(final Player player, final DirectionsGenerator directionsGenerator, int startLine) {
        super(player, PieceSymbol.Pawn, directionsGenerator);
        this.startLine = startLine;
    }


    @Override
    List<Position> calculateAvailablePosition(Position source, Direction direction) {
        if (!isFirstMove(source) && isTwoSpaceMoveDirection(direction)) {
            return null;
        }
        List<Position> positions = new ArrayList<>();
        if (isTwoSpaceMoveDirection(direction)) {
            calculatePawnTwoSpaceMove(positions, source, direction);
        }
        int file = source.getFile() + direction.getFile();
        int rank = source.getRank() + direction.getRank();

        if (checkOverRange(file, rank)) {
            positions.add(Position.of(File.of(file), Rank.of(rank)));
        }
        System.out.println(positions);
        return positions;
    }

    private boolean isFirstMove(Position source) {
        return source.getRank() == startLine;
    }

    private boolean isTwoSpaceMoveDirection(Direction direction) {
        return direction.equals(Direction.SOUTH_SOUTH) || direction.equals(Direction.NORTH_NORTH);
    }

    private void calculatePawnTwoSpaceMove(List<Position> positions, Position source,
        Direction direction) {
        Direction addDirection = generateAddDirection(direction);
        if (addDirection != null) {
            int file = source.getFile() + addDirection.getFile();
            int rank = source.getRank() + addDirection.getRank();
            if (checkOverRange(rank, file)) {
                positions.add(Position.of(File.of(file), Rank.of(rank)));
            }
        }
    }

    private Direction generateAddDirection(Direction direction) {
        if (direction.equals(Direction.NORTH_NORTH)) {
            return Direction.NORTH;
        }
        if (direction.equals(Direction.SOUTH_SOUTH)) {
            return Direction.SOUTH;
        }
        return null;
    }
}
