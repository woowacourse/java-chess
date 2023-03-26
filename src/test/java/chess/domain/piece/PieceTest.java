package chess.domain.piece;

import chess.domain.piece.position.File;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.position.PiecePosition.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Piece 은")
class PieceTest {

    @Test
    void 위치와_색상을_가지고_생성된다() {
        // when & then
        assertDoesNotThrow(() -> new Piece(Color.WHITE, of('a', 1)) {

            @Override
            protected void validateMovable(final Path path) {

            }

            @Override
            protected WayPoints wayPointsWithCondition(final Path path) {
                return null;
            }

            @Override
            public boolean isKing() {
                return false;
            }

            @Override
            public boolean isPawn() {
                return false;
            }

            @Override
            public double score() {
                return 0;
            }
        });
    }

    @Test
    void clone_할_수_있다() {
        // given
        final MyPiece myPiece = new MyPiece(Color.BLACK, of('a', 1));

        // when
        final Piece clone = myPiece.clone();

        // then
        assertThat(clone).isExactlyInstanceOf(MyPiece.class);
    }

    static class MyPiece extends Piece {
        public MyPiece(final Color color, final PiecePosition piecePosition) {
            super(color, piecePosition);
        }

        @Override
        protected void validateMovable(final Path path) {

        }

        @Override
        protected WayPoints wayPointsWithCondition(final Path path) {
            return null;
        }

        @Override
        public boolean isKing() {
            return false;
        }

        @Override
        public boolean isPawn() {
            return false;
        }

        @Override
        public double score() {
            return 0;
        }
    }

    @Test
    void 경로탐색시_같은_위치면_예외() {
        // given
        Piece myPiece = new MyPiece(Color.BLACK, of('a', 1));
        // when & then
        assertThatThrownBy(() -> myPiece.wayPointsWithCondition(of('a', 1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 어느_File에_위치해있는지_알_수_있다() {
        // given
        Piece myPiece = new MyPiece(Color.BLACK, of('a', 1));
        File file = new File('a');
        // when & then
        assertThat(myPiece.file()).isEqualTo(file);
    }
}
