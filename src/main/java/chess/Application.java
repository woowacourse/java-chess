package chess;

import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {

        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        Command command = null;

        ChessGame chessGame = new ReadyChessGame();
        do {
            List<String> inputCommand = inputView.inputCommand();
            command = Command.parseCommand(inputCommand.get(0));

            if(command == Command.START){
                chessGame = chessGame.start();
                outputView.printBoard(chessGame.getBoard());
            }
            if (command == Command.MOVE) {
                outputView.printBoard(chessGame.move(inputCommand.get(1), inputCommand.get(2)));
            }
            if(command == Command.END){
                chessGame.end();
            }
        } while (!chessGame.isEnd());
    }
}
