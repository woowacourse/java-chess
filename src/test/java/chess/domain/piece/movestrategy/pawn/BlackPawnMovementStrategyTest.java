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
@DisplayName("BlackPawnMovementStrategy 은")
class BlackPawnMovementStrategyTest {

    public Piece piece(final Color color, final PiecePosition piecePosition) {
        return new Piece(color, piecePosition, new BlackPawnMovementStrategy(Rank.from(7)));
    }

    @Nested
    class 북쪽_이동_불가_테스트 {

        private final PawnMovementStrategy pawnMovement = new BlackPawnMovementStrategy(Rank.from(7));

        @Test
        void 직선_한_칸_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d7");
            final PiecePosition dest = PiecePosition.of("d8");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 직선_두_칸_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d6");
            final PiecePosition dest = PiecePosition.of("b8");

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, null))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void 대각선_한_칸_이동_불가() {
            // given
            final PiecePosition source = PiecePosition.of("d6");
            final PiecePosition dest = PiecePosition.of("c7");
            final Piece piece = piece(Color.WHITE, dest);

            // when & then
            assertThatThrownBy(() -> pawnMovement.validateMove(source, dest, piece))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
