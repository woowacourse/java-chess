package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Rook extends OrdinaryMovement {

    private static List<Position> originalPositions = Arrays.asList(Position.of(Column.A, Row.ONE), Position.of(Column.H, Row.ONE));

    public Rook(PlayerColor playerColor) {
        super("r", Arrays.asList(N, E, W, S), 8, 5, playerColor);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
