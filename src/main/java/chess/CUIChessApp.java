package chess;

import chess.view.InputView;
import chess.view.OutputVIew;

public class CUIChessApp {

    public static void main(String[] args) {
        OutputVIew.printInputGuide();
        String input = InputView.promptUserSelection();
        while (!handleUserSelection(input)) {
            input = InputView.promptUserSelection();
        }
    }

    private static boolean handleUserSelection(String input) {
        if (input.equals("start")) {
            OutputVIew.printBoardState();
            return false;
        }
        if (input.equals("end")) {
            System.exit(0);
            return true;
        }
        throw new IllegalArgumentException("알 수 없는 명령입니다");
    }
}
