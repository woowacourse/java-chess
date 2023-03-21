package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PositionMapper;

import java.util.List;

public class ChessController {

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
            play();
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

    private void play() {
        do {
            outputView.printBoard(chessGame.getBoard());
        } while (isPlaying(readCommand()));
    }

    private boolean isPlaying(GameCommand gameCommand) {
        return gameCommand == GameCommand.MOVE;
    }

    /**
        return: 사용자에게 입력된 GameCommand에 해당하는 기능을 동작하고 반환
     */
    private GameCommand readCommand() {
        try {
            List<String> input = readInput();
            return executeCommand(input);
        } catch (IllegalArgumentException e) {
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

    private GameCommand executeCommand(List<String> input) {
        if (GameCommand.of(input.get(0)) == GameCommand.MOVE) {
            chessGame.movePiece(PositionMapper.from(input.get(1)), PositionMapper.from(input.get(2)));
        }
        return GameCommand.of(input.get(0));
    }

    private void validateCommand(List<String> input) {
        GameCommand.validate(input);
        PositionMapper.validate(input);
    }
}
