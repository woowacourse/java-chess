package chess.domain.gamestatus;

import java.util.List;

public class Running extends Started {
    @Override
    public List<List<String>> getBoard() {
        return super.board.getBoard();
    }
}
