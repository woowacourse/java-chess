package chess.infrastructure.persistence.mapper;

import chess.domain.piece.Color;
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

    private static final Map<String, Constructor<? extends PieceMovementStrategy>> contractorMap = new HashMap<>();

    static {
        try {
            contractorMap.put(KingMovementStrategy.class.getSimpleName(), KingMovementStrategy.class.getDeclaredConstructor(Color.class));
            contractorMap.put(QueenMovementStrategy.class.getSimpleName(), QueenMovementStrategy.class.getDeclaredConstructor(Color.class));
            contractorMap.put(BishopMovementStrategy.class.getSimpleName(), BishopMovementStrategy.class.getDeclaredConstructor(Color.class));
            contractorMap.put(KnightMovementStrategy.class.getSimpleName(), KnightMovementStrategy.class.getDeclaredConstructor(Color.class));
            contractorMap.put(RookMovementStrategy.class.getSimpleName(), RookMovementStrategy.class.getDeclaredConstructor(Color.class));
            contractorMap.put(BlackPawnMovementStrategy.class.getSimpleName(), BlackPawnMovementStrategy.class.getDeclaredConstructor(Color.class));
            contractorMap.put(WhitePawnMovementStrategy.class.getSimpleName(), WhitePawnMovementStrategy.class.getDeclaredConstructor(Color.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static PieceEntity fromDomain(final Piece piece, final Long chessGameId) {
        return new PieceEntity(
                piece.piecePosition().rank().value(),
                piece.piecePosition().file().value(),
                piece.color().name(),
                piece.pieceMovementStrategy().getClass().getSimpleName(),
                chessGameId
        );
    }

    public static Piece toDomain(final PieceEntity pieceEntity) {
        final Color color = Color.valueOf(pieceEntity.color());
        return new Piece(PiecePosition.of(Rank.from(pieceEntity.rank()), File.from(pieceEntity.file())),
                makeStrategy(pieceEntity, color)
        );
    }

    private static PieceMovementStrategy makeStrategy(final PieceEntity pieceEntity, final Color color) {
        try {
            return contractorMap.get(pieceEntity.movementType()).newInstance(color);
        } catch (Exception e) {
            throw new RuntimeException("피스 생성 중 문제 발생", e);
        }
    }
}
