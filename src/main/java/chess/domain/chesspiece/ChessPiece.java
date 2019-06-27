package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Move;

import java.util.List;
import java.util.Map;

public abstract class ChessPiece {
    Team team;
    Map<String, Move> movingMap;

    public ChessPiece(Team team) {
        this.team = team;
    }

    public abstract void initMovingMap();

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public boolean isSameTeam(ChessPiece chessPiece) {
        return this.team.equals(chessPiece.team);
    }

    public abstract List<Position> getRouteOfPiece(Position source, Position target);

    public abstract double getScore();
}


