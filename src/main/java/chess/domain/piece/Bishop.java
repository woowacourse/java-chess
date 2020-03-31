package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Bishop extends OrdinaryMovement {

    private static List<Position> originalPositions = Arrays.asList(Position.of(Column.C, Row.ONE), Position.of(Column.F, Row.ONE));

    public Bishop(PlayerColor playerColor) {
        super("b", Arrays.asList(NE, SE, NW, SW), 8, 3, playerColor);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
