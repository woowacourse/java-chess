package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Nested
public class SettingRuleTest {

    private final SettingRule settingRule = new SettingRule();

    @Nested
    class PawnTest {

        @Test
        @DisplayName("랭크가 2일 경우 흰색 폰을 반환한다.")
        void findPieceByPosition_RankTwo_WhitePawn() {
            assertThat(settingRule.findPieceByPosition(Rank.TWO, File.A))
                    .isEqualTo(new Piece(Type.PAWN, Color.WHITE));
        }

        @Test
        @DisplayName("랭크가 2일 경우 검정색 폰을 반환한다.")
        void findPieceByPosition_RankSeven_BlackPawn() {
            assertThat(settingRule.findPieceByPosition(Rank.SEVEN, File.A))
                    .isEqualTo(new Piece(Type.PAWN, Color.BLACK));
        }
    }

    @Nested
    class KingTest {

        @Test
        @DisplayName("랭크가 1, 파일이 e일 경우 흰색 킹을 반환한다.")
        void findPieceByPosition_RankOneFileE_WhiteKing() {
            assertThat(settingRule.findPieceByPosition(Rank.ONE, File.E))
                    .isEqualTo(new Piece(Type.KING, Color.WHITE));
        }

        @Test
        @DisplayName("랭크가 8, 파일이 e일 경우 검정색 킹을 반환한다.")
        void findPieceByPosition_RankEightFileE_BlackKing() {
            assertThat(settingRule.findPieceByPosition(Rank.EIGHT, File.E))
                    .isEqualTo(new Piece(Type.KING, Color.BLACK));
        }
    }

}
