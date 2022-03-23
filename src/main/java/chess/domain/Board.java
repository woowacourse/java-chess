package chess.domain;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Piece>> board;

    public Board() {
        board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Piece> pieces = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                pieces.add(new Rook(i, j, "black"));
            }
            board.add(pieces);
        }
    }

    public Piece getPiece(String position) {
        int x = position.charAt(0) - 'a';
        int y = Character.getNumericValue(position.charAt(1));
        return board.get(x).get(y);
    }
}
