package domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PawnTest {
    @Nested
    class 화이트_폰 {
        private final Piece whitePawn = new Pawn(Color.WHITE);

        @Test
        void 직선_방향으로_한_칸_이동할_수_있다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.FIVE);
            Piece other = Empty.create();

            assertThatCode(() -> whitePawn.validateMovement(source, target, other))
                    .doesNotThrowAnyException();
        }

        @Test
        void 앞이_빈_공간이_아니면_직선_방향으로_이동할_수_없다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.FIVE);
            Piece other = new Pawn(Color.BLACK);

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("앞에 말이 있어서 이동할 수 없습니다.");
        }

        @Test
        void 초기_위치_일_때_직선_방향으로_두_칸_이동할_수_있다() {
            Position source = new Position(File.D, Rank.TWO);
            Position target = new Position(File.D, Rank.FOUR);
            Piece other = Empty.create();

            assertThatCode(() -> whitePawn.validateMovement(source, target, other))
                    .doesNotThrowAnyException();
        }

        @Test
        void 초기_위치가_아닐_때_직선_방향으로_두_칸_이동하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.THREE);
            Position target = new Position(File.D, Rank.FIVE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("초기 위치가 아닌 폰은 2칸 이동할 수 없습니다.");
        }

        @Test
        void 대각선_방향에_적이_있으면_이동할_수_있다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.FIVE);
            Piece other = new Pawn(Color.BLACK);

            assertThatCode(() -> whitePawn.validateMovement(source, target, other))
                    .doesNotThrowAnyException();
        }

        @Test
        void 대각선_방향에_적이_없을_때_이동할_경우_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.FIVE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("대각선 방향에 상대 말이 없어서 이동할 수 없습니다.");
        }

        @Test
        void 대각선_방향으로_두_칸_이동할_경우_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.F, Rank.SIX);
            Piece other = new Pawn(Color.BLACK);

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰은 직선 방향으로 1칸 또는 2칸, 대각 방향으로 1칸만 이동할 수 있습니다.");
        }

        @Test
        void 뒤로_이동하려고_하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.THREE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰은 뒤로 이동할 수 없습니다.");
        }

        @Test
        void L자_방향으로_이동하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.F, Rank.FIVE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("잘못된 방향으로 이동하고 있습니다.");
        }

        @Test
        void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.A, Rank.EIGHT);
            Piece other = Empty.create();

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("잘못된 방향으로 이동하고 있습니다.");
        }

        @Test
        void 같은_팀의_말을_잡으면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.FIVE);
            Piece other = new Pawn(Color.WHITE);

            assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    @Nested
    class 블랙_폰 {
        private final Piece blackPawn = new Pawn(Color.BLACK);

        @Test
        void 직선_방향으로_한_칸_이동할_수_있다() {
            Position source = new Position(File.D, Rank.FIVE);
            Position target = new Position(File.D, Rank.FOUR);
            Piece other = Empty.create();

            assertThatCode(() -> blackPawn.validateMovement(source, target, other))
                    .doesNotThrowAnyException();
        }

        @Test
        void 앞이_빈_공간이_아니면_직선_방향으로_이동할_수_없다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.THREE);
            Piece other = new Pawn(Color.WHITE);

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("앞에 말이 있어서 이동할 수 없습니다.");
        }

        @Test
        void 초기_위치_일_때_직선_방향으로_두_칸_이동할_수_있다() {
            Position source = new Position(File.D, Rank.SEVEN);
            Position target = new Position(File.D, Rank.FIVE);
            Piece other = Empty.create();

            assertThatCode(() -> blackPawn.validateMovement(source, target, other))
                    .doesNotThrowAnyException();
        }

        @Test
        void 초기_위치가_아닐_때_직선_방향으로_두_칸_이동하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.SIX);
            Position target = new Position(File.D, Rank.FOUR);
            Piece other = Empty.create();

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("초기 위치가 아닌 폰은 2칸 이동할 수 없습니다.");
        }

        @Test
        void 대각선_방향에_적이_있으면_이동할_수_있다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.THREE);
            Piece other = new Pawn(Color.WHITE);

            assertThatCode(() -> blackPawn.validateMovement(source, target, other))
                    .doesNotThrowAnyException();
        }

        @Test
        void 대각선_방향에_적이_없을_때_이동할_경우_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.THREE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("대각선 방향에 상대 말이 없어서 이동할 수 없습니다.");
        }

        @Test
        void 대각선_방향으로_두_칸_이동할_경우_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FIVE);
            Position target = new Position(File.F, Rank.THREE);
            Piece other = new Pawn(Color.WHITE);

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰은 직선 방향으로 1칸 또는 2칸, 대각 방향으로 1칸만 이동할 수 있습니다.");
        }

        @Test
        void 뒤로_이동하려고_하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.D, Rank.FIVE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰은 뒤로 이동할 수 없습니다.");
        }

        @Test
        void L자_방향으로_이동하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FIVE);
            Position target = new Position(File.B, Rank.FOUR);
            Piece other = Empty.create();

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("잘못된 방향으로 이동하고 있습니다.");
        }

        @Test
        void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FIVE);
            Position target = new Position(File.G, Rank.ONE);
            Piece other = Empty.create();

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("잘못된 방향으로 이동하고 있습니다.");
        }

        @Test
        void 같은_팀의_말을_잡으면_예외가_발생한다() {
            Position source = new Position(File.D, Rank.FOUR);
            Position target = new Position(File.E, Rank.THREE);
            Piece other = new Pawn(Color.BLACK);

            assertThatThrownBy(() -> blackPawn.validateMovement(source, target, other))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("같은 팀의 말을 잡을 수 없습니다.");
        }
    }
}
