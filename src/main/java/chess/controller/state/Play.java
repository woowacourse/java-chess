package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.dto.PlayDto;
import chess.model.game.ChessGame;

public class Play implements GameState {

    private final ChessGame chessGame;

    public Play(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameState execute(final PlayDto request) {
        final GameCommand gameCommand = request.getGameCommand();

        validateGameCommand(gameCommand);

        try {
            return execute(request, gameCommand);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("존재하지 않는 체스 칸을 지정했습니다.", e);
        }
    }

    private GameState execute(final PlayDto request, final GameCommand gameCommand) {
        if (gameCommand.isMove()) {
            move(request);

            return this;
        }
        return new End();
    }

    private void move(final PlayDto request) {
        chessGame.move(request.getSource(), request.getTarget());
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    @Override
    public boolean isPlay() {
        return true;
    }
}
