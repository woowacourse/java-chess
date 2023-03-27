package chess.controller.mapper;

import chess.controller.dto.PositionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PositionDtoMapperTest {

    @Test
    @DisplayName("rank, file이 주어지면 위치에 대한 dto를 생성한다.")
    void createPositionDto() {
        // given
        final int rank = 3;
        final int file = 3;

        // when, then
        final PositionDto positionDto = assertDoesNotThrow(() -> PositionDtoMapper.createPositionDto(rank, file));
        assertThat(positionDto)
                .isInstanceOf(PositionDto.class);
    }
}
