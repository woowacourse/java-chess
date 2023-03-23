package chess;

import chess.controller.ChessController;
import chess.dao.chessGameDao;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Scanner;

public final class Application {

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final var controller = new ChessController(
                new InputView(scanner),
                new OutputView(),
                new ChessGame(),
                new chessGameDao());
        controller.play();
    }
}
