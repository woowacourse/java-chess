package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.BISHOP;
import static chess.beforedb.domain.piece.type.PieceType.KING;
import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;
import static chess.beforedb.domain.piece.type.PieceType.PAWN;
import static chess.beforedb.domain.piece.type.PieceType.QUEEN;
import static chess.beforedb.domain.piece.type.PieceType.ROOK;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.piece.type.PieceWithColorType;
import chess.beforedb.domain.player.type.TeamColor;
import chess.db.dao.PieceFromDB;
import java.util.List;
import java.util.Objects;

public abstract class PieceEntity {
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

    public PieceEntity(PieceType pieceType, TeamColor teamColor, double score,
        List<Direction> directions) {
        this.id = null;
        this.pieceType = pieceType;
        this.teamColor = teamColor;
        this.score = score;
        this.directions = directions;
    }

    public static PieceEntity castedFrom(PieceFromDB pieceFromDB) {
        return castToConcretePieceObject(pieceFromDB);
    }

    private static PieceEntity castToConcretePieceObject(PieceFromDB pieceFromDB) {
        PieceEntity castedPieceEntity = getPieceEntityHalf(pieceFromDB);
        if (castedPieceEntity != null) {
            return castedPieceEntity;
        }
        return getPieceEntityTheOtherHalf(pieceFromDB);
    }

    private static PieceEntity getPieceEntityHalf(PieceFromDB pieceFromDB) {
        if (pieceFromDB.getPieceType() == PAWN) {
            return new PawnEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == ROOK) {
            return new RookEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == KNIGHT) {
            return new KnightEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        return null;
    }

    private static PieceEntity getPieceEntityTheOtherHalf(PieceFromDB pieceFromDB) {
        if (pieceFromDB.getPieceType() == BISHOP) {
            return new BishopEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == QUEEN) {
            return new QueenEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        if (pieceFromDB.getPieceType() == KING) {
            return new KingEntity(pieceFromDB.getId(), pieceFromDB.getTeamColor());
        }
        throw new IllegalArgumentException("PieceFromDB -> 구체 Piece 클래스 캐스팅 실패");
    }

    public static PieceEntity of(PieceWithColorType pieceWithColorType) {
        if (pieceWithColorType == null) {
            return null;
        }
        return PieceEntitiesCache
            .find(pieceWithColorType.getPieceType(), pieceWithColorType.getTeamColor());
    }

    public static PieceEntity of(PieceType pieceType, TeamColor teamColor) {
        return PieceEntitiesCache.find(pieceType, teamColor);
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

    public boolean isCorrectMoveDirection(Direction moveRequestDirection) {
        return directions.contains(moveRequestDirection);
    }
}
