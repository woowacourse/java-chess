package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.domain.piece.Direction.*;

public class King extends GamePiece {

    private static final List<Direction> directions = Arrays.asList(N, NE, E, SE, S, SW, W, NW);
    private static final int moveCount = 1;

    private final OrdinaryMovement moveStrategy;

    public King(PlayerColor playerColor) {
        super("k", Collections.singletonList(Position.of(Column.E, Row.ONE)),
                0, playerColor);
        this.moveStrategy = new OrdinaryMovement(directions, moveCount);
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        return moveStrategy.canMove(board, source, target);
    }
}
