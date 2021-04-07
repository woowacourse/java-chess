package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.moving.Moving;
import chess.domain.position.Position;
import chess.exception.InvalidMovePositionException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Piece {
    private Position position;
    private final Information information;
    private final Moving moving;

    public Piece(final Position position, final Information information, final Moving moving) {
        this.position = position;
        this.information = information;
        this.moving = moving;
    }

    public final void move(final Board board, final Position endPoint) {
        List<Position> movablePositions = moving.allMovablePositions(this, board);
        validatesEndPoint(endPoint, movablePositions);
        this.position = endPoint;
    }

    private void validatesEndPoint(final Position endPoint, final List<Position> movablePositions) {
        if (!movablePositions.contains(endPoint)) {
            throw new InvalidMovePositionException();
        }
    }

    public final void erasePiece(final Board board, final Team team, final Position endPoint) {
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

    public final String initial() {
        return information.initial();
    }

    public final Double score() {
        return information.score();
    }

    public final List<String> movablePositions(final Board board) {
        List<Position> positions = this.moving.allMovablePositions(this, board);
        return positions.stream()
                .map(Position::position)
                .collect(Collectors.toList());
    }

    public abstract boolean isKing();

    public abstract boolean isPawn();
}
