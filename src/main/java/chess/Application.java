package chess;

import chess.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView.printGameStartMessage();

        String playerCommand = InputView.inputPlayerCommand();
        while ("start".equals(playerCommand)) {
            //todo : 체스판 출력
            playerCommand = InputView.inputPlayerCommand();
        }
    }
}
