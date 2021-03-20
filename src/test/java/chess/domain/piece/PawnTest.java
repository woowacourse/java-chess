package chess.domain.piece;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰 생성 테스트")
    void createTest() {
        assertThat(Pawn.createWhite()).isInstanceOf(Pawn.class);
        assertThat(Pawn.createBlack()).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("폰 색깔 테스트")
    void isBlackTest() {
        assertThat(Pawn.createWhite().isBlack()).isFalse();
        assertThat(Pawn.createBlack().isBlack()).isTrue();
    }

    @Test
    @DisplayName("폰 이동 가능성 테스트")
    void movableTest() {
        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("c5")))
                .contains(Position.valueOf("b6"), Position.valueOf("c6"), Position.valueOf("d6"));

        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("c5")))
                .contains(Position.valueOf("b4"), Position.valueOf("c4"), Position.valueOf("d4"));
    }

    @Test
    @DisplayName("폰 경계 이동 가능성 테스트")
    void borderTest() {
        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("c8"))).containsAll(Collections.singletonList(
                Position.valueOf("c8")
        ));
        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("a5"))).containsAll(Arrays.asList(
                Position.valueOf("a5"), Position.valueOf("a6"), Position.valueOf("b6")
        ));
        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("h5"))).containsAll(Arrays.asList(
                Position.valueOf("g6"), Position.valueOf("h6"), Position.valueOf("h5")
        ));
        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("a8"))).containsAll(Collections.singletonList(
                Position.valueOf("a8")
        ));
        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("h8"))).containsAll(Collections.singletonList(
                Position.valueOf("h8")
        ));

        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("c1"))).containsAll(Collections.singletonList(
                Position.valueOf("c1")
        ));
        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("a5"))).containsAll(Arrays.asList(
                Position.valueOf("a5"), Position.valueOf("a4"), Position.valueOf("b4")
        ));
        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("h5"))).containsAll(Arrays.asList(
                Position.valueOf("g4"), Position.valueOf("h4"), Position.valueOf("h5")
        ));
        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("a1"))).containsAll(Collections.singletonList(
                Position.valueOf("a1")
        ));
        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("h1"))).containsAll(Collections.singletonList(
                Position.valueOf("h1")
        ));
    }
}
