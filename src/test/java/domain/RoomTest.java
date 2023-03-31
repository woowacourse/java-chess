package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("방의 ")
class RoomTest {
    @DisplayName("이름은 1 ~ 20 글자이다")
    @Test
    void roomName() {
        //given
        final List<String> names = List.of("A", "ABCDQWERTEGSDFSJJKLW");

        //when

        //then
        assertThat(names).allSatisfy(name -> assertDoesNotThrow(() -> new Room(name)));
    }

    @DisplayName("이름이 1 ~ 20 글자가 아니면 예외가 발생한다")
    @Test
    void roomName2() {
        //given
        final List<String> names = List.of("", "ABCDQWERTEGSDFSJJKLWA");

        //when

        //then
        assertThat(names).allSatisfy(name -> assertThatThrownBy(() -> new Room(name))
                .isInstanceOf(IllegalArgumentException.class));
    }
}
