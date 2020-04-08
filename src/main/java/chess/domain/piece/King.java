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

    private static final String NAME = "k";
    private static final int SCORE = 0;
    private static final int MOVE_COUNT = 1;
    private static List<Position> originalPositions = Collections.singletonList(Position.of(Column.E, Row.ONE));

    public King(PlayerColor playerColor) {
        super(NAME, SCORE, playerColor, Arrays.asList(N, NE, E, SE, S, SW, W, NW), MOVE_COUNT);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
