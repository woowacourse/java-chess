package chess.domain.board;

import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pieces;
import chess.domain.position.Position;
import chess.exception.EnemyTurnException;
import chess.exception.NoSuchPieceException;

import java.util.HashMap;
import java.util.Map;

public final class Board {
    public static final int RANGE_MIN_PIVOT = 0;
    public static final int RANGE_MAX_PIVOT = 7;

    private final Map<Team, Pieces> board;

    public Board(final Map<Team, Pieces> board) {
        this.board = new HashMap<>(board);
    }

    public void move(final Position startPoint, final Position endPoint, final Team team) {
        Pieces pieces = board.get(team);
        checkEnemyTurn(startPoint, team);
        Piece startPointPiece = pieces.pieceByPosition(startPoint);
        startPointPiece.move(this, endPoint);
        startPointPiece.erasePiece(this, team, endPoint);
    }

    private void checkEnemyTurn(final Position startPoint, final Team team) {
        Pieces enemyPieces = board.get(Team.enemyTeam(team));
        if (enemyPieces.containsPosition(startPoint)) {
            throw new EnemyTurnException(team);
        }
    }

    public boolean isWithinBoardRange(final Position position) {
        return !position.isOutOfRange();
    }

    public boolean isEnemyKingDead(final Team team) {
        Pieces enemyPieces = board.get(Team.enemyTeam(team));
        return !enemyPieces.isKingAlive();
    }

    public double scoreByTeam(final Team team) {
        Pieces pieces = board.get(team);
        return pieces.calculatedScore(RANGE_MIN_PIVOT, RANGE_MAX_PIVOT);
    }

    public boolean existsPieceByTeam(final Team team, final Position nextPosition) {
        return piecesByTeam(team).containsPosition(nextPosition);
    }

    public Team teamByPiece(final Piece piece) {
        if (board.get(Team.BLACK).contains(piece)) {
            return Team.BLACK;
        }
        if (board.get(Team.WHITE).contains(piece)) {
            return Team.WHITE;
        }
        throw new NoSuchPieceException();
    }

    public Pieces piecesByTeam(final Team team) {
        return board.get(team);
    }

    public Map<Team, Pieces> toMap() {
        return new HashMap<>(board);
    }
}
