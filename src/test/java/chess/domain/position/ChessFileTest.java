package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessFileTest {

    @DisplayName("주어진 File의 값으로 File을 찾는다.")
    @Test
    void findByValue() {
        // given
        String fileValue = "a";

        // when
        ChessFile file = ChessFile.findByValue(fileValue);

        // then
        assertThat(file).isEqualTo(ChessFile.A);
    }

    @DisplayName("체스 파일 범위에 해당하지 않는 값을 조회하면 예외를 발생시킨다.")
    @Test
    void unknownChessFileValue() {
        // given
        String fileValue = "z";

        // when & then
        assertThatThrownBy(() -> ChessFile.findByValue(fileValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스 파일 범위에 해당하지 않는 값입니다.");
    }

    @DisplayName("랭크와 랭크 사이의 랭크들을 조회한다.")
    @Test
    void findRankBetween() {
        // given
        ChessFile start = ChessFile.A;
        ChessFile end = ChessFile.H;
        List<ChessFile> expected = List.of(ChessFile.B, ChessFile.C, ChessFile.D, ChessFile.E, ChessFile.F, ChessFile.G);

        // when
        List<ChessFile> rankBetween = ChessFile.findBetween(start, end);

        // then
        assertThat(expected).containsAll(rankBetween);
    }
}