package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.Collections;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Queen extends OrdinaryMovement {

    public Queen(PlayerColor playerColor) {
        super("q", Collections.singletonList(Position.of(Column.D, Row.ONE)),
                Arrays.asList(N, NE, E, SE, S, SW, W, NW), 8, 9, playerColor);
    }
}
