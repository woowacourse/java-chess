package chess.infrastructure.persistence.entity;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.movestrategy.KingMovementStrategy;
import chess.domain.piece.movestrategy.pawn.BlackPawnMovementStrategy;
import chess.domain.piece.position.PiecePosition;
import chess.infrastructure.persistence.mapper.PieceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PieceEntity 은")
class PieceEntityTest {

    @Test
    void Piece_로부터_생성될_수_있다() {
        // given
        final Piece piece = new Piece(Color.WHITE, PiecePosition.of("d2"), new KingMovementStrategy());

        // when
        final PieceEntity pieceEntity = PieceMapper.fromDomain(piece, 2L);

        // then
        assertAll(
                () -> assertThat(pieceEntity.rank()).isEqualTo(2),
                () -> assertThat(pieceEntity.file()).isEqualTo('d'),
                () -> assertThat(pieceEntity.movementType()).isEqualTo(KingMovementStrategy.class.getSimpleName()),
                () -> assertThat(pieceEntity.chessGameId()).isEqualTo(2L)
        );
    }

    @Test
    void Piece_를_생성할_수_있다() {
        // given
        final PieceEntity pieceEntity = new PieceEntity(4, 'f', "BLACK", BlackPawnMovementStrategy.class.getSimpleName(), 1L);

        // when
        final Piece piece = PieceMapper.toDomain(pieceEntity);

        // then
        assertAll(
                () -> assertThat(piece.piecePosition()).isEqualTo(PiecePosition.of("f4")),
                () -> assertThat(piece.pieceMovementStrategy()).isInstanceOf(BlackPawnMovementStrategy.class),
                () -> assertThat(piece.color()).isEqualTo(Color.BLACK)
        );
    }
}
