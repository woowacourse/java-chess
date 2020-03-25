package chess.domains.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Board {
    private final Set<PlayingPiece> board = BoardFactory.getBoard();

    public List<PlayingPiece> showBoard() {
        List<PlayingPiece> showingBoard = new ArrayList<>(board);
        showingBoard.sort(PlayingPiece::compareTo);
        return showingBoard;
    }
}
