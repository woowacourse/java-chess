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

public class Queen extends GamePiece {

    private static final List<Direction> directions = Arrays.asList(N, NE, E, SE, S, SW, W, NW);
    private static final int moveCount = 8;

    private final OrdinaryMovement moveStrategy;

    public Queen(PlayerColor playerColor) {
        super("q", Collections.singletonList(Position.of(Column.D, Row.ONE)),
                9, playerColor);
        this.moveStrategy = new OrdinaryMovement(directions, moveCount);
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        return moveStrategy.canMove(board, source, target);
    }
}
