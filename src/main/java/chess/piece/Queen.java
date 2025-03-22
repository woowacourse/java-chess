package chess.piece;

import chess.Color;
import chess.board.BoardVector;
import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean isMovable(Position startPoint, Position destination) {
        BoardVector boardVector = BoardVector.createVector(startPoint, destination);
        int absDx = boardVector.getAbsDx();
        int absDy = boardVector.getAbsDy();

        return (absDx == absDy) || !(absDx != 0 && absDy != 0);
    }

    @Override
    public List<Position> createAllPaths(Position startPoint, Position destination) {
        BoardVector boardVector = BoardVector.createVector(startPoint, destination);
        int absDx = boardVector.getAbsDx();
        int absDy = boardVector.getAbsDy();

        if (absDx == absDy) {
            return createDiagonalPaths(boardVector, startPoint);
        }

        return createCoordinate(boardVector, startPoint);
    }

    private List<Position> createDiagonalPaths(BoardVector boardVector, Position startPoint) {
        List<Position> paths = new ArrayList<>();
        Position path = startPoint;
        if (boardVector.isOneQuadrant()) {
            for (int i = 0; i < boardVector.dx() - 1; i++) {
                path = path.moveRightUp();
                paths.add(path);
            }
        }
        if (boardVector.isTwoQuadrant()) {
            for (int i = 0; i < boardVector.dx(); i++) {
                path = path.moveLeftUp();
                paths.add(path);
            }
        }
        if (boardVector.isThreeQuadrant()) {
            for (int i = 0; i < boardVector.dx(); i++) {
                path = path.moveLeftDown();
                paths.add(path);
            }
        }
        if (boardVector.isFourQuadrant()) {
            for (int i = 0; i < boardVector.dx(); i++) {
                path = path.moveRightDown();
                paths.add(path);
            }
        }
        return paths;
    }

    private List<Position> createCoordinate(BoardVector boardVector, Position startPoint) {
        List<Position> paths = new ArrayList<>();
        Position path = startPoint;
        if (boardVector.isDxZero()) {
            if (isPositive(boardVector.dy())) {
                while (path.canMoveUp()) {
                    path = path.moveUp();
                    paths.add(path);
                }
                return paths;
            }
            while (path.canMoveDown()) {
                path = path.moveDown();
                paths.add(path);
            }
            return paths;
        }

        if (isPositive(boardVector.dx())) {
            while (path.canMoveRight()) {
                path = path.moveRight();
                paths.add(path);
            }
            return paths;
        }
        while (path.canMoveLeft()) {
            path = path.moveLeft();
            paths.add(path);
        }
        return paths;
    }

    private boolean isPositive(int delta) {
        return delta > 0;
    }
}
