package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.Direction.*;

public class Knight extends GamePiece {

    private static final List<Direction> directions = Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);
    private static final int moveCount = 1;

    private final OrdinaryMovement moveStrategy;

    public Knight(PlayerColor playerColor) {
        super("n", Arrays.asList(Position.of(Column.B, Row.ONE), Position.of(Column.G, Row.ONE)),
                2.5, playerColor);
        this.moveStrategy = new OrdinaryMovement(directions, moveCount);
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        return moveStrategy.canMove(board, source, target);
    }
}
