package controller.command;

import domain.chessGame.ChessBoard;
import domain.position.Position;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Move extends GameCommand {

    private final ChessBoard chessBoard;
    private final List<String> commandInput;

    public Move(ChessBoard chessBoard, List<String> commandInput) {
        this.chessBoard = chessBoard;
        this.commandInput = commandInput;
    }

    @Override
    public Command execute() {
        Position start = Position.of(commandInput.get(1));
        Position end = Position.of(commandInput.get(2));

        chessBoard.movePiece(start, end);
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
        return readNextCommand();
    }

    @Override
    public Command readNextCommand() {
        List<String> commandInput = receiveGameCommandInput();
        GameCommand.GameCommandType commandType = GameCommand.GameCommandType.valueOf(commandInput.get(0).toUpperCase());

        if (commandType == GameCommand.GameCommandType.MOVE) {
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
