package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
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
        boolean isPlaying = true;
        while (isPlaying) {
            outputView.printBoard(chessGame.getBoard());
            isPlaying = move();
        }
    }

    private boolean move() {
        try {
            List<String> input = readInput();
            GameCommand gameCommand = GameCommand.of(input.get(0));
            if (gameCommand == GameCommand.END) {
                return false;
            }
            Position sourcePosition = PositionMapper.from(input.get(1));
            Position targetPosition = PositionMapper.from(input.get(2));
            chessGame.movePiece(sourcePosition, targetPosition);
            return true;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return move();
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

    private void validateCommand(List<String> input) {
        GameCommand.validate(input);
        PositionMapper.validate(input);
    }
}
