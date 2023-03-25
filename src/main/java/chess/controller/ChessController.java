package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionMapper;

import java.util.List;

public class ChessController {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    
    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGame chessGame;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.chessGame = new ChessGame(BoardFactory.createBoard());
    }

    public void run() {
        outputView.printStart();
        if (isReadStartCommand()) {
            playChessGame();
        }
    }

    private boolean isReadStartCommand() {
        try {
            return inputView.readStartCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return isReadStartCommand();
        }
    }

    private void playChessGame() {
        do {
            outputView.printBoard(chessGame.getBoard());
        } while (readCommand() == CommandType.MOVE);
    }

    /**
     * return: 사용자에게 입력된 GameCommand에 해당하는 기능을 동작하고 반환
     */
    private CommandType readCommand() {
        try {
            List<String> input = readInput();
            return executeCommand(input);
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            outputView.printError(e.getMessage());
            return readCommand();
        }
    }

    private List<String> readInput() {
        try {
            List<String> input = inputView.readGameCommand();
            validateCommand(input);
            return input;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readInput();
        }
    }

    private CommandType executeCommand(List<String> input) {
        CommandType commandType = CommandType.from(input);
        if (commandType == CommandType.MOVE) {
            chessGame.movePiece(PositionMapper.from(input.get(SOURCE_POSITION_INDEX)),
                    PositionMapper.from(input.get(TARGET_POSITION_INDEX)));
        }
        return commandType;
    }

    private void validateCommand(List<String> input) {
        PositionMapper.validate(input);
    }
}
