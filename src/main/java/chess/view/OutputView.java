package chess.view;

import java.util.List;
import java.util.Map;

public interface OutputView {
    void printBoard(List<String> positions, Map<String, String> board);
}
