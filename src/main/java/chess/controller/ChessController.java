package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        runByCommend();
    }

    private void runByCommend() {
        ChessGame chessGame = new ChessGame();
        while (true) {
            String commend = InputView.inputCommend();
            if (commend.equals("end")) {
                break;
            }
            if (commend.contains("move")) {
                String[] positions = commend.substring(5).split(" ");
                chessGame.play(Position.from(positions[0]), Position.from(positions[1]));
            }
            if (commend.equals("status")) {
                OutputView.printStatus(chessGame.getStatus());
            }
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }
}
