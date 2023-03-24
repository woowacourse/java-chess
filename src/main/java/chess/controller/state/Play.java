package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.game.ChessGame;
import chess.model.position.Position;

public class Play implements GameState {

    private static final boolean UN_PRINTABLE = false;
    private final ChessGame chessGame;

    public Play(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameState execute(final GameCommand gameCommand, final Position source, final Position target) {
        validateGameCommand(gameCommand);

        try {
            return handleGameCommand(gameCommand, source, target);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("존재하지 않는 체스 칸을 지정했습니다.", e);
        }
    }

    private GameState handleGameCommand(final GameCommand gameCommand, final Position source, final Position target) {
        if (gameCommand.isMove()) {
            return handleMove(source, target);
        }
        return new End();
    }

    private GameState handleMove(final Position source, final Position target) {
        chessGame.move(source, target);

        if (chessGame.isGameOnGoing()) {
            return this;
        }
        return new Result(UN_PRINTABLE);
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
        if (gameCommand.isStatus()) {
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

    @Override
    public boolean isPrintable() {
        return true;
    }
}
