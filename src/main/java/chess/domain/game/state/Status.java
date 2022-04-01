package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.dto.CommandDto;

public final class Status extends AbstractState {
    public Status(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (chessGame.isPlaying()) {
            return new Play(chessGame).execute(commandDto);
        }
        return new ExitFinished(chessGame);
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
