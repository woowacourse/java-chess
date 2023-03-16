package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovablePositionTest {

    @Test
    @DisplayName("기물의 현재 포지션을 입력하면 십자가 문양으로 이동한 경로 그룹이 생성된다.")
    void shouldSuccessGenerateCrossPosition() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addCrossPosition(ChessBoard.generateChessBoard(), Position.initPosition(5, 3), true);

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(6, 3), Position.initPosition(7, 3), Position.initPosition(8, 3),
                Position.initPosition(4, 3), Position.initPosition(3, 3), Position.initPosition(2, 3), Position.initPosition(1, 3), Position.initPosition(5, 4),
                Position.initPosition(5, 5), Position.initPosition(5, 6), Position.initPosition(5, 7), Position.initPosition(5, 2)
        ));
    }

    @Test
    @DisplayName("addsd")
    void shouldSuccessFindKnightRoute() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addKnightPosition(ChessBoard.generateChessBoard(), Position.initPosition(2, 1));

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(1, 3), Position.initPosition(3, 3)
        ));
    }


    @Test
    @DisplayName("addsd")
    void shouldSuccessFindBlackPawnRoute() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addBlackPawnPosition(ChessBoard.generateChessBoard(), Position.initPosition(1, 7));

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(1, 6), Position.initPosition(1, 5)
        ));
    }

    @Test
    @DisplayName("addsd")
    void shouldSuccessFindWhitePawnRoute() {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.addWhitePawnPosition(ChessBoard.generateChessBoard(), Position.initPosition(1, 2));

        assertThat(movablePosition.getMovablePosition()).containsAll(List.of(Position.initPosition(1, 3), Position.initPosition(1, 4)
        ));
    }

}
