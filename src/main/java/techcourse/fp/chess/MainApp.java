package techcourse.fp.chess;

import java.util.Scanner;
import techcourse.fp.chess.controller.ChessController;
import techcourse.fp.chess.view.InputView;
import techcourse.fp.chess.view.OutputView;

//TODO: 3단계 README.md 추가
public final class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final ChessController chessController = new ChessController(new InputView(scanner), new OutputView());
            chessController.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
