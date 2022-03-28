package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public abstract class Piece {
    private Team team;
    private final String symbol;
    protected Position position;
    private double score;

    public Piece(Team team, String symbol, Position position, double score) {
        this.team = team;
        this.symbol = team.getSymbol(symbol);
        this.position = position;
        this.score = score;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public abstract List<Position> findPath(Position destination);

    public boolean isBlank() {
        return false;
    };

    public boolean isPawn() {
        return false;
    }

    public void move(Position destination) {
        position = destination;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", name='" + symbol + '\'' +
                ", position=" + position +
                '}';
    }

    public Team getTeam() {
        return team;
    }

    public boolean isKing() {
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSameTeam(Piece piece) {
        return getTeam() == piece.getTeam();
    }
}
