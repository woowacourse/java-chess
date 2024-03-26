package view.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateRequestTest {

    @DisplayName("입력 받은 좌표 커멘드를 나누어 Requset에 담는다.")
    @Test
    void divideCommand() {
        CoordinateRequest coordinateRequest = CoordinateRequest.fromCommand("b1");

        Assertions.assertAll(
                () -> assertThat(coordinateRequest.row()).isEqualTo(7),
                () -> assertThat(coordinateRequest.column()).isEqualTo(1)
        );
    }
}
