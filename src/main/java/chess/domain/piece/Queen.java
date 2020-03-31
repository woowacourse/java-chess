package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Queen extends OrdinaryMovement {

    private static List<Position> originalPositions = Collections.singletonList(Position.of(Column.D, Row.ONE));

    public Queen(PlayerColor playerColor) {
        super("q", Arrays.asList(N, NE, E, SE, S, SW, W, NW), 8, 9, playerColor);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
