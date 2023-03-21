package chess.model.game.state;

import chess.controller.PlayRequest;
import chess.model.game.ChessGame;
import chess.model.game.GameCommand;
import chess.model.position.Position;
import chess.model.position.PositionConverter;

public class Play implements GameState {

    private final ChessGame chessGame;

    public Play(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameState execute(final PlayRequest request) {
        final String command = request.getCommand();
        final GameCommand gameCommand = GameCommand.findGameCommand(command);

        validateGameCommand(gameCommand);

        return execute(request, gameCommand);
    }

    private GameState execute(final PlayRequest request, final GameCommand gameCommand) {
        if (gameCommand.isMove()) {
            move(request);

            return this;
        }
        return new End();
    }

    private void move(final PlayRequest request) {
        final String source = request.getSource();
        final String target = request.getTarget();
        final Position sourcePosition = PositionConverter.convert(source);
        final Position targetPosition = PositionConverter.convert(target);

        chessGame.move(sourcePosition, targetPosition);
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
