package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.DiagonalMove;
import chess.domain.chessmove.HorizontalMove;
import chess.domain.chessmove.VerticalMove;

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
        movingMap.put("horizontal", HorizontalMove.getInstance());
        movingMap.put("vertical", VerticalMove.getInstance());
        movingMap.put("diagonal", DiagonalMove.getInstance());
    }

    @Override
    public List<Position> getRouteOfPiece(Position source, Position target) {
        String moveName = "diagonal";

        if (source.isInLine(target) == VERTICAL_LINE) {
            moveName = "vertical";
        }
        if (source.isInLine(target) == HORIZONTAL_LINE) {
            moveName = "horizontal";
        }

        return movingMap.get(moveName).move(source, target);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
