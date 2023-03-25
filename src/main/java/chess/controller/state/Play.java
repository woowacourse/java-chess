package chess.controller.state;

import chess.controller.GameCommand;
import chess.dao.ChessMovementDao;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import java.util.List;

public final class Play implements GameState {

    private static final int TARGET_INDEX = 1;
    private static final int SOURCE_INDEX = 0;

    private final ChessGame chessGame;
    private final ChessMovementDao chessMovementDao;

    Play(final ChessGame chessGame, final ChessMovementDao chessMovementDao) {
        this.chessGame = chessGame;
        this.chessMovementDao = chessMovementDao;
    }

    @Override
    public GameState execute(final GameCommand gameCommand, final List<Position> positions) {
        validateGameCommand(gameCommand);

        try {
            return handleGameCommand(gameCommand, positions);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("존재하지 않는 체스 칸을 지정했습니다.", e);
        }
    }

    private GameState handleGameCommand(final GameCommand gameCommand, final List<Position> positions) {
        if (gameCommand.isMove()) {
            return handleMove(positions.get(SOURCE_INDEX), positions.get(TARGET_INDEX));
        }
        return new End();
    }

    private GameState handleMove(final Position source, final Position target) {
        chessGame.move(source, target);
        chessMovementDao.save(source, target);

        if (chessGame.isGameOnGoing()) {
            return this;
        }
        chessMovementDao.delete();
        return new Result();
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (isInvalidCommand(gameCommand)) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
    }

    private boolean isInvalidCommand(final GameCommand gameCommand) {
        return gameCommand.isStart() || gameCommand.isStatus() || gameCommand.isLoad();
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
