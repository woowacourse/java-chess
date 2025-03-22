package chess.piece;

import chess.Color;
import chess.board.BoardVector;
import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean isMovable(Position startPoint, Position destination) {
        BoardVector boardVector = BoardVector.createVector(startPoint, destination);
        int absDx = boardVector.getAbsDx();
        int absDy = boardVector.getAbsDy();
        return !(absDx != 0 && absDy != 0);
    }

    @Override
    public List<Position> createAllPaths(Position startPoint, Position destination) {
        List<Position> paths = new ArrayList<>();
        BoardVector boardVector = BoardVector.createVector(startPoint, destination);

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
