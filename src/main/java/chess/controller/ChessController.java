package chess.controller;

import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.manager.ChessManager;
import chess.manager.Menu;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController(){
        chessManager = new ChessManager();
    }

    public void run() {
        if (!Menu.of(InputView.getNewGameCommand()).isStart()) {
            return;
        }
        OutputView.printBoard(chessManager.getBoard());

        String userInput;
        do {
            userInput = InputView.getUserCommand();
            commandExecute(userInput);
        } while (!chessManager.isEnd() && !Menu.of(userInput).isEnd());

        OutputView.printGameResult(chessManager.calculateStatus());
    }

    private void commandExecute(final String input){
        final Menu menu = Menu.of(input);

        // XXX :: ENUM 고민

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

    private void movePiece(final MoveCommand command){
        chessManager.move(command);
        OutputView.printBoard(chessManager.getBoard());
    }

    private void showAblePositionToMove(final ShowCommand command){
        OutputView.printAbleToMove(chessManager.getBoard(), chessManager.getReachablePositions(command));
    }
}
