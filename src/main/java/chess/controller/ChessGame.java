package chess.controller;

import chess.Board;
import chess.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private Board board = new Board();
    public void run() {
        OutputView.printGameIntro();
        Command command = new Command(InputView.requestCommand());
        if(command.isStart()){
            OutputView.printBoard(board);
        }
    }
}
