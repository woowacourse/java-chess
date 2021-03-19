package chess.domain.piece;

import static chess.domain.board.Direction.DOWN;
import static chess.domain.board.Direction.LEFT;
import static chess.domain.board.Direction.RIGHT;
import static chess.domain.board.Direction.UP;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    public Rook(TeamType teamType) {
        super(teamType, NAME, SCORE, Arrays.asList(LEFT, RIGHT, UP, DOWN));
    }

    @Override
    public boolean isMovableTo(Board board, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        Direction moveCommandDirection = currentCoordinate.calculateDirection(targetCoordinate);
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        List<Direction> directions = getDirections();
        if (!directions.contains(moveCommandDirection)) {
            return false;
        }
        System.out.println("=========");
        System.out.println("룩의 목적지 : " + targetCoordinate.getFile() + targetCoordinate.getRank().getY());
        System.out.println("룩의 이동 방향 : " + moveCommandDirection);
        System.out.println("룩의 위치 : " + currentCoordinate.getFile() + currentCoordinate.getRank().getY());
        Coordinate movingCoordinate = currentCoordinate.move(moveCommandDirection);
        while (!movingCoordinate.equals(targetCoordinate)) {
            System.out.println("룩의 위치 : " + movingCoordinate.getFile() + movingCoordinate.getRank().getY());
            Piece piece = board.find(movingCoordinate);
            if (piece != null) {
                break;
            }
            possibleCoordinates.add(movingCoordinate);
            movingCoordinate = movingCoordinate.move(moveCommandDirection);
        }
        Piece piece = board.find(movingCoordinate);
        if (piece == null || !piece.isTeamOf(this.getTeamType())) {
            System.out.println("룩의 위치 : " + movingCoordinate.getFile() + movingCoordinate.getRank().getY());
            possibleCoordinates.add(movingCoordinate);
        }
        return possibleCoordinates.contains(targetCoordinate);
    }
}
