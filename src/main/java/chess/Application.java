package chess;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView.printGameStartMessage();
        String playerCommand = InputView.inputPlayerCommand();

        Board board = Board.getInstance();
        board.initialize();
        while ("start".equals(playerCommand)) {
            //todo : 체스판 출력
            OutputView.printBoard(board);
            playerCommand = InputView.inputPlayerCommand();
        }
    }
}
