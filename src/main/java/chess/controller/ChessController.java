package chess.controller;

import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.manager.ChessManager;
import chess.manager.Menu;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController(){
        chessManager = new ChessManager();
    }

    public void run() {
        readStart();
        executeUserCommands();
        printResult();
    }

    private void readStart(){
        while(!Menu.of(InputView.getNewGameCommand()).isStart()){}
        OutputView.printBoard(chessManager.getBoard());
    }

    private void executeUserCommands(){
        String userInput;
        do {
            userInput = InputView.getUserCommand();
            commandExecute(userInput);
        } while (!chessManager.isEnd() && !Menu.of(userInput).isEnd());
    }

    private void commandExecute(final String input){
        final Menu menu = Menu.of(input);

        if (menu.isMove()) {
            movePiece(MoveCommand.of(input));
        }

        if (menu.isShow()) {
            showAblePositionToMove(ShowCommand.of(input));
        }

        if (menu.isStatus()) {
            OutputView.printScores(chessManager.calculateStatus());
        }
    }

    private void movePiece(final MoveCommand command){
        chessManager.move(command);
        OutputView.printBoard(chessManager.getBoard());
    }

    private void showAblePositionToMove(final ShowCommand command){
        final List<Position> reachablePositions = chessManager.getReachablePositions(command);
        OutputView.printReachableBoard(chessManager.getBoard(),reachablePositions);
    }

    private void printResult(){
        OutputView.printGameResult(chessManager.calculateStatus());
    }
}
