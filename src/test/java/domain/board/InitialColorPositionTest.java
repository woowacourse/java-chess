package domain.board;

import domain.piece.Color;
import domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class InitialColorPositionTest {

    @Nested
    class WhiteTest {

        @ParameterizedTest
        @EnumSource(names = {"ONE", "TWO"})
        @DisplayName("랭크가 1또는 2일 경우 흰색을 반환한다.")
        void findPieceByPosition_RankTwo_WhitePawn(Rank rank) {
            assertThat(InitialColorPosition.find(rank))
                    .isEqualTo(Color.WHITE);
        }
    }

    @Nested
    class BlackTest {

        @ParameterizedTest
        @EnumSource(names = {"SEVEN", "EIGHT"})
        @DisplayName("랭크가 7또는 8일 경우 검은색을 반환한다.")
        void findPieceByPosition_RankTwo_WhitePawn(Rank rank) {
            assertThat(InitialColorPosition.find(rank))
                    .isEqualTo(Color.BLACK);
        }
    }
}
