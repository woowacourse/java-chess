package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.move.Direction;
import chess.domain.move.KnightType;
import chess.domain.move.MoveType;
import chess.domain.piece.Pawn;

import java.util.Objects;

public class Position {
    private static final String ERROR_MESSAGE_EXIST_PIECE_ON_PATH = "경로에 다른 말이 존재합니다.";
    private static final int Y_POSITION_VALUE = 1;
    private static final int X_POSITION_INDEX = 0;

    private XPosition xPosition;
    private YPosition yPosition;

    private Position(XPosition xPosition, YPosition yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public static Position of(String coordinate) {
        String[] xyPositionValue = coordinate.split("");
        return new Position(XPosition.of(xyPositionValue[X_POSITION_INDEX])
                , YPosition.of(xyPositionValue[Y_POSITION_VALUE]));
    }

    public static Position of(XPosition xPosition, YPosition yPosition) {
        return new Position(xPosition, yPosition);
    }

    public boolean isNewLine() {
        return xPosition == XPosition.A;
    }

    public boolean isSameRank(Position target) {
        return this.yPosition == target.yPosition;
    }

    public boolean isSameFile(Position target) {
        return isSameFile(target.xPosition);
    }

    public boolean isSameFile(XPosition xPosition) {
        return this.xPosition == xPosition;
    }

    public boolean isUpDirection(Position target) {
        return calculateYPositionDistance(target) < 0;
    }

    public boolean isDownDirection(Position target) {
        return calculateYPositionDistance(target) > 0;
    }

    public boolean isLeftDirection(Position target) {
        return calculateXPositionDistance(target) > 0;
    }

    public boolean isRightDirection(Position target) {
        return calculateXPositionDistance(target) < 0;
    }

    public int calculateYPositionDistance(Position target) {
        return yPosition.getNumber() - target.yPosition.getNumber();
    }

    public int calculateXPositionDistance(Position target) {
        return xPosition.getNumber() - target.xPosition.getNumber();
    }

    private void isExistPieceOnPath(ChessBoard chessBoard, int xDegree, int yDegree) {
        if (chessBoard.isExistPiece(Position.of(XPosition.of(xDegree), YPosition.of(yDegree)))) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EXIST_PIECE_ON_PATH);
        }
    }

    public boolean isPawnStartLine(Pawn pawn) {
        if (pawn.isBlackTeam()) {
            return this.yPosition == YPosition.TWO;
        }
        return this.yPosition == YPosition.SEVEN;
    }

    public void move(MoveType moveType, ChessBoard chessBoard) {
        moveWhenKnightType(moveType);
        moveWhenStraight(moveType, chessBoard);
        moveWhenCross(moveType, chessBoard);
    }

    private void moveWhenKnightType(MoveType moveType) {
        if (moveType instanceof KnightType) {
            Position target = ((KnightType) moveType).getTargetPosition();
            this.xPosition = target.xPosition;
            this.yPosition = target.yPosition;
        }
    }

    private void moveWhenStraight(MoveType moveType, ChessBoard chessBoard) {
        Direction direction = moveType.getDirection();
        int count = moveType.getCount();

        for (int i = 0; i < count; i++) {
            moveUpDirection(chessBoard, direction);
            moveDownDirection(chessBoard, direction);
            moveLeftDirection(chessBoard, direction);
            moveRightDirection(chessBoard, direction);
        }
    }

    private void moveWhenCross(MoveType moveType, ChessBoard chessBoard) {
        Direction direction = moveType.getDirection();
        int count = moveType.getCount();

        for (int i = 0; i < count; i++) {
            moveUpRightDirection(chessBoard, direction);
            moveUpLeftDirection(chessBoard, direction);
            moveDownRightDirection(chessBoard, direction);
            moveDownLeftDirection(chessBoard, direction);
        }
    }

    private void moveUpDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.UP) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            yPositionNumber++;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveDownDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.DOWN) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            yPositionNumber--;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveLeftDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.LEFT) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            xPositionNumber--;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveRightDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.RIGHT) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            xPositionNumber++;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveUpRightDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.UP_RIGHT) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            xPositionNumber++;
            yPositionNumber++;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveUpLeftDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.UP_LEFT) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            xPositionNumber--;
            yPositionNumber++;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveDownRightDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.DOWN_RIGHT) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            xPositionNumber++;
            yPositionNumber--;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void moveDownLeftDirection(ChessBoard chessBoard, Direction direction) {
        if (direction == Direction.DOWN_LEFT) {
            int xPositionNumber = this.xPosition.getNumber();
            int yPositionNumber = this.yPosition.getNumber();
            xPositionNumber--;
            yPositionNumber--;
            isExistPieceOnPath(chessBoard, xPositionNumber, yPositionNumber);
            movePiece(xPositionNumber, yPositionNumber);
        }
    }

    private void movePiece(int xPositionNumber, int yPositionNumber) {
        this.yPosition = YPosition.of(yPositionNumber);
        this.xPosition = XPosition.of(xPositionNumber);
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
}
