package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.dto.CommandDto;

public final class Status extends DefaultState {
    public Status(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (chessGame.isFinished()) {
            return new ExitFinished(chessGame);
        }
        return new Play(chessGame).execute(commandDto);
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
