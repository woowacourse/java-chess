package chess.infrastructure.persistence.mapper;

import chess.domain.piece.Color;
import chess.domain.piece.MovementType;
import chess.domain.piece.Piece;
import chess.domain.piece.movestrategy.BishopMovementStrategy;
import chess.domain.piece.movestrategy.KingMovementStrategy;
import chess.domain.piece.movestrategy.KnightMovementStrategy;
import chess.domain.piece.movestrategy.PieceMovementStrategy;
import chess.domain.piece.movestrategy.QueenMovementStrategy;
import chess.domain.piece.movestrategy.RookMovementStrategy;
import chess.domain.piece.movestrategy.pawn.BlackPawnMovementStrategy;
import chess.domain.piece.movestrategy.pawn.WhitePawnMovementStrategy;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.infrastructure.persistence.entity.PieceEntity;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class PieceMapper {

    private static final Map<MovementType, Constructor<? extends PieceMovementStrategy>> contractorMap = new HashMap<>();

    static {
        try {
            contractorMap.put(
                    MovementType.KING,
                    KingMovementStrategy.class.getDeclaredConstructor()
            );
            contractorMap.put(
                    MovementType.QUEEN,
                    QueenMovementStrategy.class.getDeclaredConstructor()
            );
            contractorMap.put(
                    MovementType.BISHOP,
                    BishopMovementStrategy.class.getDeclaredConstructor()
            );
            contractorMap.put(
                    MovementType.KNIGHT,
                    KnightMovementStrategy.class.getDeclaredConstructor()
            );
            contractorMap.put(
                    MovementType.ROOK,
                    RookMovementStrategy.class.getDeclaredConstructor()
            );
            contractorMap.put(
                    MovementType.BLACK_PAWN,
                    BlackPawnMovementStrategy.class.getDeclaredConstructor()
            );
            contractorMap.put(
                    MovementType.WHITE_PAWN,
                    WhitePawnMovementStrategy.class.getDeclaredConstructor()
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static PieceEntity fromDomain(final Piece piece, final Long chessGameId) {
        return new PieceEntity(
                piece.piecePosition().rank().value(),
                piece.piecePosition().file().value(),
                piece.color().name(),
                piece.type().name(),
                chessGameId
        );
    }

    public static Piece toDomain(final PieceEntity pieceEntity) {
        final Color color = Color.valueOf(pieceEntity.color());
        return new Piece(color,
                PiecePosition.of(
                        Rank.from(pieceEntity.rank()), File.from(pieceEntity.file())),
                makeStrategy(pieceEntity));
    }

    private static PieceMovementStrategy makeStrategy(final PieceEntity pieceEntity) {
        try {
            return contractorMap.get(MovementType.valueOf(pieceEntity.movementType())).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("피스 생성 중 문제 발생", e);
        }
    }
}
