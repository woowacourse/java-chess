package chess.domain.piece;

import chess.domain.board.Board;
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

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public abstract boolean canMove(Position target, Position destination, Board board);

    public abstract List<Position> searchMovablePositions(Position target);

    public boolean isSameTeam(Piece piece) {
        return team.equals(piece.getTeam());
    }

    public Team getTeam() {
        return team;
    }
}
