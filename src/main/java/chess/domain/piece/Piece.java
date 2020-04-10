package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected static final String ILLEGAL_MOVE = "말이 움직일 수 없는 자리입니다.";
    private static final String PIECE_IN_PATH = "경로에 다른 말이 있어 움직일 수 없습니다.";

    protected char representation;
    protected final Team team;
    protected boolean isAlive;
    protected Position position;
    protected double score;

    public Piece(Position position, Team team) {
        this.team = team;
        this.position = position;
        this.isAlive = true;
    }

    public abstract void validateMove(Position destination);

    public abstract void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween);

    protected void validateNoObstacle(List<Piece> piecesInBetween) {
        for (Piece piece : piecesInBetween) {
            if (piece != null) {
                throw new IllegalMoveException(PIECE_IN_PATH);
            }
        }
    }

    public void move(Position destination) {
        validateMove(destination);
        this.position = destination;
    }

    public void kill() {
        this.isAlive = false;
    }

    public boolean isSameTeam(Piece destinationPiece) {
        return this.team == destinationPiece.team;
    }

    public boolean isInTeam(Team team) {
        return this.team == team;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getScore(List<Piece> pieces) {
        return this.score;
    }

    public Team getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.valueOf(team.getTeamRepresentation().apply(representation));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return representation == piece.representation &&
                isAlive == piece.isAlive &&
                Double.compare(piece.score, score) == 0 &&
                team == piece.team &&
                position.equals(piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(representation, team, isAlive, position, score);
    }
}
