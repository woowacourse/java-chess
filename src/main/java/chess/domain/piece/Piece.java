package chess.domain.piece;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;

import chess.domain.piece.cache.PiecesCache;
import chess.domain.piece.type.Direction;
import chess.domain.piece.type.PieceType;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.type.TeamColor;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;
    private final double score;
    private final List<Direction> directions;

    public Piece(Long id, PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.id = id;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public Piece(PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.id = null;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public static Piece castedFrom(chess.dao.entity.PieceEntity pieceEntity) {
        return castToConcretePieceObject(pieceEntity);
    }

    private static Piece castToConcretePieceObject(chess.dao.entity.PieceEntity pieceEntity) {
        Piece castedPiece = getPieceEntityHalf(pieceEntity);
        if (castedPiece != null) {
            return castedPiece;
        }
        return getPieceEntityTheOtherHalf(pieceEntity);
    }

    private static Piece getPieceEntityHalf(chess.dao.entity.PieceEntity pieceEntity) {
        if (pieceEntity.getPieceType() == PAWN) {
            return new Pawn(pieceEntity.getId(), pieceEntity.getTeamColor());
        }
        if (pieceEntity.getPieceType() == ROOK) {
            return new Rook(pieceEntity.getId(), pieceEntity.getTeamColor());
        }
        if (pieceEntity.getPieceType() == KNIGHT) {
            return new Knight(pieceEntity.getId(), pieceEntity.getTeamColor());
        }
        return null;
    }

    private static Piece getPieceEntityTheOtherHalf(chess.dao.entity.PieceEntity pieceEntity) {
        if (pieceEntity.getPieceType() == BISHOP) {
            return new Bishop(pieceEntity.getId(), pieceEntity.getTeamColor());
        }
        if (pieceEntity.getPieceType() == QUEEN) {
            return new Queen(pieceEntity.getId(), pieceEntity.getTeamColor());
        }
        if (pieceEntity.getPieceType() == KING) {
            return new King(pieceEntity.getId(), pieceEntity.getTeamColor());
        }
        throw new IllegalArgumentException("PieceFromDB -> 구체 Piece 클래스 캐스팅 실패");
    }

    public static Piece of(PieceWithColorType pieceWithColorType) {
        if (pieceWithColorType == null) {
            return null;
        }
        return PiecesCache
            .find(pieceWithColorType.getPieceType(), pieceWithColorType.getTeamColor());
    }

    public static Piece of(PieceType pieceType, TeamColor teamColor) {
        return PiecesCache.find(pieceType, teamColor);
    }

    public static Piece of(Long pieceId) {
        return PiecesCache.findById(pieceId);
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
