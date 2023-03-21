package chess.model.game.state;

import chess.controller.PlayRequest;
import chess.model.game.ChessGame;
import chess.model.game.GameCommand;

public class Ready implements GameState {

    private final ChessGame chessGame;

    public Ready(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameState execute(final PlayRequest request) {
        final GameCommand gameCommand = convertGameCommand(request);

        validateGameCommand(gameCommand);
        return execute(gameCommand);
    }

    private GameCommand convertGameCommand(final PlayRequest request) {
        final String command = request.getCommand();

        return GameCommand.findGameCommand(command);
    }

    private GameState execute(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            chessGame.initialChessGame();
            return new Play(chessGame);
        }
        return new End();
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isMove()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    @Override
    public boolean isPlay() {
        return false;
    }
}
