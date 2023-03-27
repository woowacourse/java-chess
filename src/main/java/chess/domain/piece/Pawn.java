package chess.domain.piece;

import chess.domain.*;

import java.util.List;

public class Pawn extends ChessPiece {

    public static final List<Direction> MOVABLE_DIRECTION = List.of(Direction.NORTH, Direction.SOUTH, Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    public static final int MAX_MOVEMENT_OF_INIT_PAWN = 2;
    public static final int MAX_MOVEMENT_OF_NON_INIT_PAWN = 1;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_PAWN;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_PAWN;
        }
        return Shape.BLANK;
    }

    @Override
    public Direction findMovableDirection(Position sourcePosition, Position targetPosition) {
        if (color.equals(Color.WHITE) && Direction.isMovableNorth(sourcePosition, targetPosition)) {
            return Direction.NORTH;
        }
        if (color.equals(Color.WHITE) && Direction.isMovableNorthEast(sourcePosition, targetPosition)) {
            return Direction.NORTH_EAST;
        }
        if (color.equals(Color.WHITE) && Direction.isMovableNorthWest(sourcePosition, targetPosition)) {
            return Direction.NORTH_WEST;
        }
        if (color.equals(Color.BLACK) && Direction.isMovableToSouth(sourcePosition, targetPosition)) {
            return Direction.SOUTH;
        }
        if (color.equals(Color.BLACK) && Direction.isMovableSouthEast(sourcePosition, targetPosition)) {
            return Direction.SOUTH_EAST;
        }
        if (color.equals(Color.BLACK) && Direction.isMovableSouthWest(sourcePosition, targetPosition)) {
            return Direction.SOUTH_WEST;
        }
        throw new IllegalArgumentException("[ERROR] 북쪽(화이트폰) 또는 남쪽(블랙폰) 중 이동 가능한 방향이 없습니다.");
    }

    @Override
    public int findDistance(Direction direction, Position sourcePosition, Position targetPosition) {
        validateDirection(direction);
        int distance = 0;
        Position tempPosition = sourcePosition;
        while (tempPosition != targetPosition) {
            tempPosition = tempPosition.getMovingPosition(direction);
            distance++;
            validateDistance(distance, sourcePosition);
        }
        return distance;
    }

    public void validateDirection(Direction direction) {
        if (!MOVABLE_DIRECTION.contains(direction)) {
            throw new IllegalArgumentException("[ERROR] 해당 방향으로는 이동할 수 없습니다.");
        }
    }

    private void validateDistance(int distance, Position sourcePosition) {
        if (!isFirstMovement(sourcePosition) && distance > MAX_MOVEMENT_OF_NON_INIT_PAWN) {
            throw new IllegalArgumentException("[ERROR] Pawn은 한 칸만 이동할 수 있습니다.");
        }
        if (isFirstMovement(sourcePosition) && distance > MAX_MOVEMENT_OF_INIT_PAWN) {
            throw new IllegalArgumentException("[ERROR] 초기 상태의 폰은 최대 두칸까지 이동 가능합니다.");
        }
    }

    public boolean isFirstMovement(Position position) {
        if (this.color.equals(Color.WHITE) && position.isInWhitePawnInitRank()) {
            return true;
        }

        return this.color.equals(Color.BLACK) && position.isInBlackPawnInitRank();
    }

    @Override
    public boolean isMovable(Movement movement, ChessBoard chessBoard) {
        if (this.color.equals(Color.WHITE)) {
            return isMovableWhitePawn(movement, chessBoard);
        }
        if (this.color.equals(Color.BLACK)) {
            return isMovableBlackPawn(movement, chessBoard);
        }
        throw new IllegalArgumentException("[ERROR] 공백 객체는 이동 여부를 판단할 수 없습니다.");
    }

    private boolean isMovableWhitePawn(Movement movement, ChessBoard chessBoard) {
        if (movement.getDirection().equals(Direction.NORTH)) {
            return isMovableToStraight(movement, chessBoard);
        }
        if (movement.getDirection().equals(Direction.NORTH_EAST) || movement.getDirection().equals(Direction.NORTH_WEST)) {
            return isMovableToDiagonal(movement, chessBoard, Color.WHITE);
        }
        throw new IllegalArgumentException("[ERROR] 화이트폰은 해당 방향으로 이동할 수 없습니다.");
    }

    private boolean isMovableBlackPawn(Movement movement, ChessBoard chessBoard) {
        if (movement.getDirection().equals(Direction.SOUTH)) {
            return isMovableToStraight(movement, chessBoard);
        }
        if (movement.getDirection().equals(Direction.SOUTH_EAST) || movement.getDirection().equals(Direction.SOUTH_WEST)) {
            return isMovableToDiagonal(movement, chessBoard, Color.BLACK);
        }
        throw new IllegalArgumentException("[ERROR] 블랙폰은 해당 방향으로 이동할 수 없습니다.");

    }

    private boolean isMovableToStraight(Movement movement, ChessBoard chessBoard) {
        Position targetPosition = movement.findTargetPosition();
        return chessBoard.isEmpty(targetPosition);
    }

    private boolean isMovableToDiagonal(Movement movement, ChessBoard chessBoard, Color targetColor) {
        Position targetPosition = movement.findTargetPosition();
        ChessPiece targetChessPiece = chessBoard.getChessPiece(targetPosition);
        return !targetChessPiece.equals(new Empty()) && this.color.equals(targetColor);
    }
}
