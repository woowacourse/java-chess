package chess.controller;

import static chess.controller.ChessStartCommand.START;

import chess.controller.dto.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final ChessGame chessGame;

    public ChessController(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        outputView.printStartMessage();
        if (readChessStartCommand() == START) {
            outputView.printChessBoard(ChessBoardDto.from(chessGame));
            playChessGame();
        }
    }

    private ChessStartCommand readChessStartCommand() {
        while (true) {
            try {
                return ChessStartCommand.from(inputView.readChessExecuteCommand());
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void playChessGame() {
        while (true) {
            try {
                repeatMove();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void repeatMove() {
        ChessGameCommand chessGameCommand = ChessGameCommand.from(inputView.readChessGameCommand());
        while (chessGameCommand.getChessExecuteCommand() == ChessExecuteCommand.MOVE) {
            chessGame.move(chessGameCommand.getSource(), chessGameCommand.getDestination());
            outputView.printChessBoard(ChessBoardDto.from(chessGame));
            chessGameCommand = ChessGameCommand.from(inputView.readChessGameCommand());
        }
    }
}
