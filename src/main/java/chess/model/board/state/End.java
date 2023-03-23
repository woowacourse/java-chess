package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.Scores;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class End implements GameState {

    @Override
    public GameState changeState(final GameCommand gameCommand) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(final GameCommand gameCommand, final Position source, final Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }

    @Override
    public GameState isGameEnd() {
        return this;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Scores calculateScores() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException();
    }
}
