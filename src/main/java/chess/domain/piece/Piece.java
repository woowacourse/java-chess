package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.moving.PieceMoving;
import chess.exception.ImpossibleMoveException;

import java.util.List;

import static chess.domain.TeamColor.WHITE;

public abstract class Piece {

    private final String name;
    private final TeamColor teamColor;
    private final Score score;
    private final PieceMoving pieceMoving;

    public Piece(String name, TeamColor teamColor, Score score, PieceMoving pieceMoving) {
        this.name = name;
        this.teamColor = teamColor;
        this.score = score;
        this.pieceMoving = pieceMoving;
    }

    public final void updateMovablePositions(List<Position> existPiecePositions, List<Position> enemiesPositions) {
        pieceMoving.updateMovablePositions(existPiecePositions, enemiesPositions);
    }

    public final void move(Position targetPosition) throws ImpossibleMoveException {
        pieceMoving.move(targetPosition);
    }

    public final Position currentPosition() {
        return pieceMoving.currentPosition();
    }

    public final boolean isSamePosition(Position position) {
        return pieceMoving.isSamePosition(position);
    }

    public final int row() {
        return pieceMoving.row();
    }

    public final List<Position> movablePositions() {
        return pieceMoving.movablePositions();
    }

    public final boolean sameColor(TeamColor teamColor) {
        return this.teamColor.isSameColor(teamColor);
    }

    public final boolean notSameColor(TeamColor teamColor) {
        return this.teamColor.isNotSameColor(teamColor);
    }

    public final TeamColor enemyColor() {
        return teamColor.reverse();
    }

    public final TeamColor teamColor() {
        return teamColor;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public final Score score() {
        return score;
    }

    public final String name() {
        if (teamColor == WHITE) {
            return name;
        }
        return name.toUpperCase();
    }
}
