package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementTest {

    @Test
    @DisplayName("of() : file과 rank를 통해 Movement를 생성할 수 있다.")
    void test_of() throws Exception {
        //when & then
        assertDoesNotThrow(() -> Movement.of(1, 1));
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "3,1",
            "1,3",
            "-2,3"
    })
    @DisplayName("of() : Movement에 존재하지 않는 file이나 rank를 통해 생성한다면 NoSuchElementException이 발생한다.")
    void test_of_NoSuchElementException(final int file, final int rank) throws Exception {
        //when & then
        assertThatThrownBy(() -> Movement.of(file, rank))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("이동할 수 없는 방향입니다.");
    }

    @Test
    @DisplayName("nextPosition() : 다음 위치로 움직일 수 있다.")
    void test_nextPosition() throws Exception {
        //given
        final File toFile = File.from(7);
        final Rank toRank = Rank.from(7);

        //when
        final Position to = Movement.UR.nextPosition(toFile, toRank);

        //then
        assertAll(
                () -> assertEquals(8, to.file().value()),
                () -> assertEquals(8, to.rank().value())
        );
    }
}
