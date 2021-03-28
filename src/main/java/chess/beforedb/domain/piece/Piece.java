package chess.beforedb.domain.piece;

import chess.beforedb.domain.board.Board;
import chess.beforedb.domain.piece.cache.PiecesCache;
import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.MoveRoute;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final double score;
    private final List<Direction> directions;

    protected Piece(PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public static Piece of(PieceWithColorType pieceWithColorType) {
        return PiecesCache
            .find(pieceWithColorType.getPieceType(), pieceWithColorType.getTeamColor());
    }

    public static Piece of(PieceType pieceType, TeamColor teamColor) {
        return PiecesCache.find(pieceType, teamColor);
    }

    public boolean canMoveTo(MoveRoute moveRoute, Board board) {
        Direction moveDirection = moveRoute.direction();
        if (isNotCorrectDirection(moveDirection)
            || board.isAnyPieceExistsOnRouteBeforeDestination(moveRoute)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        if (board.isOwnPieceExistsInCell(moveRoute.destination(), teamColor)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }

    protected boolean isNotCorrectDirection(Direction moveCommandDirection) {
        return !directions.contains(moveCommandDirection);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public double getScore() {
        return score;
    }

    public String getName() {
        return pieceType.getName(teamColor);
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
