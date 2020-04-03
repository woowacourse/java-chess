package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Rook extends GamePiece {

    private static final List<Direction> directions = Arrays.asList(N, E, W, S);
    private static final int moveCount = 8;

    private final OrdinaryMovement moveStrategy;

    public Rook(PlayerColor playerColor) {
        super("r", Arrays.asList(Position.of(Column.A, Row.ONE), Position.of(Column.H, Row.ONE)),
                5, playerColor);
        this.moveStrategy = new OrdinaryMovement(directions, moveCount);
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        return moveStrategy.canMove(board, source, target);
    }
}
