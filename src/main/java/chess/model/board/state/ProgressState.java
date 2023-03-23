package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.ChessGame;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public abstract class ProgressState implements GameState {

    protected final ChessGame chessGame;

    protected ProgressState(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public abstract boolean isStatus();

    @Override
    public final GameState changeState(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            throw new IllegalArgumentException("게임 도중에 start를 입력할 수 없습니다.");
        }

        return modifyState(gameCommand);
    }

    private GameState modifyState(final GameCommand gameCommand) {
        if (gameCommand.isEnd()) {
            return new End();
        }

        if (gameCommand.isStatus()) {
            return new Status(chessGame);
        }

        return new Playing(chessGame);
    }

    @Override
    public final boolean isNotEnd() {
        return true;
    }

    @Override
    public final GameState isGameEnd() {
        if ((chessGame.isGameEnd())) {
            return new End();
        }

        return this;
    }

    @Override
    public final Map<Position, Piece> getBoard() {
        return chessGame.getBoard();
    }
}
