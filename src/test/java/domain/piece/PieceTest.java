package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PieceTest {
    @Nested
    class 룩 {
        private final Piece rook = new Piece(Type.ROOK, Color.WHITE);

        @Test
        void UP_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.F, Rank.EIGHT);

            List<Position> positions = rook.route(resource, target);
            assertThat(positions).hasSize(2);
            assertThat(positions).containsExactlyElementsOf(List.of(
                    new Position(File.F, Rank.SIX),
                    new Position(File.F, Rank.SEVEN)
            ));
        }

        @Test
        void DOWN_LEFT_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.C, Rank.TWO);

            assertThatThrownBy(() -> rook.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void L자_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.H, Rank.FOUR);

            assertThatThrownBy(() -> rook.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.A, Rank.ONE);

            assertThatThrownBy(() -> rook.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 비숍 {
        private final Piece bishop = new Piece(Type.BISHOP, Color.WHITE);

        @Test
        void UP_LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.C, Rank.TWO);
            Position target = new Position(File.F, Rank.FIVE);

            List<Position> positions = bishop.route(resource, target);
            assertThat(positions).hasSize(2);
            assertThat(positions).containsExactlyElementsOf(List.of(
                    new Position(File.D, Rank.THREE),
                    new Position(File.E, Rank.FOUR)
            ));
        }

        @Test
        void UP_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.C, Rank.TWO);
            Position target = new Position(File.C, Rank.FIVE);

            assertThatThrownBy(() -> bishop.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void L자_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.C, Rank.TWO);
            Position target = new Position(File.E, Rank.THREE);

            assertThatThrownBy(() -> bishop.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.F, Rank.FIVE);
            Position target = new Position(File.A, Rank.ONE);

            assertThatThrownBy(() -> bishop.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 퀸 {
        private final Piece queen = new Piece(Type.QUEEN, Color.WHITE);

        @Test
        void UP_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.EIGHT);

            List<Position> positions = queen.route(resource, target);
            assertThat(positions).hasSize(3);
            assertThat(positions).containsExactlyElementsOf(List.of(
                    new Position(File.D, Rank.FIVE),
                    new Position(File.D, Rank.SIX),
                    new Position(File.D, Rank.SEVEN)
            ));
        }

        @Test
        void DOWN_RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.G, Rank.ONE);

            List<Position> positions = queen.route(resource, target);
            assertThat(positions).hasSize(2);
            assertThat(positions).containsExactlyElementsOf(List.of(
                    new Position(File.E, Rank.THREE),
                    new Position(File.F, Rank.TWO)
            ));
        }

        @Test
        void L자_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.F, Rank.THREE);

            assertThatThrownBy(() -> queen.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.A, Rank.TWO);

            assertThatThrownBy(() -> queen.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    class 킹 {
        private final Piece king = new Piece(Type.KING, Color.WHITE);

        @Test
        void UP_RIGHT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.FIVE);

            List<Position> positions = king.route(resource, target);
            assertThat(positions).hasSize(0);
        }

        @Test
        void L자_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.F, Rank.THREE);

            assertThatThrownBy(() -> king.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void DOWN_방향으로_두_칸_이상_이동하면_예외가_발생한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.TWO);

            assertThatThrownBy(() -> king.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 거리입니다.");
        }
    }

    @Nested
    class 나이트 {
        private final Piece knight = new Piece(Type.KNIGHT, Color.WHITE);

        @Test
        void KNIGHT_UP_LEFT_방향으로_이동하는_경로를_반환한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.C, Rank.SIX);

            List<Position> positions = knight.route(resource, target);
            assertThat(positions).hasSize(0);
        }

        @Test
        void UP_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.FIVE);

            assertThatThrownBy(() -> knight.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }

        @Test
        void DOWN_LEFT_방향으로_이동하면_예외가_발생한다() {
            Position resource = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.B, Rank.TWO);

            assertThatThrownBy(() -> knight.route(resource, target))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 방향입니다.");
        }
    }
}
