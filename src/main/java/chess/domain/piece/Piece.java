package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.route.Route;
import chess.domain.route.RouteFinder;

public class Piece {
    private Team team;
    private PieceType pieceType;

    public Piece(Team team, PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public Route findRoute(Position fromPosition, Position toPosition) {
        return RouteFinder.findRoute(fromPosition, toPosition, team, pieceType);
    }

    public boolean isTeam(Team team) {
        return this.team == team;
    }

    public String getAcronym() {
        return pieceType.getAcronym(team);
    }

    public double getScore() {
        return pieceType.getScore();
    }
}
