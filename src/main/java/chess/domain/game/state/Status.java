package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;

public final class Status implements State {
    private final ChessGame chessGame;
    public Status(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (chessGame.isFinished()) {
            return new End(chessGame);
        }
        return new Play(chessGame).execute(commandDto);
    }

    public StateType getType() {
        return StateType.STATUS;
    }
}
