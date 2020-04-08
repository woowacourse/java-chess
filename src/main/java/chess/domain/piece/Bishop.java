package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.Direction.*;

public class Bishop extends GamePiece {

    private static final List<Direction> directions = Arrays.asList(NE, SE, NW, SW);
    private static final int moveCount = 8;

    private final OrdinaryMovement moveStrategy;

    public Bishop(PlayerColor playerColor) {
        super("b", Arrays.asList(Position.of(Column.C, Row.ONE), Position.of(Column.F, Row.ONE)),
                3, playerColor);
        this.moveStrategy = new OrdinaryMovement(directions, moveCount);
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        return moveStrategy.canMove(board, source, target);
    }
}
