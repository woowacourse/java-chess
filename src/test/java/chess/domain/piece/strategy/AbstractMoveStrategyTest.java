package chess.domain.piece.strategy;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("AbstractMoveStrategy 은")
class AbstractMoveStrategyTest {

    @Test
    void 올바른_경로가_아니면_예외가_발생한다() {
        // given
        final PieceMovementStrategy strategy = new AbstractPieceMovementStrategy() {

            @Override
            protected void validateMoveWithNoAlly(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {
                throw new IllegalArgumentException();
            }
        };
        final Path path = Path.of(PiecePosition.of("d2"), PiecePosition.of("d4"));

        // when & then
        assertThatThrownBy(() ->
                strategy.waypoints(Color.BLACK, path, null)
        );
    }

    @Test
    void 올바른_경로라면_경유지를_반환한다() {
        // given
        final PieceMovementStrategy strategy = new AbstractPieceMovementStrategy() {
            @Override
            protected void validateMoveWithNoAlly(final Path path, final Piece nullableEnemy) throws IllegalArgumentException {

            }
        };
        final Path path = Path.of(PiecePosition.of("d2"), PiecePosition.of("d4"));

        // when
        final List<PiecePosition> waypoints = strategy.waypoints(Color.BLACK, path, null);

        // then
        assertThat(waypoints).containsOnly(PiecePosition.of("d3"));
    }
}
