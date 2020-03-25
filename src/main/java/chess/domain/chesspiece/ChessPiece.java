package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public abstract class ChessPiece {
    private String name;
    protected Position position;
    private final Team team;

    public ChessPiece(String name, Position position, Team team) {
        this.name = name;
        if(team == Team.BLACK ){
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
