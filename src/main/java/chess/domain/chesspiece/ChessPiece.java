package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public abstract class ChessPiece {
    private String name;
    protected Position position;
    private final Team team;

    public ChessPiece(String name, Position position, Team team) {
        this.name = name;
        if (team == Team.BLACK) {
            this.name = name.toUpperCase();
        }
        this.position = position;
        this.team = team;
    }

    public abstract boolean canMove(Position position);

    public abstract List<Position> makeCanMovePositions();


    public String getName() {
        return name;
    }

}
