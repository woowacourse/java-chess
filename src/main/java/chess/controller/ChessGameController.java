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

            //ready +  + fininshed 상태일때만 호출 가능 -> 개별구현
            if (command == ChessGameCommand.START) {
                chessGame.start(); // Ready -> new Board -> Running
                outputView.printBoard(chessGame.board().getValue());
            }

            //-> 이미 Running상태------------------------

            //  + Running +    상태일대만 move  ->
            if (command == ChessGameCommand.MOVE) {
                final Positions movePositions = Positions.from(rawInputCommand);
                // TODO: getter대신 메세지보내서 처리하기
                chessGame.move(movePositions.get(0), movePositions.get(1));
                outputView.printBoard(chessGame.board().getValue());

                // move결과 -> 결과를 뿌려주긴하는데,
                //     if king 잡 -> new Finished()  -> 최종결과 print
                //     king 안잡 -> 그대로 this( Running())
                if (chessGame.isFinished()) {
                    printResult(chessGame);
                }
            }
            //ready + running 상태일때만    finished(x)
            if (command == ChessGameCommand.END) {
                if (chessGame.isFinished()) {
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
