package chess.dto;

import chess.domain.position.Position;
import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveRequestDtoTest {

    @Test
    @DisplayName("json으로 Dto를 생성한다.")
    void fromJson() {
        // given
        final Gson gson = new Gson();
        final String json = "{\n"
                + "  \"from\": \"a2\",\n"
                + "  \"to\": \"a4\"\n"
                + "}";

        // when
        final MoveRequestDto dto = gson.fromJson(json, MoveRequestDto.class);

        // then
        Assertions.assertThat(dto.getFrom()).isEqualTo(Position.from("a2"));
        Assertions.assertThat(dto.getTo()).isEqualTo(Position.from("a4"));
    }
}