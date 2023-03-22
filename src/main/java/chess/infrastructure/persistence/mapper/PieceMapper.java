package chess.infrastructure.persistence.mapper;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.movestrategy.PieceMovementStrategy;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.infrastructure.persistence.entity.PieceEntity;

public class PieceMapper {

    public static PieceEntity fromDomain(final Piece piece, final Long chessGameId) {
        return new PieceEntity(
                piece.id(),
                piece.piecePosition().rank().value(),
                piece.piecePosition().file().value(),
                piece.color().name(),
                piece.pieceMovementStrategy().getClass().getName(),
                chessGameId
        );
    }

    public static Piece toDomain(final PieceEntity pieceEntity) {
        final Color color = Color.valueOf(pieceEntity.color());
        return new Piece(pieceEntity.id(),
                PiecePosition.of(Rank.from(pieceEntity.rank()), File.from(pieceEntity.file())),
                makeStrategy(pieceEntity, color)
        );
    }

    private static PieceMovementStrategy makeStrategy(final PieceEntity pieceEntity, final Color color) {
        try {
            return PieceMovementStrategy.class.cast(
                    Class.forName(pieceEntity.movementType()).getDeclaredConstructor(Color.class).newInstance(color)
            );
        } catch (Exception e) {
            throw new RuntimeException("피스 생성 중 문제 발생", e);
        }
    }

}
