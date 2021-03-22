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

    public void updateMovablePositions(List<Position> existPiecePositions, List<Position> enemiesPositions) {
        pieceMoving.updateMovablePositions(existPiecePositions, enemiesPositions);
    }

    public void move(Position targetPosition) throws ImpossibleMoveException {
        pieceMoving.move(targetPosition);
    }

    public Position currentPosition() {
        return pieceMoving.currentPosition();
    }

    public boolean isSamePosition(Position position) {
        return pieceMoving.isSamePosition(position);
    }

    public int row() {
        return pieceMoving.row();
    }

    public boolean sameColor(TeamColor teamColor) {
        return this.teamColor.isSameColor(teamColor);
    }

    public boolean notSameColor(TeamColor teamColor) {
        return this.teamColor.isNotSameColor(teamColor);
    }

    public TeamColor enemyColor() {
        return teamColor.reverse();
    }

    public TeamColor teamColor() {
        return teamColor;
    }

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public List<Position> movablePositions(){
        return pieceMoving.movablePositions();
    }

    public Score score() {
        return score;
    }

    public String name() {
        if (teamColor == WHITE) {
            return name;
        }
        return name.toUpperCase();
    }
}
