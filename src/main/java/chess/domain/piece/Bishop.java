package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.CommonMoveStrategy;
import chess.domain.movestrategy.MoveStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {

    private static final double SCORE = 3;

    private Bishop(Color color) {
        super(color, Symbol.BISHOP);
    }

    public static Bishop createBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop createWhite() {
        return new Bishop(Color.WHITE);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        return new ArrayList<>(Arrays.asList(
            position.leftUpVector(), position.leftDownVector(),
            position.rightUpVector(), position.rightDownVector()));
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new CommonMoveStrategy();
    }

    @Override
    public double score() {
        return SCORE;
    }
}
