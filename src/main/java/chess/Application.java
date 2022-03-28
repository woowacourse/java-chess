package chess;

import chess.controller.ChessController;
import chess.domain.Board;
import chess.domain.Team;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.play();
    }
}
