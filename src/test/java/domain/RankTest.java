package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class RankTest {

    @Test
    @DisplayName("Color 와 initRank.NONE 를 입력하면 None Rank 를 생성한다.")
    void givenNoneRank_thenCreateNoneRank() {
        Set<Piece> duplicationRank = new HashSet<>(Rank.initRankToRank(InitRank.NONES, Color.NONE)
                .getRank());

        assertThat(duplicationRank)
                .hasSize(1)
                .contains(Piece.of(Type.NONE, Color.NONE));
    }

    @Test
    @DisplayName("Color 와 initRank.PAWN 를 입력하면 해당 Rank 를 생성한다.")
    void givenPawnRank_thenCreatePawnRank() {
        Set<Piece> duplicationBlack = new HashSet<>(Rank.initRankToRank(InitRank.PAWNS, Color.BLACK)
                .getRank());
        Set<Piece> duplicationWhite = new HashSet<>(Rank.initRankToRank(InitRank.PAWNS, Color.WHITE)
                .getRank());

        assertThat(duplicationBlack)
                .hasSize(1)
                .contains(Piece.of(Type.PAWN, Color.BLACK));
        assertThat(duplicationWhite)
                .hasSize(1)
                .contains(Piece.of(Type.PAWN, Color.WHITE));
    }

    @Test
    @DisplayName("Color 와 initRank.OTHERS 를 입력하면 해당 Rank 를 생성한다.")
    void givenOtherRank_thenCreateOtherRank() {
        final List<Piece> blackOtherRank = Rank.initRankToRank(InitRank.OTHERS, Color.BLACK)
                .getRank();
        final List<Piece> whiteOtherRank = Rank.initRankToRank(InitRank.OTHERS, Color.WHITE)
                .getRank();

        assertThat(blackOtherRank)
                .hasSize(8)
                .extracting("color")
                .containsOnly(Color.BLACK);
        assertThat(whiteOtherRank)
                .hasSize(8)
                .extracting("color")
                .containsOnly(Color.WHITE);
    }

}
