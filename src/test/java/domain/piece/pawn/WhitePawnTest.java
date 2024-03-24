package domain.piece.pawn;

import domain.piece.Empty;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhitePawnTest {
    private final Piece whitePawn = new WhitePawn();

    @Test
    void 직선_방향으로_한_칸_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.FIVE);
        Piece other = Empty.getInstance();

        assertThatCode(() -> whitePawn.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 앞이_빈_공간이_아니면_직선_방향으로_이동할_수_없다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.FIVE);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 방향으로 이동하고 있습니다.");
    }

    @Test
    void 초기_위치_일_때_직선_방향으로_두_칸_이동할_수_있다() {
        Position source = new Position(File.D, Rank.TWO);
        Position target = new Position(File.D, Rank.FOUR);
        Piece other = Empty.getInstance();

        assertThatCode(() -> whitePawn.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 초기_위치가_아닐_때_직선_방향으로_두_칸_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.THREE);
        Position target = new Position(File.D, Rank.FIVE);
        Piece other = Empty.getInstance();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 방향으로 이동하고 있습니다.");
    }

    @Test
    void 대각선_방향에_적이_있으면_이동할_수_있다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = new BlackPawn();

        assertThatCode(() -> whitePawn.validateMovement(source, target, other))
                .doesNotThrowAnyException();
    }

    @Test
    void 대각선_방향에_적이_없을_때_이동할_경우_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = Empty.getInstance();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 방향으로 이동하고 있습니다.");
    }

    @Test
    void 대각선_방향으로_두_칸_이동할_경우_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.SIX);
        Piece other = new BlackPawn();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 방향으로 이동하고 있습니다.");
    }

    @Test
    void 뒤로_이동하려고_하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.D, Rank.THREE);
        Piece other = Empty.getInstance();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 이동해야 합니다.");
    }

    @Test
    void L자_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.FIVE);
        Piece other = Empty.getInstance();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 방향으로 이동하고 있습니다.");
    }

    @Test
    void 정의되지_않은_방향으로_이동하면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.A, Rank.EIGHT);
        Piece other = Empty.getInstance();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 방향으로 이동하고 있습니다.");
    }

    @Test
    void 같은_팀의_말을_잡으면_예외가_발생한다() {
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.E, Rank.FIVE);
        Piece other = new WhitePawn();

        assertThatThrownBy(() -> whitePawn.validateMovement(source, target, other))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 팀의 말을 잡을 수 없습니다.");
    }
}
