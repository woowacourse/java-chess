package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class InitRankTest {

    @Test
    @DisplayName("Others를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenOthers_thenReturnTypes() {
        assertThat(InitRank.from(InitRank.OTHERS).getTypes()).
                containsExactly(Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN, Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK);
    }

    @Test
    @DisplayName("Pawns를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenPawn_thenReturnTypes() {
        assertThat(InitRank.from(InitRank.PAWNS).getTypes()).
                containsExactly(Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN, Type.PAWN);
    }

    @Test
    @DisplayName("Nones를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenNone_thenReturnTypes() {
        assertThat(InitRank.from(InitRank.NONES).getTypes()).
                containsExactly(Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE, Type.NONE);
    }
}
