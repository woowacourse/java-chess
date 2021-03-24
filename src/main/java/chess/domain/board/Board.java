package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;
import chess.exception.AnotherTeamTurnException;

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
        checkAnotherTeamTurn(startPoint, team);
        Piece startPointPiece = pieces.getPieceByPosition(startPoint);
        startPointPiece.move(this, endPoint);
    }

    private void checkAnotherTeamTurn(Position startPoint, Team team) {
        Pieces anotherTeamPieces = board.get(Team.getAnotherTeam(team));
        if (anotherTeamPieces.containByPosition(startPoint)) {
            throw new AnotherTeamTurnException(team);
        }
    }

    public final boolean validateRange(final int row, final int col) {
        return !(row < RANGE_MIN_PIVOT || row > RANGE_MAX_PIVOT || col < RANGE_MIN_PIVOT || col > RANGE_MAX_PIVOT);
    }

    public final boolean isEnemyKingDie(final Team team) {
        Pieces enemyPieces = board.get(Team.getAnotherTeam(team));
        return !enemyPieces.kingAlive();
    }

    public final double scoreByTeam(final Team team) {
        Pieces pieces = board.get(team);
        return pieces.calculateScore(RANGE_MIN_PIVOT, RANGE_MAX_PIVOT);
    }

    public final Pieces piecesByTeam(final Team team) {
        return board.get(team);
    }

    public final Map<Team, Pieces> toMap() {
        return new HashMap<>(board);
    }
}
