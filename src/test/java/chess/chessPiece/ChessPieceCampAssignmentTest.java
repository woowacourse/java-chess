package chess.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.chessboard.Numbering;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessPieceCampAssignmentTest {

    ChessPieceCampAssignment instance = ChessPieceCampAssignment.getInstance();

    @Test
    void 넘버링이_1_또는_2인_경우_진영은_흰색이다() {
        //given, when
        Camp camp1 = instance.determineCamp(Numbering.ONE);
        Camp camp2 = instance.determineCamp(Numbering.TWO);

        //then
        assertAll(
                () -> assertThat(camp1).isEqualTo(Camp.WHITE),
                () -> assertThat(camp2).isEqualTo(Camp.WHITE)
        );
    }

    @Test
    void 넘버링이_7_또는_8인_경우_진영은_검정색이다() {
        //given, when
        Camp camp1 = instance.determineCamp(Numbering.SEVEN);
        Camp camp2 = instance.determineCamp(Numbering.EIGHT);

        //then
        assertAll(
                () -> assertThat(camp1).isEqualTo(Camp.BLACK),
                () -> assertThat(camp2).isEqualTo(Camp.BLACK)
        );
    }

    @Test
    void 넘버링이_3_4_5_6인_경우_예외를_발생시킨다() {
        //given, when, then
        assertAll(
                () -> assertThatThrownBy(() -> instance.determineCamp(Numbering.THREE)),
                () -> assertThatThrownBy(() -> instance.determineCamp(Numbering.FOUR)),
                () -> assertThatThrownBy(() -> instance.determineCamp(Numbering.FIVE)),
                () -> assertThatThrownBy(() -> instance.determineCamp(Numbering.SIX))
        );
    }
}
