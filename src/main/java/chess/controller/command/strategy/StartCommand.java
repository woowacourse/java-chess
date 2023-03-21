package chess.controller.command.strategy;

import chess.controller.ChessState;
import chess.service.ChessService;
import chess.view.OutputView;

public class StartCommand implements StrategyCommand {

    private StartCommand() {
    }

    public static StartCommand create() {
        return new StartCommand();
    }

    @Override
    public ChessState execute(final ChessState state, final ChessService chessService) {
        if (state == ChessState.INIT) {
            OutputView.printBoard(chessService.getBoard());
            return ChessState.START;
        }

        throw new IllegalArgumentException("게임 진행 도중에 다시 초기화할 수 없습니다.");
    }
}
