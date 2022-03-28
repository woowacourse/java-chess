package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Positions;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame();

        while (true) {
            final String rawInputCommand = inputView.inputCommand();

            final ChessGameCommand command = ChessGameCommand.from(rawInputCommand);
            //ready상태일때만
            if (command == ChessGameCommand.START) {
                chessGame.start();
                outputView.printBoard(chessGame.getBoard().getValue());
            }
            //running상태일대만
            if (command == ChessGameCommand.MOVE) {
                final Positions movePositions = Positions.from(rawInputCommand);
                // TODO: getter대신 메세지보내서 처리하기
                chessGame.move(movePositions.get(0), movePositions.get(1));
                outputView.printBoard(chessGame.getBoard().getValue());
                if (!chessGame.isRunning()) {
                    printResult(chessGame);
                }
            }
            //ready + running 상태일때만    finished(x)
            if (command == ChessGameCommand.END) {
                if (!chessGame.isRunning()) {
                    break;
                }
                chessGame.end();
                printResult(chessGame);
            }
            //ready(X) running + finished 둘다 가능
            if (command == ChessGameCommand.STATUS) {
                outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
            }
        }
    }

    private void printResult(ChessGame chessGame) {
        outputView.printFinishMessage();
        outputView.printStatus(chessGame.statusOfWhite(), chessGame.statusOfBlack());
        if (chessGame.hasBlackWon() > 0) {
            outputView.printBlackWin();
        }
        if (chessGame.hasBlackWon() < 0) {
            outputView.printWhiteWin();
        }
        if (chessGame.hasBlackWon() == 0) {
            outputView.printDraw();
        }
    }
}
