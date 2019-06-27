package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.DiagonalMove;
import chess.domain.chesspieceMove.HorizontalMove;
import chess.domain.chesspieceMove.VerticalMove;

import java.util.HashMap;
import java.util.List;

public class Queen extends ChessPiece {
    private static final int SCORE = 9;

    private static final int VERTICAL_LINE = 0;
    private static final int HORIZONTAL_LINE = 1;

    public Queen(Team team) {
        super(team);
        initMovingMap();
    }

    @Override
    public void initMovingMap() {
        movingMap = new HashMap<>();
        movingMap.put(MoveDirection.HORIZONTAL, HorizontalMove.getInstance());
        movingMap.put(MoveDirection.VERTICAL, VerticalMove.getInstance());
        movingMap.put(MoveDirection.DIAGONAL, DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        MoveDirection moveName = MoveDirection.DIAGONAL;

        if (source.isInLine(target) == VERTICAL_LINE) {
            moveName = MoveDirection.VERTICAL;
        }
        if (source.isInLine(target) == HORIZONTAL_LINE) {
            moveName = MoveDirection.HORIZONTAL;
        }

        return movingMap.get(moveName).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
