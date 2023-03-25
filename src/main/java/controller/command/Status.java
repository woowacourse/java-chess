package controller.command;

import domain.chessGame.ChessBoard;
import domain.chessGame.ScoreCalculator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Status extends GameCommand {

    private final ChessBoard chessBoard;

    public Status(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    @Override
    public Command execute() {
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard.getBlackPieces(), chessBoard.getWhitePieces());
        OutputView.printStatusResult(scoreCalculator.getBlackScore(), scoreCalculator.getWhiteScore());
        return readNextCommand();
    }

    @Override
    public Command readNextCommand() {
        List<String> commandInput = receiveGameCommandInput();
        GameCommand.GameCommandType commandType = GameCommand.GameCommandType.valueOf(commandInput.get(0).toUpperCase());

        if (commandType == GameCommand.GameCommandType.MOVE) {
            return new Move(chessBoard, commandInput);
        }
        if (commandType == GameCommand.GameCommandType.STATUS) {
            return new Status(chessBoard);
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
