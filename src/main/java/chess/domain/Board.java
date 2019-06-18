package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static Map<Position, Object> board;

    static {
        board = new HashMap<>();
        List<Character> aToH = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
        List<Object> piecesTeamA = Arrays.asList(new Rook(), new Knight(), new Bishop(), new Queen(),
                new King(), new Bishop(), new Knight(), new Rook());
        List<Object> piecesTeamB = Arrays.asList(new Rook(), new Knight(), new Bishop(), new Queen(),
                new King(), new Bishop(), new Knight(), new Rook());

        for (int i = 0; i < aToH.size(); i++) {
            board.put(new Position(new Coordinate(aToH.get(i)), new Coordinate(1)), piecesTeamA.get(i));
            board.put(new Position(new Coordinate(aToH.get(i)), new Coordinate(8)), piecesTeamB.get(i));
        }

        aToH.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(2)), new Pawn()));
        aToH.forEach(character -> board.put(new Position(new Coordinate(character), new Coordinate(7)), new Pawn()));
    }

    public static Object at(final Position position) {
        return board.get(position);
    }
}
