package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;
import chess.exception.EnemyTurnException;

import java.util.HashMap;
import java.util.Map;

public final class Board {
    public static final int RANGE_MIN_PIVOT = 0;
    public static final int RANGE_MAX_PIVOT = 7;

    private final Map<Team, Pieces> board;

    public Board(final Map<Team, Pieces> board) {
        this.board = new HashMap<>(board);
    }

    public final void move(final Position startPoint, final Position endPoint, final Team team) {
        Pieces pieces = board.get(team);
        checkEnemyTurn(startPoint, team);
        Piece startPointPiece = pieces.pieceByPosition(startPoint);
        startPointPiece.move(this, endPoint);
        startPointPiece.erasePiece(this, endPoint);
    }

    private void checkEnemyTurn(Position startPoint, Team team) {
        Pieces enemyPieces = board.get(Team.enemyTeam(team));
        if (enemyPieces.containsPosition(startPoint)) {
            throw new EnemyTurnException(team);
        }
    }

    public boolean validatesRange(Position position) {
        return !position.isOutOfRange();
    }

    public final boolean isEnemyKingDead(final Team team) {
        Pieces enemyPieces = board.get(Team.enemyTeam(team));
        return !enemyPieces.isKingAlive();
    }

    public final double scoreByTeam(final Team team) {
        Pieces pieces = board.get(team);
        return pieces.calculatedScore(RANGE_MIN_PIVOT, RANGE_MAX_PIVOT);
    }

    public final Pieces piecesByTeam(final Team team) {
        return board.get(team);
    }

    public final Map<Team, Pieces> toMap() {
        return new HashMap<>(board);
    }
}
