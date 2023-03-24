package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.domain.game.ChessGame;
import chess.view.OutputView;

public class StartCommand implements StrategyCommand {

    private StartCommand() {
    }

    public static StartCommand create() {
        return new StartCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessGame chessGame) {
        if (state == ChessState.INIT) {
            OutputView.printBoard(chessGame.getBoard());
            return ChessState.START;
        }

        throw new IllegalArgumentException("게임 진행 도중에 다시 초기화할 수 없습니다.");
    }
}
