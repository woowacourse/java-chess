package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.service.ChessService;
import chess.view.OutputView;
import java.util.Map;

public abstract class ProgressState implements GameState {

    protected final ChessService chessService;

    public ProgressState(final ChessService chessService) {
        this.chessService = chessService;
    }

    public static GameState of(final GameCommand gameCommand, final ChessService chessService) {
        if (gameCommand.isMove()) {
            throw new IllegalArgumentException("시작하기 전에 move를 호출 할 수 없습니다.");
        }

        if (gameCommand.isEnd()) {
            return new End();
        }

        return createProgressState(gameCommand, chessService);
    }

    private static ProgressState createProgressState(final GameCommand gameCommand, final ChessService chessService) {
        chessService.loadGame();

        if (gameCommand.isStatus()) {
            return new Status(chessService);
        }

        return new Playing(chessService);
    }

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
            return new Status(chessService);
        }

        return new Playing(chessService);
    }

    @Override
    public final boolean isNotEnd() {
        return true;
    }

    @Override
    public final GameState isGameEnd() {
        if ((chessService.isGameEnd())) {
            return new End();
        }

        return this;
    }

    public abstract void printScores(OutputView outputView);

    @Override
    public final Map<Position, Piece> getBoard() {
        return chessService.getBoard();
    }
}
