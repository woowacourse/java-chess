package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Piece {
    private Position position;
    private final Team team;
    private final String initial;

    public Piece(final Position position, final String initial, final Team team) {
        this.position = position;
        this.initial = checkTeam(team, initial);
        this.team = team;
    }

    protected final Team getTeam() {
        return team;
    }

    private String checkTeam(final Team team, final String initial) {
        if (team.equals(Team.WHITE)) {
            return initial.toLowerCase(Locale.ROOT);
        }
        return initial.toUpperCase(Locale.ROOT);
    }

    public final String getInitial() {
        return initial;
    }

    public final Position getPosition() {
        return position;
    }

    public final boolean samePosition(final Position startPoint) {
        return this.position.equals(startPoint);
    }

    public final void move(final Map<Team, List<Piece>> board, final Position endPoint) {
        List<Position> movablePositions = getMovablePositions(board);
        validateEndPoint(endPoint, movablePositions);
        erasePiece(board, endPoint);
        this.position = endPoint;
    }

    private void erasePiece(Map<Team, List<Piece>> board, Position endPoint) {
        List<Piece> anotherTeamPieces = board.get(Team.getAnotherTeam(team));
        anotherTeamPieces.stream()
                .filter(piece -> piece.samePosition(endPoint))
                .findFirst()
                .ifPresent(anotherTeamPieces::remove);
    }

    private void validateEndPoint(final Position endPoint, final List<Position> movablePositions) {
        if (!movablePositions.contains(endPoint)) {
            throw new IllegalArgumentException("갈수 없는 위치입니다.");
        }
    }

    public abstract List<Position> getMovablePositions(Map<Team, List<Piece>> board);
}
