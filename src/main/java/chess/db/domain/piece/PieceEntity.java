package chess.db.domain.piece;

import chess.domain.board.Board;
import chess.domain.piece.type.Direction;
import chess.domain.piece.type.PieceType;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import java.util.List;
import java.util.Objects;

public class PieceEntity {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final double score;
    private final List<Direction> directions;

    public PieceEntity(Long id, PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.id = id;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public PieceEntity(Long id, String name, String color) {
        this.id = id;
        this.pieceType = PieceType.find(name);
        this.teamColor = TeamColor.of(color);
        score = -9.9;
        directions = null;
    }

    public static PieceEntity of(PieceWithColorType pieceWithColorType) {
        return PieceEntitiesCache.find(pieceWithColorType.type(), pieceWithColorType.color());
    }

    public static PieceEntity of(PieceType pieceType, TeamColor teamColor) {
        return PieceEntitiesCache.find(pieceType, teamColor);
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

    public Long getId() {
        return id;
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
        if (!(o instanceof PieceEntity)) {
            return false;
        }
        PieceEntity piece = (PieceEntity) o;
        return pieceType == piece.pieceType && teamColor == piece.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, teamColor);
    }
}
