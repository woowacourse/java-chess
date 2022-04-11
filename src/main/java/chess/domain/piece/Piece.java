package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private final Team team;
    private final String symbol;
    private final float score;

    public Piece(Team team, String symbol, float score) {
        this.team = team;
        this.symbol = team.getSymbol(symbol);
        this.score = score;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public abstract List<Position> findPath(Position source, Position destination);

    public boolean isBlank() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public double getScore() {
        return score;
    }

    protected List<Position> getPath(Position destination, Direction direction, Position position) {
        List<Position> positions = new ArrayList<>();
        while (!destination.matchPosition(position)) {
            positions.add(position);
            position = position.plusDirection(direction);
        }
        return positions;
    }

    protected Direction findDirection(Position source, Position destination) {
        int colDifference = destination.getColDifference(source.getCol());
        int rowDifference = destination.getRowDifference(source.getRow());
        return Direction.find(rowDifference, colDifference, getDirections());
    }

    protected abstract List<Direction> getDirections();

    public Team getTeam() {
        return team;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isSameTeam(Piece piece) {
        return getTeam() == piece.getTeam();
    }

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", name='" + symbol + '\'' +
                '}';
    }
}
