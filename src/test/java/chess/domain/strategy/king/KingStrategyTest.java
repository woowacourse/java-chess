package chess.domain.strategy.king;

import chess.domain.Position;
import chess.domain.Color;
import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class KingStrategyTest {

    @DisplayName("모든 방향으로 한 칸 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource({"3,e", "4,e", "5,e", "3,f", "5,f", "3,g", "4,g", "5,g"})
    void canMove(int rank, char filter) {
        //given
        List<Position> piecesExist = List.of(Position.from(4, 'f'));
        //when
        MoveRequest moveRequest = MoveRequest.from(
                piecesExist,
                Color.WHITE,
                new PositionDto(Position.from(4, 'f')),
                new PositionDto(Position.from(rank, filter)));;
        //then
        assertDoesNotThrow(()-> new KingStrategy().validateDirection(moveRequest));
    }

    @DisplayName("한 칸 이상 움직일 수 없다.")
    @Nested
    class InvalidDistance {

        @DisplayName("두 칸 위로 움직이기")
        @Test
        void twoUp() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'd'));
            //when
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'd')),
                    new PositionDto(Position.from(6, 'd')));
            //then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(moveRequest));
        }

        @DisplayName("두 칸 아래로 움직이기")
        @Test
        void twoBack() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'd'));
            //when
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'd')),
                    new PositionDto(Position.from(2, 'd')));
            //then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(moveRequest));
        }

        @DisplayName("대각선으로 두 칸 움직이기")
        @Test
        void twoDiagonal() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'f'));
            //when
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'f')),
                    new PositionDto(Position.from(2, 'd')));
            //then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(moveRequest));
        }

        @DisplayName("왼쪽으로 여러 칸 움직이기")
        @Test
        void twoLeft() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'f'));
            //when
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'f')),
                    new PositionDto(Position.from(4, 'a')));
            //then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(moveRequest));
        }

        @DisplayName("오른쪽으로 여러 칸 움직이기")
        @Test
        void twoRight() {
            //given
            List<Position> piecesExist = List.of(Position.from(4, 'f'));
            //when
            MoveRequest moveRequest = MoveRequest.from(
                    piecesExist,
                    Color.WHITE,
                    new PositionDto(Position.from(4, 'f')),
                    new PositionDto(Position.from(4, 'h')));
            //then
            assertThatThrownBy(() -> new KingStrategy().validateDirection(moveRequest));
        }
    }
}
