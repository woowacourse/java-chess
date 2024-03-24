package chess.view;

import java.util.List;

public class OutputView {

    public void writeBoard(List<String> board) {
        System.out.println(String.join("\n", board));
    }
}
