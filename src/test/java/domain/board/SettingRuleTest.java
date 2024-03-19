package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SettingRuleTest {

    @Test
    @DisplayName("랭크가 2 또는 7일 경우 폰을 반환한다.")
    void findPieceByPosition_RankTwoOrSeven_Pawn() {
        SettingRule settingRule = new SettingRule();

        assertAll(
                () -> assertThat(settingRule.findPieceByPosition(Rank.TWO, File.A))
                        .isEqualTo(new Piece(Type.PAWN, Color.WHITE)),
                () -> assertThat(settingRule.findPieceByPosition(Rank.SEVEN, File.A))
                        .isEqualTo(new Piece(Type.PAWN, Color.BLACK))
        );
    }
}
