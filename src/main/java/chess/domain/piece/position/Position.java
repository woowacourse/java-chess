package chess.domain.piece.position;

import chess.domain.move.Direction;
import chess.domain.piece.Pawn;

import java.util.Objects;

public class Position {
    private static final int XPOSITION_INDEX = 0;
    private static final int YPOSITION_INDEX = 1;
    private static final String FILE_RANK_DELIMITER = "";

    private XPosition xPosition;
    private YPosition yPosition;

    private Position(XPosition xPosition, YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public static Position of(String coordinate) {
        String[] xPositionAndYPosition = coordinate.split(FILE_RANK_DELIMITER);
        XPosition xPosition = XPosition.of(xPositionAndYPosition[XPOSITION_INDEX]);
        YPosition yPosition = YPosition.of(xPositionAndYPosition[YPOSITION_INDEX]);
        return Position.of(xPosition, yPosition);
    }

    public static Position of(XPosition xPosition, YPosition yPosition) {
        return new Position(xPosition, yPosition);
    }

    public static Position of(Position position) {
        return Position.of(position.xPosition, position.yPosition);
    }

    public void move(Direction direction) {
        int x = direction.getXDegree();
        int y = direction.getYDegree();
        this.xPosition = XPosition.of(this.xPosition.getNumber() + x);
        this.yPosition = YPosition.of(this.yPosition.getNumber() + y);
    }

    public boolean isPawnStartLine(Pawn pawn) {
        if (pawn.isBlackTeam()) {
            return this.yPosition == YPosition.TWO;
        }
        return this.yPosition == YPosition.SEVEN;
    }

    public boolean isSameRank(Position target) {
        return this.yPosition == target.yPosition;
    }

    public boolean isSameXPosition(Position target) {
        return isSameXPosition(target.xPosition);
    }

    public boolean isSameXPosition(XPosition XPosition) {
        return this.xPosition == XPosition;
    }

    public int calculateRankDistance(Position target) {
        return yPosition.calculateDistance(target.yPosition);
    }

    public int calculateFileDistance(Position target) {
        return xPosition.calculateDistance(target.xPosition);
    }

    public String getXPosition() {
        return xPosition.getName();
    }

    public String getYPosition() {
        return String.valueOf(yPosition.getNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPosition == position.xPosition &&
                yPosition == position.yPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPosition, yPosition);
    }

    @Override
    public String toString() {
        return xPosition.getName() + yPosition.getNumber();
    }
}
