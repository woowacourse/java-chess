package chess.domain.command;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public final class End extends Command {
    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Command execute(String command) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StatusResult getStatus() {
        throw new UnsupportedOperationException();
    }
}
