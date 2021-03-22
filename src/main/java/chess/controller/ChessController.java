package chess.controller;

import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.manager.ChessManager;
import chess.manager.Menu;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController() {
        chessManager = new ChessManager();
    }

    public void run() {
        OutputView.getNewGameCommand();
        firstCommand();
        String userInput;
        do {
            userInput = InputView.getUserCommand();
            commandExecute(userInput);
        } while (!chessManager.isEnd() && !Menu.of(userInput).isEnd());

        OutputView.printGameResult(chessManager.calculateStatus());
    }

    private void firstCommand() {
        Menu menu = initFirstCommand();
        if (menu.isStart()) {
            OutputView.printBoard(chessManager.getBoard());
        }
        if (menu.isEnd()) {
            OutputView.printEndGame();
        }
    }

    private Menu initFirstCommand() {
        try {
            String userInput = InputView.getUserCommand();
            Menu menu = Menu.of(userInput);
            if (!menu.isStart() && !menu.isEnd()) {
                throw new IllegalArgumentException("첫 입력은 start(게임시작) 또는 end(게임종료)만 가능합니다.");
            }
            return menu;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return initFirstCommand();
        }
    }

    private void commandExecute(final String input) {
        final Menu menu = Menu.of(input);

        // XXX :: ENUM 고민

        if (menu.isStart()) {
            chessManager.resetBoard();
            OutputView.printBoard(chessManager.getBoard());
        }

        if (menu.isMove()) {
            movePiece(MoveCommand.of(input));
        }

        if (menu.isShow()) {
            showAblePositionToMove(ShowCommand.of(input));
        }

        if (menu.isStatus()) {
            OutputView.printStatus(chessManager.calculateStatus());
        }
    }

    private void movePiece(final MoveCommand command) {
        chessManager.move(command);
        OutputView.printBoard(chessManager.getBoard());
    }

    private void showAblePositionToMove(final ShowCommand command) {
        OutputView.printAbleToMove(chessManager.getBoard(), chessManager.getReachablePositions(command));
    }
}
