package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public class ChessPiece {
    private String name;
    private Position position;
    private final Team team;

    public ChessPiece(String name, Position position, Team team) {
        this.name = name;
        if(team == Team.BLACK ){
            this.name = name.toUpperCase();
        }
        this.position = position;
        this.team = team;
    }

    public boolean equalPosition(Position checkPosition) {
        return this.position.equals(checkPosition);
    }

    public String getName() {
        return name;
    }

    public boolean isBlack() {
        return team == Team.BLACK;
    }
}
