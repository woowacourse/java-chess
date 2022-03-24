package chess.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Row, Rank> board = new EnumMap<>(Row.class);

    public Board() {
        board.put(Row.EIGHT, Rank.createPiecesExceptPawn(Team.BLACK, 8));
        board.put(Row.SEVEN, Rank.createPawn(Team.BLACK, 7));
        for (int i = 3; i <= 6; i++) {
            board.put(Row.find(i), Rank.createBlank(i));
        }
        board.put(Row.TWO, Rank.createPawn(Team.WHITE, 2));
        board.put(Row.ONE, Rank.createPiecesExceptPawn(Team.WHITE, 1));
    }

    public Piece getPiece(String input) {
        Position position = Position.from(input);
        return board.get(position.getRow()).getPiece(position.getCol());
    }

    public Map<Row, Rank> getBoard() {
        return board;
    }
}
