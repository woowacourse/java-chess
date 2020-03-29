package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Rook extends OrdinaryMovement {

    public Rook(PlayerColor playerColor) {
        super("r", Arrays.asList(Position.of(Column.A, Row.ONE), Position.of(Column.H, Row.ONE)),
                Arrays.asList(N, E, W, S), 8, 5, playerColor);
    }
}
