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
    protected Position position;
    private final float score;

    public Piece(Team team, String symbol, Position position, float score) {
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
    }

    public boolean isPawn() {
        return false;
    }

    public void move(Piece destination) {
        position = destination.getPosition();
    }

    public double getScore() {
        return score;
    }

    protected List<Position> getPath(Position destination, Direction direction, Column col, Row row) {
        List<Position> positions = new ArrayList<>();
        while (!destination.matchPosition(col, row)) {
            positions.add(Position.of(col, row));
            col = col.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
        }
        return positions;
    }

    protected Direction findDirection(Position destination) {
        int colDifference = destination.getColDifference(position.getCol());
        int rowDifference = destination.getRowDifference(position.getRow());
        return Direction.find(rowDifference, colDifference, getDirections());
    }

    protected abstract List<Direction> getDirections();

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

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", name='" + symbol + '\'' +
                ", position=" + position +
                '}';
    }
}
