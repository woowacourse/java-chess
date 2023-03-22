package chess.domain.piece.movestrategy.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import chess.domain.piece.movestrategy.RookMovementStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PawnMovementStrategy 은")
class PawnMovementStrategyTest {

    private final PawnMovementStrategy pawnMovement = new PawnMovementStrategy(Color.WHITE, Rank.from(4)) {
        @Override
        protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
        }
    };

    public Piece piece(final Color color, final PiecePosition piecePosition) {
        return new Piece(piecePosition, new PawnMovementStrategy(color, Rank.from(4)) {
            @Override
            protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
            }
        });
    }

    @Test
    void 이동할_수_있으면_경유지_조회_가능() {
        // given
        assertDoesNotThrow(() -> pawnMovement.validateMove(PiecePosition.of("d4"), PiecePosition.of("d6"), null));

        // when
        final List<PiecePosition> waypoints = pawnMovement.waypoints(PiecePosition.of("d4"), PiecePosition.of("d6"), null);

        // then
        assertThat(waypoints).isNotEmpty();
    }

    @Test
    void 이동할_수_없으면_경유지_조회_불가() {
        // given
        final PiecePosition source = PiecePosition.of("d2");
        final PiecePosition dest = PiecePosition.of("d4");
        final Piece enemy = piece(Color.BLACK, dest);
        assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, enemy));

        // when & then
        assertThatThrownBy(() -> pawnMovement.waypoints(source, dest, enemy))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Nested
    class 도착지에_적이_있다면 {

        @Test
        void 대각선_한_칸_이동_가능() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("c3");
            final Piece piece = piece(Color.BLACK, dest);

            // when & then
            assertDoesNotThrow(() -> pawnMovement.validateMove(source, dest, piece));
        }

        @Test
        void 대각선_두칸_이상_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("f4");
            final Piece piece = piece(Color.BLACK, dest);

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, piece))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 수직_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("d3");
            final Piece piece = piece(Color.BLACK, dest);

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, piece))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 수평_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("c2");
            final Piece piece = piece(Color.BLACK, dest);

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, piece))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 도착지에_피스가_없다면 {

        @Test
        void 수직_한_칸_이동_가능() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("d3");

            // when & then
            assertDoesNotThrow(() -> pawnMovement.validateMove(source, dest, null));
        }

        @Test
        void 두칸_이동할_수_있는_랭크라면_두_칸_이동_가능() {
            // given
            final PawnMovementStrategy pawnMovement = new PawnMovementStrategy(Color.BLACK, Rank.from(4)) {
                @Override
                protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
                }
            };
            final PiecePosition source = PiecePosition.of("d4");
            final PiecePosition dest = PiecePosition.of("d6");

            // when & then
            assertDoesNotThrow(() -> pawnMovement.validateMove(source, dest, null));
        }

        @Test
        void 두칸_이동할_수_없는_랭크라면_두_칸_이동_불가() {
            // given
            final PawnMovementStrategy pawnMovement = new PawnMovementStrategy(Color.BLACK, Rank.from(4)) {
                @Override
                protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
                }
            };
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("d4");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 대각선_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("e3");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 수평_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d2");
            final PiecePosition dest = PiecePosition.of("c2");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    void 추가_제약조건을_지키지_않았다면_예외() {
        // given
        final PawnMovementStrategy pawnMovement = new PawnMovementStrategy(Color.WHITE, Rank.from(1)) {
            @Override
            protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
                throw new IllegalArgumentException();
            }
        };
        final PiecePosition source = PiecePosition.of("d2");
        final PiecePosition dest = PiecePosition.of("d3");

        // when & then
        assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 추가_제약조건을_지켰다면_기본_이동에_대해서는_가능() {
        // given
        final PawnMovementStrategy pawnMovement = new PawnMovementStrategy(Color.WHITE, Rank.from(1)) {
            @Override
            protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
            }
        };
        final PiecePosition source = PiecePosition.of("d2");
        final PiecePosition dest = PiecePosition.of("d3");

        // when & then
        assertDoesNotThrow(() -> pawnMovement.validateMove(source, dest, null));
    }

    @Test
    void 추가_제약조건을_지켰더라도_기본_이동_수칙을_지키지_않으면_예외() {
        // given
        final PawnMovementStrategy pawnMovement = new PawnMovementStrategy(Color.WHITE, Rank.from(2)) {
            @Override
            protected void validateAdditionalConstraint(final PiecePosition source, final PiecePosition destination) {
            }
        };
        final PiecePosition source = PiecePosition.of("d2");
        final PiecePosition dest = PiecePosition.of("d5");

        // when & then
        assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 아군을_죽일_수_없다() {
        // given
        final PiecePosition source = PiecePosition.of("d2");
        final PiecePosition dest = PiecePosition.of("c3");
        final Piece ally = new Piece(dest, new RookMovementStrategy(Color.WHITE));

        // when & then
        assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, ally))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 적군을_죽일_수_있다() {
        // given
        final PiecePosition source = PiecePosition.of("d2");
        final PiecePosition dest = PiecePosition.of("c3");
        final Piece enemy = new Piece(dest, new RookMovementStrategy(Color.BLACK));

        // when & then
        assertDoesNotThrow(() -> pawnMovement.validateMove(source, dest, enemy));
    }
}
