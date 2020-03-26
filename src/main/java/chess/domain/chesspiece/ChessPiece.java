package chess.domain.chesspiece;

import chess.domain.game.Team;
import chess.domain.move.Direction;

import java.util.List;

public abstract class ChessPiece {
    private final ChessPieceInfo chessPieceInfo;
    private final Team team;

    public ChessPiece(ChessPieceInfo chessPieceInfo, Team team) {
        this.chessPieceInfo = chessPieceInfo;
        this.team = team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public String getName() {
        String name = chessPieceInfo.getName();
        if(team == Team.BLACK){
            return name.toUpperCase();
        }
        return name;
    }

    public List<Direction> getMoveDirections() {
        return chessPieceInfo.getMoveDirections();
    }

    public Team getTeam() {
        return team;
    }

    public double getPoint() {
        return chessPieceInfo.getPoint();
    }
}
