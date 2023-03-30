package chess.domain.strategy.queen;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import chess.domain.piece.Position;
import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

final class QueenStrategyTest {

    @DisplayName("올바른 위치일 경우 타겟 포지션이 반환된다.")
    @ParameterizedTest(name = "rank: {0}, file: {1}")
    @CsvSource({"3,e", "4,e", "1,b", "3,h", "0,g", "3,g"})
    void returnTargetPosition(int rank, char file) {
        //given
        List<Position> piecesExist = List.of(Position.from(3, 'd'));
        //when
        MoveRequest moveRequest = MoveRequest.from(
                piecesExist,
                Color.WHITE,
                new PositionDto(Position.from(3, 'd')),
                new PositionDto(Position.from(rank, file)));
        //then
        assertDoesNotThrow(() -> new QueenStrategy().validateDirection(moveRequest));
    }

    @Nested
    @DisplayName("올바르지 않은 위치일 경우 예외가 발생한다")
    class Invalid {
        @ParameterizedTest(name = "rank: {0}, file: {1}")
        @CsvSource({"5, e", "6, c", "0, b", "1, h"})
        void throwException(int rank, char file) {
            //given
            List<Position> piecesExist = List.of(Position.from(3, 'd'));
            //when
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(3, 'd')),
                    new PositionDto(Position.from(rank, file)));
            //then
            assertThatThrownBy(() -> new QueenStrategy().validateDirection(moveRequest));
        }
    }

}
