package chess.domain.strategy.bishop;


import chess.domain.Color;
import chess.domain.Position;
import chess.dto.PositionDto;
import chess.dto.request.MoveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public final class BishopStrategyTest {
    // TODO: 성공테스트
    @DisplayName("상하좌우로 움직일 수 없다.")
    @Nested
    class InvalidDirection {
        @DisplayName("위로 움직일 수 없다.")
        @Test
        void forward() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'f'));
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'f')),
                    new PositionDto(Position.from(6, 'f')));
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(moveRequest));
        }

        @DisplayName("밑으로 움직일 수 없다.")
        @Test
        void backward() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'd'));
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'd')),
                    new PositionDto(Position.from(3, 'd')));
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(moveRequest));
        }

        @DisplayName("오른쪽으로 움직일 수 없다.")
        @Test
        void right() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'd'));
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'd')),
                    new PositionDto(Position.from(4, 'e')));
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(moveRequest));
        }

        @DisplayName("왼쪽으로 움직일 수 없다.")
        @Test
        void left() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'd'));
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'd')),
                    new PositionDto(Position.from(4, 'c')));
            //when
            //then
            assertThatThrownBy(() -> new BishopStrategy().validateDirection(moveRequest));
        }
    }

}
