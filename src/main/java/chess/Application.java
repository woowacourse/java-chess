package chess;

import chess.machine.ChessMachine;
import chess.repository.ChessBoardRepositoryImpl;
import chess.service.ChessBoardService;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessMachine chessMachine = new ChessMachine(
                new OutputView(),
                new InputView(),
                new ChessBoardService(new ChessBoardRepositoryImpl()));
        chessMachine.run();
    }
}
