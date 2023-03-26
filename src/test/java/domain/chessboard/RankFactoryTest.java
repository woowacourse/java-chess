package domain.chessboard;

import domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;


class RankFactoryTest {

    @Test
    @DisplayName("Others를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenOthers_thenReturnTypes() {
        //given
        final Set<? extends Class<?>> othersBlack = getClasses(RankFactory.OTHERS_BLACK);
        final Set<? extends Class<?>> othersWhite = getClasses(RankFactory.OTHERS_WHITE);

        // when&then
        assertThat(othersBlack).hasSize(5);
        assertThat(othersWhite).hasSize(5);
        assertThat(RankFactory.OTHERS_BLACK.getSquareStatus()).extracting("color").containsOnly(Color.BLACK);
        assertThat(RankFactory.OTHERS_WHITE.getSquareStatus()).extracting("color").containsOnly(Color.WHITE);
    }

    @Test
    @DisplayName("Pawns를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenPawn_thenReturnTypes() {
        final Set<? extends Class<?>> pawnBlack = getClasses(RankFactory.PAWN_BLACK);
        final Set<? extends Class<?>> pawnWhite = getClasses(RankFactory.PAWN_WHITE);

        assertThat(pawnBlack).hasSize(1);
        assertThat(pawnWhite).hasSize(1);
        assertThat(RankFactory.PAWN_BLACK.getSquareStatus()).extracting("color").containsOnly(Color.BLACK);
        assertThat(RankFactory.PAWN_WHITE.getSquareStatus()).extracting("color").containsOnly(Color.WHITE);
    }

    @Test
    @DisplayName("Nones를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenNone_thenReturnTypes() {
        assertThat(getClasses(RankFactory.EMPTY)).hasSize(1);
    }

    private Set<? extends Class<?>> getClasses(final RankFactory rankFactory) {
        return RankFactory.from(rankFactory)
                .getSquareStatus()
                .stream()
                .map(Object::getClass)
                .collect(toSet());
    }

}
