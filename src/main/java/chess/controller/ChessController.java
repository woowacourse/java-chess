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
        if (isStartCommand()) {
            outputView.printBoard(chessGame.getBoard());
            play();
        }
    }

    private boolean isStartCommand() {
        try {
            return inputView.readStartCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return isStartCommand();
        }
    }

    private void play() {
        boolean isPlaying = true;
        while (isPlaying) {
            isPlaying = move();
            outputView.printBoard(chessGame.getBoard());
        }
    }

    private boolean move() {
        try {
            List<String> input = inputView.readGameCommand();
            GameCommand gameCommand = GameCommand.of(input.get(0));
            if (gameCommand == GameCommand.END) {
                return false;
            }
            List<Position> positions = PositionMapper.from(input);
            chessGame.movePieceTo(positions.get(0), positions.get(1));
            return true;
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return move();
        }
    }
}
