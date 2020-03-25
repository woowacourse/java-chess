package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MoveTypeTest {
    @Test
    @DisplayName("직선 세로")
    void straightTest1() {
        Position source = new Position(File.A, Rank.ONE);
        Position target = new Position(File.A, Rank.EIGHT);
        assertThat(MoveType.of(source, target) == MoveType.STRAIGHT).isTrue();
    }

    @Test
    @DisplayName("직선 가로")
    void straightTest2() {
        Position source = new Position(File.A, Rank.TWO);
        Position target = new Position(File.H, Rank.TWO);
        assertThat(MoveType.of(source, target) == MoveType.STRAIGHT).isTrue();
    }

    @Test
    @DisplayName("좌상 대각선")
    void crossTest1() {
        Position source = new Position(File.B, Rank.TWO);
        Position target = new Position(File.A, Rank.THREE);
        assertThat(MoveType.of(source, target) == MoveType.CROSS).isTrue();
    }

    @Test
    @DisplayName("좌하 대각선")
    void crossTest2() {
        Position source = new Position(File.B, Rank.TWO);
        Position target = new Position(File.A, Rank.ONE);
        assertThat(MoveType.of(source, target) == MoveType.CROSS).isTrue();
    }

    @Test
    @DisplayName("우상 대각선")
    void crossTest3() {
        Position source = new Position(File.B, Rank.TWO);
        Position target = new Position(File.H, Rank.EIGHT);
        assertThat(MoveType.of(source, target) == MoveType.CROSS).isTrue();
    }

    @Test
    @DisplayName("우하 대각선")
    void crossTest4() {
        Position source = new Position(File.A, Rank.EIGHT);
        Position target = new Position(File.H, Rank.ONE);
        assertThat(MoveType.of(source, target) == MoveType.CROSS).isTrue();
    }

    @Test
    @DisplayName("나이트 상좌")
    void knight1() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.C, Rank.SEVEN);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 상우")
    void knight2() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.E, Rank.SEVEN);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 우상")
    void knight3() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.F, Rank.SIX);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 우하")
    void knight4() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.F, Rank.FOUR);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 하우")
    void knight5() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.E, Rank.THREE);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 하좌")
    void knight6() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.C, Rank.THREE);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 좌상")
    void knight7() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.B, Rank.SIX);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }

    @Test
    @DisplayName("나이트 좌하")
    void knight8() {
        Position source = new Position(File.D, Rank.FIVE);
        Position target = new Position(File.B, Rank.FOUR);
        assertThat(MoveType.of(source, target) == MoveType.KNIGHT).isTrue();
    }


}