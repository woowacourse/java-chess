package chess.domain.piece.movestrategy.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("WhitePawnMovementStrategy 은")
class WhitePawnMovementStrategyTest {

    public Piece piece(final Color color, final PiecePosition piecePosition) {
        return new Piece(color, piecePosition, new BlackPawnMovementStrategy(Rank.from(7)));
    }

    @Nested
    class 남쪽_이동_불가_테스트 {

        private final PawnMovementStrategy pawnMovement = new WhitePawnMovementStrategy(Rank.from(4));

        @Test
        void 직선_한_칸_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d4");
            final PiecePosition dest = PiecePosition.of("d3");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 직선_두_칸_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d4");
            final PiecePosition dest = PiecePosition.of("d2");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 대각선_한_칸_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d4");
            final PiecePosition dest = PiecePosition.of("c3");
            final Piece piece = piece(Color.BLACK, dest);

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, piece))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
