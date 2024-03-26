package chess;

import chess.controller.ChessGameController;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessApplication {

    //TODO: 모든 메서드 테스트 하기
    //TODO : abstract 큻래스 테스트하기 1. 구현체로, 2. 빈 ㄱ개체 하나만들어서

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ChessGameController chessGameController = new ChessGameController(inputView, outputView);
        chessGameController.run();
    }
}
