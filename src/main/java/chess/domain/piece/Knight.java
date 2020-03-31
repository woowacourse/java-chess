package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Knight extends OrdinaryMovement {

    private static List<Position> originalPositions = Arrays.asList(Position.of(Column.B, Row.ONE), Position.of(Column.G, Row.ONE));

    public Knight(PlayerColor playerColor) {
        super("n", Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW), 1, 2.5, playerColor);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
