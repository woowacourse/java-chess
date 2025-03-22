package chess.piece;

import chess.Color;
import chess.board.BoardVector;
import chess.board.Movement;
import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isMovable(Position startPoint, Position destination) {
        BoardVector boardVector = BoardVector.createVector(startPoint, destination);
        int absDx = boardVector.getAbsDx();
        int absDy = boardVector.getAbsDy();
        return (absDx == 1 && absDy == 2) || (absDx == 2 && absDy == 1);
    }

    @Override
    public List<Position> createAllPaths(Position startPoint, Position destination) {
        List<Position> paths = new ArrayList<>();

        List<Movement> coordinateAxis = Movement.getAllCoordinateAxis();
        List<Movement> diagonals = Movement.getAllDiagonal();

        BoardVector boardVector = BoardVector.createVector(startPoint, destination);
        Position current = startPoint;
        for (Movement part : coordinateAxis) {
            for (Movement diagonal : diagonals) {
                if (boardVector.equals(new BoardVector(part.x() + diagonal.x(), part.y() + diagonal.y()))) {
                    current = current.move(part);
                    paths.add(current);
                }
            }
        }

        return paths;
    }

    @Override
    public boolean canJumpOver() {
        return true;
    }
}
