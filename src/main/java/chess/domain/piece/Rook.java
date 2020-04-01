package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Rook extends GamePiece {

    private static List<Position> originalPositions = Arrays.asList(Position.of(Column.A, Row.ONE), Position.of(Column.H, Row.ONE));

    public Rook(PlayerColor playerColor) {
        super("r", 5, playerColor, Arrays.asList(N, E, W, S), 8);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
