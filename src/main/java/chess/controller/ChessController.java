package chess.controller;

import static chess.controller.ChessStartCommand.START;

import chess.controller.dto.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private final ChessGame chessGame = new ChessGame();

    public void run() {
        outputView.printStartMessage();
        final ChessStartCommand chessStartCommand = ChessStartCommand.from(inputView.readChessExecuteCommand());
        if (chessStartCommand == START) {
            outputView.printChessBoard(ChessBoardDto.from(chessGame));
            playChessGame();
        }
    }

    private void playChessGame() {
        ChessGameCommand chessGameCommand = ChessGameCommand.from(inputView.readChessGameCommand());
        while (chessGameCommand.getChessExecuteCommand() == ChessExecuteCommand.MOVE) {
            chessGame.move(chessGameCommand.getSource(), chessGameCommand.getDestination());
            outputView.printChessBoard(ChessBoardDto.from(chessGame));
            chessGameCommand = ChessGameCommand.from(inputView.readChessGameCommand());
        }
    }
}
