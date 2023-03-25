package controller.command;

import domain.chessGame.ChessBoard;
import domain.chessGame.ChessBoardGenerator;
import domain.position.Position;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Initialize extends GameCommand {

    private final ChessBoard chessBoard;

    public Initialize() {
        this.chessBoard = setUpChessBoard();
    }

    @Override
    public Command execute() {
        return readNextCommand();
    }

    private ChessBoard setUpChessBoard() {
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
        return chessBoard;
    }

    @Override
    public Command readNextCommand() {
        List<String> commandInput = receiveGameCommandInput();
        GameCommandType commandType = GameCommandType.valueOf(commandInput.get(0).toUpperCase());

        if (commandType == GameCommandType.MOVE) {
            return new Move(chessBoard, commandInput);
        }
        return new GameEnd(chessBoard);
    }

    private List<String> receiveGameCommandInput() {
        List<String> userInput = InputView.readUserInput();
        while (!isGameCommands(userInput.get(0))) {
            OutputView.printNotGameCommandMessage();
            userInput = InputView.readUserInput();
        }
        return userInput;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
