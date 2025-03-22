package chess.piece;

import chess.Color;
import chess.board.BoardVector;
import chess.board.Position;
import java.util.List;

public class Pawn extends Piece {

    private boolean isMoved;

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(Position startPoint, Position destination) {
        BoardVector boardVector = BoardVector.createVector(startPoint, destination);
        if (color == Color.BLACK) {
            if (!isMoved) {
                isMoved = true;
                return boardVector.dy() == -2 && boardVector.dx() == 0;
            }
            return boardVector.dy() == -1 && boardVector.dx() == 0;
        }

        if (color == Color.WHITE) {
            if (!isMoved) {
                isMoved = true;
                return boardVector.dy() == 2 && boardVector.dx() == 0;
            }
            return boardVector.dy() == 1 && boardVector.dx() == 0;
        }

        throw new IllegalStateException("[ERROR] 현재 기물의 색이 정해져있지 않습니다");
    }

    @Override
    public List<Position> createAllPaths(Position startPoint, Position destination) {
        return List.of();
    }
}
