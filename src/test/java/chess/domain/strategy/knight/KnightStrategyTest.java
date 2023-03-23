package chess.domain.strategy.knight;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.dto.PositionDto;
import chess.domain.dto.req.MoveRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class KnightStrategyTest {

    private KnightStrategy knightStrategy;
    private MoveRequest failMoveRequest;

    @BeforeEach
    void setUp() {
        knightStrategy = new KnightStrategy();

        List<Position> piecesExist = List.of(Position.from(2, 'a'));
        failMoveRequest = MoveRequest.from(
                piecesExist,
                Color.WHITE,
                new PositionDto(Position.from(0, 'c')),
                new PositionDto(Position.from(3, 'a')));
    }

    @Test
    @DisplayName("rank 차이가 1, file 차이가 2가 아닐 때 움직일 수 없다.")
    void cantMoveRankIs1AndFileIs2() {
        // when, then
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> knightStrategy.validateDirection(failMoveRequest)
        );
    }

    @Test
    @DisplayName("rank 차이가 1, file 차이가 2이면 움직일 수 있다.")
    void canMoveRankIs1AndFileIs2() {
        // given
        List<Position> piecesExist = List.of(Position.from(2, 'a'));

        MoveRequest successMoveRequest = MoveRequest.from(
                piecesExist,
                Color.WHITE,
                new PositionDto(Position.from(0, 'b')),
                new PositionDto(Position.from(1, 'd')));

        // when, then
        Assertions.assertDoesNotThrow(
                () -> knightStrategy.validateDirection(successMoveRequest)
        );
    }

    @Test
    @DisplayName("rank 차이가 2이고, file 차이가 1이 아닐 때 움직일 수 없다.")
    void cantMoveRankIs2AndFileIs1() {
        // when, then
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> knightStrategy.validateDirection(failMoveRequest)
        );
    }

    @Test
    @DisplayName("rank 차이가 2 이고, file 차이가 1일 때 움직일 수 있다.")
    void canMoveRankIs2AndFileIs1() {
        // given
        List<Position> piecesExist = List.of(Position.from(2, 'a'));

        MoveRequest successMoveRequest = MoveRequest.from(
                piecesExist,
                Color.WHITE,
                new PositionDto(Position.from(0, 'b')),
                new PositionDto(Position.from(2, 'a')));

        // when, then
        Assertions.assertDoesNotThrow(
                () -> knightStrategy.validateDirection(successMoveRequest)
        );
    }

}
