package chess.dto;

import chess.model.Board;
import chess.model.Position;
import java.util.ArrayList;
import java.util.List;

public class RankDto {

    private static final int MAX_INDEX = 7;
    private static final String DELIMITER = "";

    private final List<String> rank;

    public RankDto(List<String> rank) {
        this.rank = rank;
    }

    public static RankDto of(Board board, int row) {
        List<String> rank = new ArrayList<>();
        for (int i = 0; i <= MAX_INDEX; i++) {
            Position position = new Position(row, i);
            rank.add(findPieceName(board, position));
        }
        return new RankDto(rank);
    }

    private static String findPieceName(Board board, Position position) {
        return board.findPiece(position)
                .toString();
    }

    @Override
    public String toString() {
        return String.join(DELIMITER, rank);
    }
}
