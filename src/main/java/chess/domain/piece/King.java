package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class King extends GamePiece {

    private static List<Position> originalPositions = Collections.singletonList(Position.of(Column.E, Row.ONE));

    public King(PlayerColor playerColor) {
        super("k", 0, playerColor, Arrays.asList(N, NE, E, SE, S, SW, W, NW), 1);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
