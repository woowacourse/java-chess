package domain;

import domain.piece.ChessFile;
import domain.piece.ChessRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @DisplayName("주어진 위치 값(value)로 Position을 생성한다.")
    @Test
    void createPosition() {
        //given
        String source = "b2";
        Position expectedPosition = new Position(ChessFile.B, ChessRank.TWO);

        //when
        Position position = new Position(source);

        //then
        assertThat(position).isEqualTo(expectedPosition);
    }
}
