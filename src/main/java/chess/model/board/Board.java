package chess.model.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Square> squares;

    public Board(List<Square> squares) {
        this.squares = new ArrayList<>(squares);
    }

    public List<String> getSignatures() {
        return squares.stream()
                .map(Square::getPieceSignature)
                .toList();
    }
}
