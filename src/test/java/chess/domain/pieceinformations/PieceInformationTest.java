package chess.domain.pieceinformations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceInformationTest {

    @Test
    @DisplayName("이름 가져오기")
    void getName() {
        assertThat(PieceInformation.BISHOP.getName()).isEqualTo("b");
    }

    @Test
    @DisplayName("점수 가져오기")
    void getScore() {
        assertThat(PieceInformation.BISHOP.getScore()).isEqualTo(3);
    }

}