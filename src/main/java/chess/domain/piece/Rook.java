package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Location;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final String ROOK_NAME = "R";

    public Rook(Team team) {
        super(ROOK_NAME, team);
    }

    @Override
    public List<Location> movableLocations(Location target) {
        List<Location> movableLocations = new ArrayList<>();
        Vertical targetVertical = target.getVertical();
        Horizontal targetHorizontal = target.getHorizontal();

        for (Horizontal horizontal : Horizontal.values()) {
            movableLocations.add(Location.of(horizontal, targetVertical));
        }
        for (Vertical vertical : Vertical.values()) {
            movableLocations.add(Location.of(targetHorizontal, vertical));
        }

        return movableLocations;
    }
}