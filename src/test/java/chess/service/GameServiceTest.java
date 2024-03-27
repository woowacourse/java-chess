package chess.service;

import static chess.fixture.MovementFixture.createMovements;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import chess.domain.square.Movement;
import chess.repository.FakeMovementDao;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 로직")
class GameServiceTest {
    FakeMovementDao moveRepository;
    GameService gameService;

    @BeforeEach
    void setUp() {
        moveRepository = new FakeMovementDao();
        moveRepository.deleteAll();

        gameService = new GameService(moveRepository);
    }

    @DisplayName("게임방 식별자로 보드의 움직임을 모두 찾는다")
    @Test
    void findMoves() {
        //given
        long roomId = 1L;

        //when
        List<Movement> movements = gameService.findMoves(roomId);

        //then
        assertArrayEquals(movements.toArray(), createMovements().toArray());
    }

    @DisplayName("움직임을 생성한다")
    @Test
    void createMove() {
        //given
        long roomId = 2L;
        String source = "a2";
        String target = "a3";

        //when
        gameService.createMove(roomId, source, target);
        List<Movement> movements = moveRepository.findAllByRoomId(roomId);

        //then
        assertAll(
                () -> assertThat(movements).hasSize(1),
                () -> assertThat(movements.get(0).isCross()).isTrue(),
                () -> assertThat(movements.get(0).calculateMaxDistance()).isEqualTo(1)
        );
    }
}
