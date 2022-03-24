package chess;

import java.util.List;

import chess.domain.Board;
import chess.domain.position.Square;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void run() {
        OutputView.announceStart();
        Board board = new Board();
        boolean start = false;

        while (true) {
            List<String> input = InputView.requireCommand();
            String command = input.get(0);
            if ("start".equals(command)) {
                start = true;
                board = new Board();
                OutputView.showBoard(board.splitByRank());
            }

            if(!start && "move".equals(command)){
                OutputView.printMessage("[ERROR] 게임이 시작되지 않았습니다\n");
            }
            if (start && "move".equals(command)) {
                try {
                    String source = input.get(1);
                    String target = input.get(2);
                    board.move(new Square(source),new Square(target));
                    OutputView.showBoard(board.splitByRank());
                } catch (IllegalArgumentException e) {
                    OutputView.printMessage(e.getMessage());
                }
            }

            if ("end".equals(command)){
                return;
            }
        }
    }
}
