package chess.domain.chess;

import chess.domain.chess.unit.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessUnitMapper {
    private static Map<String, Unit> map =  new HashMap<>();

    static {
        map.put("p", new Pawn(Team.WHITE));
        map.put("r", new Rook(Team.WHITE));
        map.put("n", new Knight(Team.WHITE));
        map.put("q", new Queen(Team.WHITE));
        map.put("k", new King(Team.WHITE));
        map.put("b", new Bishop(Team.WHITE));
        map.put("P", new Pawn(Team.BLACK));
        map.put("R", new Rook(Team.BLACK));
        map.put("N", new Knight(Team.BLACK));
        map.put("Q", new Queen(Team.BLACK));
        map.put("K", new King(Team.BLACK));
        map.put("B", new Bishop(Team.BLACK));
    }


    public static Optional<Unit> getUnit(String name) {
        return Optional.ofNullable(map.get(name));
    }
}
