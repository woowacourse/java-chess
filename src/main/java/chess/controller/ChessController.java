package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
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
            OutputView.printChessBoard(chessGame.getBoard());
        }
    }
}
