package domain;

import domain.chessboard.Empty;
import domain.chessboard.InitRank;
import domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.collection;


class InitRankTest {

    @Test
    @DisplayName("Others를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenOthers_thenReturnTypes() {
        //given
        final Set<? extends Class<?>> othersBlack = getClasses(InitRank.OTHERS_BLACK);
        final Set<? extends Class<?>> othersWhite = getClasses(InitRank.OTHERS_WHITE);

        // when&then
        assertThat(othersBlack).hasSize(5);
        assertThat(othersWhite).hasSize(5);
        assertThat(InitRank.OTHERS_BLACK.getSquareStatus()).extracting("color").containsOnly(Color.BLACK);
        assertThat(InitRank.OTHERS_WHITE.getSquareStatus()).extracting("color").containsOnly(Color.WHITE);
    }

    @Test
    @DisplayName("Pawns를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenPawn_thenReturnTypes() {
        final Set<? extends Class<?>> pawnBlack = getClasses(InitRank.PAWN_BLACK);
        final Set<? extends Class<?>> pawnWhite = getClasses(InitRank.PAWN_WHITE);

        assertThat(pawnBlack).hasSize(1);
        assertThat(pawnWhite).hasSize(1);
        assertThat(InitRank.PAWN_BLACK.getSquareStatus()).extracting("color").containsOnly(Color.BLACK);
        assertThat(InitRank.PAWN_WHITE.getSquareStatus()).extracting("color").containsOnly(Color.WHITE);
    }

    @Test
    @DisplayName("Nones를 입력하면 해당 타입 리스트를 반환받는다.")
    void givenNone_thenReturnTypes() {
        assertThat(getClasses(InitRank.EMPTY)).hasSize(1);
    }

    private Set<? extends Class<?>> getClasses(final InitRank initRank) {
        return InitRank.from(initRank)
                .getSquareStatus()
                .stream()
                .map(Object::getClass)
                .collect(toSet());
    }
}
