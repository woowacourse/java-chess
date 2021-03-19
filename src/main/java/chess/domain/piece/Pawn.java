package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
        this.type = Type.PAWN;
    }

    @Override
    public List<Position> getMovablePositions(ChessBoard chessBoard) {
        List<Position> movablePositions = new ArrayList<>();
        movablePositions.addAll(getLinearMovablePositions(chessBoard));
        movablePositions.addAll(getDiagonalMovablePositions(chessBoard));
        return Collections.unmodifiableList(movablePositions);
    }

    private List<Position> getLinearMovablePositions(ChessBoard chessBoard) {
        Direction direction = getLinearDirections().get(0);
        int xDegree = direction.getXDegree();
        int yDegree = direction.getYDegree();

        List<Position> movablePositions = new ArrayList<>(
            getLinearMovablePositions(chessBoard, xDegree, yDegree));
        return Collections.unmodifiableList(movablePositions);
    }

    private List<Position> getLinearMovablePositions(ChessBoard chessBoard, int xDegree,
        int yDegree) {
        List<Position> movablePositions = new ArrayList<>();
        if (chessBoard.hasNextBlankSquare(position, xDegree, yDegree)) {
            Position nextPosition = new Position(position.getRow() + yDegree,
                position.getColumn() + xDegree);
                movablePositions.add(nextPosition);
            if (isStartingPosition()
                && chessBoard.hasNextBlankSquare(nextPosition, xDegree, yDegree)) {
                nextPosition = new Position(nextPosition.getRow() + yDegree,
                    nextPosition.getColumn() + xDegree);
                movablePositions.add(nextPosition);
            }
        }
        return Collections.unmodifiableList(movablePositions);
    }

    private List<Position> getDiagonalMovablePositions(ChessBoard chessBoard) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = getDiagonalDirections();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movablePositions.addAll(getDiagonalMovablePositions(chessBoard, xDegree, yDegree));
        }
        return Collections.unmodifiableList(movablePositions);
    }

    private List<Position> getDiagonalMovablePositions(ChessBoard chessBoard, int xDegree,
        int yDegree) {
        List<Position> movablePositions = new ArrayList<>();
        if (chessBoard.hasNextPossibleSquare(position, xDegree, yDegree)) {
            Position nextPosition = new Position(position.getRow() + yDegree,
                position.getColumn() + xDegree);
            if (chessBoard.isAttackMove(this, nextPosition)) {
                movablePositions.add(nextPosition);
            }
        }
        return Collections.unmodifiableList(movablePositions);
    }


    private List<Direction> getLinearDirections() {
        if (isBlack()) {
            return Direction.blackPawnLinearDirection();
        }
        return Direction.whitePawnLinearDirection();
    }

    private List<Direction> getDiagonalDirections() {
        if (isBlack()) {
            return Direction.blackPawnDiagonalDirection();
        }
        return Direction.whitePawnDiagonalDirection();
    }

    private boolean isStartingPosition() {
        if (isBlack()) {
            return this.position.getRow() == 1;
        }
        return this.position.getRow() == 6;
    }
}
