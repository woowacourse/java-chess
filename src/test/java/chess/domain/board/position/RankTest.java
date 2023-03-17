package chess.domain.board.position;

import chess.domain.board.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "1","2","3","4",
            "5","6","7","8",
    })
    @DisplayName("from() : Rank 속에 value 가 있으면 Rank를 생성할 수 있다.")
    void test_from(final int value) throws Exception {
        //when
        final Rank rank = Rank.from(value);

        //then
        assertEquals(rank.value(), value);
    }

    @Test
    @DisplayName("from() : Rank 에 존재하지 않는 value로 Rank를 생성할 경우 NoSuchElementException 반환한다.")
    void test_from_NoSuchElementException() throws Exception {
        //when & then
        assertThatThrownBy(() -> Rank.from(9))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("이동할 수 없는 Rank 방향입니다.");
    }
}
