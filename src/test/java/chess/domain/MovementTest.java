package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MovementTest {

    @Test
    @DisplayName("룩의 이동 방향과 이동 거리를 입력하면 Movement 객체가 정상적으로 생성된다.")
    void shouldSucceedGeneratingRookMovement() {
        Position position = Position.findPosition("a1");
        Direction direction = Direction.EAST;
        int distance = 3;

        assertDoesNotThrow(() -> Movement.generateRookMovement(position, direction, distance));
    }

    @Test
    @DisplayName("룩의 이동 로직과 맞지 않는 이동 방향을 입력하여 Movement 객체 생성 시 예외가 발생한다.")
    void shouldFailGeneratingRookMovement() {
        Position position = Position.findPosition("a1");
        Direction direction = Direction.NORTH_EAST_EAST;
        int distance = 3;

        assertThatThrownBy(() -> Movement.generateRookMovement(position, direction, distance))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 룩은 해당 방향으로 이동할 수 없습니다.");
    }

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


//    @Test
//    @DisplayName("초기 포지션에 위치한 화이트폰은 북쪽으로 2칸을 이동할 수 있다.")
//    void shouldSucceedWhitePawnMoveToNorthTwoDistance() {
//        Position sourcePosition = Position.findPosition("a2");
//        Direction direction = Direction.NORTH;
//        int distance = 2;
//        Movement movement = new Movement(sourcePosition, direction, distance);
//        Pawn whitePawn = new Pawn(Color.WHITE);
//
//        org.assertj.core.api.Assertions.assertThat(movement.isFirstMovement(whitePawn)).isTrue();
//    }
}
