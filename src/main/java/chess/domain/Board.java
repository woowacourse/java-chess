package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Rank> board;

    public Board() {
        board = new ArrayList<>();
        board.add(Rank.createFirstRank(Team.BLACK));
        board.add(Rank.createPawn(Team.BLACK));
        for (int i = 0; i < 4; i++) {
            board.add(Rank.createBlank());
        }
        board.add(Rank.createPawn(Team.WHITE));
        board.add(Rank.createFirstRank(Team.WHITE));
    }

    public Piece getPiece(String input) {
        Position position = Position.from(input);
        return getRank(position.getRow()).getPiece(position.getColumn());
    }

    private Rank getRank(int row) {
        return board.get(row);
    }

    public List<Rank> getBoard() {
        return new ArrayList<>(board);
    }
}
