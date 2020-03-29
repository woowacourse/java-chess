package chess.domain;

import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessboard.ChessBoard;
import chess.domain.move.Direction;
import chess.domain.move.KnightType;
import chess.domain.move.MoveType;

import java.util.Objects;

public class Position {
    private static final String ERROR_MESSAGE_EXIST_PIECE_ON_PATH = "경로에 다른 말이 존재합니다.";
    private static final int Y_AXIS_INDEX = 1;
    private static final int X_AXIS_INDEX = 0;

    private XAxis xAxis;
    private YAxis yAxis;

    private Position(XAxis xAxis, YAxis yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public static Position of(String coordinate) {
        String[] xyAxisValue = coordinate.split("");
        return new Position(XAxis.of(xyAxisValue[X_AXIS_INDEX]), YAxis.of(xyAxisValue[Y_AXIS_INDEX]));
    }

    public static Position of(XAxis xAxis, YAxis yAxis) {
        return new Position(xAxis, yAxis);
    }

    public boolean isPawnStartLine(Pawn pawn) {
        if (pawn.isBlackTeam()) {
            return this.yAxis == YAxis.TWO;
        }
        return this.yAxis == YAxis.SEVEN;
    }

    public boolean isNewLine() {
        return xAxis == XAxis.A;
    }

    public boolean isSameRank(Position target) {
        return this.yAxis == target.yAxis;
    }

    public boolean isSameFile(Position target) {
        return isSameFile(target.xAxis);
    }

    public boolean isSameFile(XAxis xAxis) {
        return this.xAxis == xAxis;
    }

    public int calculateRankDistance(Position target) {
        return yAxis.getNumber() - target.yAxis.getNumber();
    }

    public int calculateFileDistance(Position target) {
        return xAxis.getNumber() - target.xAxis.getNumber();
    }

    public void move(MoveType moveType, ChessBoard chessBoard) {
        moveWhenKnightType(moveType);
        moveWhenStraight(moveType, chessBoard);
        moveWhenCross(moveType, chessBoard);
    }

    private void moveWhenKnightType(MoveType moveType) {
        if (moveType instanceof KnightType) {
            Position target = ((KnightType) moveType).getTargetPosition();
            this.xAxis = target.xAxis;
            this.yAxis = target.yAxis;
        }
    }

    private void moveWhenStraight(MoveType moveType, ChessBoard chessBoard) {
        Direction direction = moveType.getDirection();
        int count = moveType.getCount();
        int xAxisNumber = this.xAxis.getNumber();
        int yAxisNumber = this.yAxis.getNumber();

        if (direction == Direction.UP) {
            for (int i = 0; i < count; i++) {
                yAxisNumber++;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.yAxis = YAxis.of(yAxisNumber);
        }

        if (direction == Direction.DOWN) {
            for (int i = 0; i < count; i++) {
                yAxisNumber--;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.yAxis = YAxis.of(yAxisNumber);
        }

        if (direction == Direction.RIGHT) {
            for (int i = 0; i < count; i++) {
                xAxisNumber++;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.xAxis = XAxis.of(xAxisNumber);
        }

        if (direction == Direction.LEFT) {
            for (int i = 0; i < count; i++) {
                xAxisNumber--;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.xAxis = XAxis.of(xAxisNumber);
        }
    }

    private void moveWhenCross(MoveType moveType, ChessBoard chessBoard) {
        Direction direction = moveType.getDirection();
        int count = moveType.getCount();
        int xAxisNumber = this.xAxis.getNumber();
        int yAxisNumber = this.yAxis.getNumber();

        if (direction == Direction.UP_RIGHT) {
            for (int i = 0; i < count; i++) {
                xAxisNumber++;
                yAxisNumber++;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.xAxis = XAxis.of(xAxisNumber);
            this.yAxis = YAxis.of(yAxisNumber);
        }

        if (direction == Direction.UP_LEFT) {
            for (int i = 0; i < count; i++) {
                xAxisNumber--;
                yAxisNumber++;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.xAxis = XAxis.of(xAxisNumber);
            this.yAxis = YAxis.of(yAxisNumber);
        }

        if (direction == Direction.DOWN_RIGHT) {
            for (int i = 0; i < count; i++) {
                xAxisNumber++;
                yAxisNumber--;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.xAxis = XAxis.of(xAxisNumber);
            this.yAxis = YAxis.of(yAxisNumber);
        }

        if (direction == Direction.DOWN_LEFT) {
            for (int i = 0; i < count; i++) {
                xAxisNumber--;
                yAxisNumber--;
                isExistPieceOnPath(chessBoard, xAxisNumber, yAxisNumber);
            }
            this.xAxis = XAxis.of(xAxisNumber);
            this.yAxis = YAxis.of(yAxisNumber);
        }
    }

    private void isExistPieceOnPath(ChessBoard chessBoard, int xDegree, int yDegree) {
        if (chessBoard.isExistPiece(Position.of(XAxis.of(xDegree), YAxis.of(yDegree)))) {
            throw new IllegalArgumentException(ERROR_MESSAGE_EXIST_PIECE_ON_PATH);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xAxis == position.xAxis &&
                yAxis == position.yAxis;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xAxis, yAxis);
    }
}
