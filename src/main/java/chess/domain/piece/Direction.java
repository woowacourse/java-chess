package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1), NORTHEAST(1, 1), EAST(1, 0), SOUTHEAST(1, -1), SOUTH(0, -1), SOUTHWEST(-1, -1), WEST(-1, 0),
    NORTHWEST(-1, 1),
    
    NNE(1, 2), NNW(-1, 2), SSE(1, -2), SSW(-1, -2), EEN(2, 1), EES(2, -1), WWN(-2, 1), WWS(-2, -1);
    
    private final int xDegree;
    private final int yDegree;
    
    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }
    
    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }
    
    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }
    
    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }
    
    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }
    
    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }
    
    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }
    
    public Position addDegreeTo(Position position) {
        return position.add(xDegree, yDegree);
    }
    
    public boolean isCorrectDirection(int xDistance, int yDistance, int movableLength) {
        boolean xCanArrive = canArrive(xDistance, xDegree, movableLength);
        boolean yCanArrive = canArrive(yDistance, yDegree, movableLength);
        return xCanArrive && yCanArrive;
    }
    
    private boolean canArrive(int distance, int degree, int movableLength) {
        if (isStay(distance, degree)) {
            return true;
        }
        
        if (isOppositeDirection(distance, degree)) {
            return false;
        }
    
        return degree == 0 || canDivideWithoutRemainder(distance, degree, movableLength);
    }
    
    private boolean isStay(int distance, int degree) {
        return distance == 0 && degree == 0;
    }
    
    private boolean isOppositeDirection(int distance, int degree) {
        return distance * degree <= 0;
    }
    
    private boolean canDivideWithoutRemainder(int distance, int degree, int movableLength) {
        return (distance % degree == 0) && (distance / degree <= movableLength);
    }
}
