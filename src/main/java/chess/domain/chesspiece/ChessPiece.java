package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.chessmove.Direction;
import chess.domain.chessmove.Move;

import java.util.EnumMap;
import java.util.List;

public abstract class ChessPiece {
    Team team;
    ChessScore chessScore;
    EnumMap<Direction, Move> movingMap;

    public ChessPiece(Team team) {
        this.team = team;
    }

    public boolean isSameTeam(Team team) {
        return this.team.equals(team);
    }

    public double getScore() {
        return chessScore.getScore();
    }

    public boolean isSameTeam(ChessPiece chessPiece) {
        return this.team.equals(chessPiece.team);
    }

    public abstract void initMovingMap();

    public abstract List<Position> getRouteOfPiece(Position source, Position target);
}


