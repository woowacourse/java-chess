package chess.domain.piece;

import chess.domain.board.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<List<Position>> whiteMoveStrategy = Pawn.createWhite().vectors(Position.of("c5"));
        assertThat(whiteMoveStrategy.get(0)).containsExactly(Position.of("c6"));
        assertThat(whiteMoveStrategy.get(1)).containsExactly(Position.of("b6"), Position.of("d6"));

        List<List<Position>> blackMoveStrategy = Pawn.createBlack().vectors(Position.of("c5"));
        assertThat(blackMoveStrategy.get(0)).containsExactly(Position.of("c4"));
        assertThat(blackMoveStrategy.get(1)).containsExactly(Position.of("b4"), Position.of("d4"));
    }

    @Test
    @DisplayName("폰 초기이동 가능성 테스트")
    void initMovableTest() {
        List<List<Position>> whiteMoveStrategy = Pawn.createWhite().vectors(Position.of("c2"));
        assertThat(whiteMoveStrategy.get(0)).containsExactly(Position.of("c3"), Position.of("c4"));
        assertThat(whiteMoveStrategy.get(1)).containsExactly(Position.of("b3"), Position.of("d3"));

        List<List<Position>> blackMoveStrategy = Pawn.createBlack().vectors(Position.of("c7"));
        assertThat(blackMoveStrategy.get(0)).containsExactly(Position.of("c6"), Position.of("c5"));
        assertThat(blackMoveStrategy.get(1)).containsExactly(Position.of("b6"), Position.of("d6"));
    }

    @Test
    @DisplayName("폰 경계 이동 가능성 테스트")
    void borderTest() {
        assertThat(Pawn.createWhite().vectors(Position.of("c8")).get(0))
                .containsExactly(Position.of("c8"));
        assertThat(Pawn.createWhite().vectors(Position.of("c8")).get(0))
                .containsExactly(Position.of("c8"));

        assertThat(Pawn.createWhite().vectors(Position.of("a5")).get(0))
                .containsExactly(Position.of("a6"));
        assertThat(Pawn.createWhite().vectors(Position.of("a5")).get(1))
                .containsExactly(Position.of("a5"), Position.of("b6"));
//
//        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("h5")).get(0))
//            .containsAll(Arrays.asList(Position.valueOf("g6"), Position.valueOf("h6"), Position.valueOf("h5")));
//
//        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("a8")).get(0))
//            .containsAll(Collections.singletonList(Position.valueOf("a8")));
//
//        assertThat(Pawn.createWhite().moveStrategy(Position.valueOf("h8")).get(0))
//            .containsAll(Collections.singletonList(Position.valueOf("h8")));
//
//        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("c1")).get(0))
//            .containsAll(Collections.singletonList(Position.valueOf("c1")));
//
//        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("a5")).get(0))
//            .containsAll(Arrays.asList(Position.valueOf("a5"), Position.valueOf("a4"), Position.valueOf("b4")));
//
//        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("h5")).get(0))
//            .containsAll(Arrays.asList(Position.valueOf("g4"), Position.valueOf("h4"), Position.valueOf("h5")));
//
//        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("a1"))
//            .get(0)).containsAll(Collections.singletonList(Position.valueOf("a1")));
//
//        assertThat(Pawn.createBlack().moveStrategy(Position.valueOf("h1"))
//            .get(0)).containsAll(Collections.singletonList(Position.valueOf("h1")));
    }
}
