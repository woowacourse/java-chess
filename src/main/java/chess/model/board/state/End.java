package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.piece.type.Piece;
import chess.model.position.Position;
import java.util.Map;

public class End implements GameState {

    @Override
    public GameState execute(final GameCommand gameCommand, final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException();
    }
}
