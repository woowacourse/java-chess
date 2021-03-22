package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.cache.PiecesCache;
import chess.domain.piece.type.Direction;
import chess.domain.piece.type.PieceType;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final double score;
    private final List<Direction> directions;

    public Piece(PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public static Piece of(PieceWithColorType pieceWithColorType) {
        return PiecesCache.find(pieceWithColorType.type(), pieceWithColorType.color());
    }

    public static Piece of(PieceType pieceType, TeamColor teamColor) {
        return PiecesCache.find(pieceType, teamColor);
    }

    public boolean isMovableTo(MoveRoute moveRoute, Board board) {
        Direction moveDirection = moveRoute.direction();
        if (isNotCorrectDirection(moveDirection)
            || board.isAnyPieceExistsOnRouteBeforeDestination(moveRoute)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        if (board.isNotCellEmpty(moveRoute.destination())
            && !board.isEnemyExists(moveRoute.destination(), teamColor)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }

    protected boolean isNotCorrectDirection(Direction moveCommandDirection) {
        return !directions.contains(moveCommandDirection);
    }

    public PieceType type() {
        return pieceType;
    }

    public TeamColor color() {
        return teamColor;
    }

    public double score() {
        return score;
    }

    public String name() {
        return pieceType.name(teamColor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && teamColor == piece.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, teamColor);
    }
}
