package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.move.Moving;
import chess.domain.position.Position;
import chess.exception.InvalidMovePositionException;

import java.util.List;
import java.util.Locale;

public abstract class Piece {
    private Position position;
    private final Team team;
    private final String initial;
    private final Double score;
    private final Moving moving;

    public Piece(final Position position, final String initial, final Team team, final Double score, final Moving moving) {
        this.position = position;
        this.score = score;
        this.initial = initialByTeam(team, initial);
        this.team = team;
        this.moving = moving;
    }

    private String initialByTeam(final Team team, final String initial) {
        if (team.equals(Team.WHITE)) {
            return initial.toLowerCase(Locale.ROOT);
        }
        return initial.toUpperCase(Locale.ROOT);
    }

    public final void move(final Board board, final Position endPoint) {
        List<Position> movablePositions = allMovablePositions(board);
        validatesEndPoint(endPoint, movablePositions);
        this.position = endPoint;
    }

    private void validatesEndPoint(final Position endPoint, final List<Position> movablePositions) {
        if (!movablePositions.contains(endPoint)) {
            throw new InvalidMovePositionException();
        }
    }

    public void erasePiece(final Board board, final Position endPoint) {
        Pieces enemyPieces = board.piecesByTeam(Team.enemyTeam(team));
        enemyPieces.removePieceByPosition(endPoint);
    }

    public final boolean isSamePosition(final Position startPoint) {
        return this.position.equals(startPoint);
    }

    public final boolean isSameCol(final int col) {
        return position.isSameCol(col);
    }

    public final Position position() {
        return position;
    }

    public final Team team() {
        return team;
    }

    public final String initial() {
        return initial;
    }

    public final Double score() {
        return score;
    }

    public final Moving moving() {
        return moving;
    }

    public abstract List<Position> allMovablePositions(final Board board);

    public abstract boolean isKing();

    public abstract boolean isPawn();
}
