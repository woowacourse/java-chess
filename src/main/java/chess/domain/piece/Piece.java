package chess.domain.piece;

import chess.dao.entity.PieceEntity;
import chess.domain.piece.cache.PiecesCache;
import chess.domain.piece.type.Direction;
import chess.domain.piece.type.PieceType;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.type.TeamColor;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final double score;
    private final List<Direction> directions;

    public Piece(Long id, PieceType pieceType, TeamColor teamColor, double score, List<Direction> directions) {
        this.id = id;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public Piece(PieceType pieceType, TeamColor teamColor, double score, List<Direction> directions) {
        this.id = null;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public static Piece of(PieceEntity pieceEntity) {
        try {
            String className = PieceType.getClassNameByPieceType(pieceEntity.getPieceType());
            Class<Piece> pieceClass = (Class<Piece>) Class.forName(className);
            Constructor<?> constructor = pieceClass.getConstructor(Long.class, TeamColor.class);
            return (Piece) constructor.newInstance(pieceEntity.getId(), pieceEntity.getTeamColor());
        } catch (Exception e) {
            throw new IllegalArgumentException("Piece 구현 객체 생성에 실패했습니다.");
        }
    }

    public static Piece of(PieceWithColorType pieceWithColorType) {
        if (pieceWithColorType == null) {
            return null;
        }
        return PiecesCache.find(pieceWithColorType.getPieceType(), pieceWithColorType.getTeamColor());
    }

    public static Piece of(PieceType pieceType, TeamColor teamColor) {
        return PiecesCache.find(pieceType, teamColor);
    }

    public static Piece of(Long pieceId) {
        return PiecesCache.findById(pieceId);
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

    public boolean isCorrectMoveDirection(Direction moveRequestDirection) {
        return directions.contains(moveRequestDirection);
    }
}
