package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MovementTest {

    @Test
    @DisplayName("체스 이동 경로에 장애물(같은 편 또는 다른 편 기물)이 존재하지 않을 경우 false를 리턴한다.")
    void shouldFailObstacleInPieceRoute() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a2");
        Direction direction = Direction.NORTH;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(movement.hasObstacleInRoute(chessBoard)).isFalse();
    }

    @Test
    @DisplayName("체스 이동 경로에 장애물(같은 편 또는 다른 편 기물)이 존재할 경우 true를 리턴한다.")
    void shouldSucceedObstacleInPieceRoute() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a1");
        Direction direction = Direction.NORTH;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(movement.hasObstacleInRoute(chessBoard)).isTrue();
    }

    @Test
    @DisplayName("source포지션, 이동 방향, 거리 정보가 입력될 때 target 포지션 객체를 반환한다.")
    void shouldSucceedFindTargetPosition() {
        Position sourcePosition = Position.findPosition("a2");
        Direction direction = Direction.NORTH;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(movement.findTargetPosition()).isEqualTo(Position.findPosition("a5"));
    }

    @Test
    @DisplayName("체스판 범위를 벗어난 target 포지션 객체를 찾을 경우 예외가 발생한다.")
    void shouldFailFindTargetPosition() {
        Position sourcePosition = Position.findPosition("a7");
        Direction direction = Direction.NORTH;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThatThrownBy(movement::findTargetPosition)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 포지션은 체스판 범위 밖입니다.");
    }
}
