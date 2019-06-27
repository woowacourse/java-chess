package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chesspieceMove.Move;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public abstract class ChessPiece {
    Team team;
    Map<MoveDirection, Move> movingMap;

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

    public boolean isSameChessPiece(Type type) {
        return this.getClass().getTypeName().equals(type.getTypeName());
    }

    public Boolean isNotSameTeam(ChessPiece target) {
        return !this.isSameTeam(target) && !target.isSameTeam(Team.BLANK);
    }
}


