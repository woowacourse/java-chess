package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.LEFT_DOWN;
import static chess.domain.board.Direction.LEFT_UP;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.RIGHT_DOWN;
import static chess.domain.board.Direction.RIGHT_UP;
import static chess.domain.board.Direction.UP;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final String NAME = "K";
    private static final double SCORE = 0;

    public King(TeamType teamType) {
        super(teamType, NAME, SCORE, Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN));
    }

    @Override
    public boolean isMovableTo(Board board, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        List<Direction> directions = getDirections();
        List<Coordinate> possibleAllCoordinates = getPossibleCoordinates(currentCoordinate, directions);
        List<Coordinate> possibleCoordinates = new ArrayList<>();

        if (!possibleAllCoordinates.contains(targetCoordinate)) {
            return false;
        }

        for (Coordinate coordinate : possibleAllCoordinates) {
            Piece piece = board.find(coordinate);
            if (piece == null || !piece.isTeamOf(getTeamType())) {
                possibleCoordinates.add(coordinate);
            }
        }

        List<Coordinate> checkCoordinates = board.findCheckCoordinatesNew(currentCoordinate, possibleCoordinates, getTeamType());
        possibleCoordinates.removeAll(checkCoordinates);
        return possibleCoordinates.contains(targetCoordinate);
    }

    private List<Coordinate> getPossibleCoordinates(Coordinate currentCoordinate,
        List<Direction> directions) {

        List<Coordinate> possibleCoordinates = new ArrayList<>();
        for (Direction direction : directions) {
            Coordinate nextCoordinate;
            try {
                nextCoordinate = currentCoordinate.move(direction);
                possibleCoordinates.add(nextCoordinate);
            } catch (Exception ignored) {
            }
        }
        return possibleCoordinates;
    }
}
