package chess.model.piece;

import chess.model.Coordinate;

import java.util.List;

public class Pawn implements Piece {
    private boolean isNotMoved;
    private String team;

    public Pawn(String team) {
        if (!team.equals("white") && !team.equals("black")) {
            throw new IllegalArgumentException("없는 팀입니다!");
        }
        this.isNotMoved = true;
        this.team = team;
    }

    @Override
    public boolean isMovePossible(List<Coordinate> coordinates) {
        Coordinate sourceCoordinateX = coordinates.get(0);
        Coordinate sourceCoordinateY = coordinates.get(1);
        Coordinate targetCoordinateX = coordinates.get(2);
        Coordinate targetCoordinateY = coordinates.get(3);

        if (team.equals("white")) { // 아래 팀인 경우
            if ((targetCoordinateX.calculateDistance(sourceCoordinateX) == 0)) {
                if (targetCoordinateY.calculateDistance(sourceCoordinateY) == 1) {
                    return true;
                }
                if (targetCoordinateY.calculateDistance(sourceCoordinateY) == 2 && isNotMoved) {
                    return true;
                }

            }
            if (Math.abs(targetCoordinateX.calculateDistance(sourceCoordinateX)) == 1
                    && targetCoordinateY.calculateDistance(sourceCoordinateY) == 1) {
                return true;
            }
        }

        if (team.equals("black")) {
            if (sourceCoordinateX.calculateDistance(targetCoordinateX) == 0) {
                if (sourceCoordinateY.calculateDistance(targetCoordinateY) == 1) {
                    return true;
                }
                if (sourceCoordinateY.calculateDistance(targetCoordinateY) == 2 && isNotMoved) {
                    return true;
                }
            }
            if (Math.abs(sourceCoordinateX.calculateDistance(targetCoordinateX)) == 1
                    && sourceCoordinateY.calculateDistance(targetCoordinateY) == 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void signalMoved() {
        this.isNotMoved = false;
    }
}
