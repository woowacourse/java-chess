package chess.controller;

import chess.domain.ChessGame;
import chess.domain.Point;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.view.InputView.STATUS;

public class ChessController {
    public void run() {
        OutputView.printStartGuideMessage();

        while (InputView.inputStart()) {
            ChessGame chessGame = new ChessGame();
            OutputView.printBoard(chessGame);

            while (chessGame.isNotEnd()) {
                List<String> userInput = InputView.inputMoveOrStatus();
                if(userInput.get(0).equals(STATUS)) {
                    System.out.println(chessGame.calculateScore());
                    continue;
                }

                Point source = Point.of(userInput.get(0));
                Point target = Point.of(userInput.get(1));
                chessGame.playTurn(source, target);
                OutputView.printBoard(chessGame);
            }
            System.out.println("게임이 끝났습니다. 새로운 게임을 시작하시려면 start, 종료를 원하시면 end를 입력하세요.");
        }
    }
}
