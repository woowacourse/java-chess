package chess.domain.piece;

import chess.domain.board.Position;

import java.util.List;

public abstract class Piece {
    private final String name;
    private final Team team;

    public Piece(String name, Team team) {
        this.team = team;
        this.name = convertName(name, team);
    }

    private String convertName(String name, Team team) {
        if (team.isBlack()) {
            return name;
        }
        return name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public abstract List<Position> movablePositions(Position target);
}
