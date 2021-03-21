package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandRouter;
import chess.domain.ChessGameManager;
import chess.domain.GameStatus;
import chess.util.Repeater;
import chess.view.InputView;
import chess.view.OutputView;

public class Controller {
    public static void run() {
        OutputView.printInitialMessage();
        ChessGameManager chessGameManager = new ChessGameManager();
        Command command;
        do {
            command = Repeater.repeatOnError(() -> CommandRouter.findByInputCommand(InputView.getCommand()));
            executeCommandOrPassOnError(chessGameManager, command);
        } while(!command.isEnd() && chessGameManager.getGameStatus() != GameStatus.END);
        // TODO
        // 분기 1. 사용자가 시작도 안 하고 종료를 눌러버린 경우, 아무 것도 출력하지 않고 끝
        // 분기 2. 점수차 혹은 킹이 잡혔는지의 여부에 따라 승자를 출력하고 종료
    }

    private static void executeCommandOrPassOnError(ChessGameManager chessGameManager, Command command) {
        try {
            command.execute(chessGameManager);
        } catch (RuntimeException e) {
            OutputView.printMessage(e.getMessage());
        }
    }
}
