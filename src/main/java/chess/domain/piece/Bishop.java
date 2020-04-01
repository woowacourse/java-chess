package chess.domain.piece;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

public class Bishop extends GamePiece {

    private static List<Position> originalPositions = Arrays.asList(Position.of(Column.C, Row.ONE), Position.of(Column.F, Row.ONE));

    public Bishop(PlayerColor playerColor) {
        super("b", 3, playerColor, Arrays.asList(NE, SE, NW, SW), 8);
    }

    @Override
    public List<Position> getOriginalPositions() {
        return playerColor.reviseInitialPositions(originalPositions);
    }
}
