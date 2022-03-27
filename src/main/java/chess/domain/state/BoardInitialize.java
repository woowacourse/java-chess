package chess.domain.state;

import chess.domain.Rank;
import chess.domain.Row;
import chess.domain.Team;

import java.util.EnumMap;
import java.util.Map;

public class BoardInitialize {
    public static Map<Row, Rank> create() {
        Map<Row, Rank> board = new EnumMap<>(Row.class);
        board.put(Row.EIGHT, Rank.createPiecesExceptPawn(Team.BLACK, 8));
        board.put(Row.SEVEN, Rank.createPawn(Team.BLACK, 7));
        for (int i = 3; i <= 6; i++) {
            board.put(Row.find(i), Rank.createBlank(i));
        }
        board.put(Row.TWO, Rank.createPawn(Team.WHITE, 2));
        board.put(Row.ONE, Rank.createPiecesExceptPawn(Team.WHITE, 1));
        return board;
    }
}
