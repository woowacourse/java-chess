package chess.domain.piece;

import chess.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BishopTest {

    Bishop whiteBishop = new Bishop(Color.WHITE);
    Bishop blackBishop = new Bishop(Color.BLACK);

    @Test
    @DisplayName("color를 입력하면 Bishop 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingBishop() {

        Color black = Color.BLACK;
        Color white = Color.WHITE;

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> new Bishop(black)),
                () -> assertDoesNotThrow(() -> new Bishop(white))
        );
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 북동쪽 방향을 반환한다.")
    void shouldSucceedToFindNorthEastDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f5");

        assertThat(whiteBishop.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_EAST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 북서쪽 방향을 반환한다.")
    void shouldSucceedToFindNorthWestDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("b5");

        assertThat(whiteBishop.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_WEST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 남동쪽 방향을 반환한다.")
    void shouldSucceedToFindSouthEastDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f1");

        assertThat(whiteBishop.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_EAST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 남서쪽 방향을 반환한다.")
    void shouldSucceedToFindSouthWestDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("b1");

        assertThat(whiteBishop.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_WEST);
    }

    @Test
    @DisplayName("북동, 북서, 남동, 남서 방향 어디로도 이동할 수 없을 때 예외가 발생한다.")
    void shouldFailToFindDirection() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("a3");

        assertThatThrownBy(() -> whiteBishop.findMovableDirection(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 북동,북서,남동,남서 방향 중 이동 가능한 방향이 없습니다.");
    }

    @Test
    @DisplayName("이동 방향, 현재 위치, 목표 위치가 입력될 때 현재 위치에서 목표 위치까지의 단위 거리가 반환된다")
    void shouldSucceedToFindDistance() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("f5");
        Direction direction = Direction.NORTH_EAST;
        int expectedDistance = 2;

        assertThat(whiteBishop.findDistance(direction, sourcePosition, targetPosition)).isEqualTo(expectedDistance);
    }

    @Test
    @DisplayName("이동 방향이 맞지 않는 포지션 사이의 간격을 구할 경우 예외가 발생한다.")
    void shouldFailToFindToFindDistance() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("d5");
        Direction direction = Direction.NORTH_EAST;

        assertThatThrownBy(() -> whiteBishop.findDistance(direction, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 포지션은 체스판 범위 밖입니다.");
    }

    @Test
    @DisplayName("화이트 비숍이 움직일 수 없는 경우 false를 반환한다.")
    void shouldFailWhiteBishopMove() {

        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("c1");
        Direction direction = Direction.NORTH_EAST;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whiteBishop.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("블랙 비숍이 움직일 수 없는 경우 false를 반환한다.")
    void shouldFailBlackBishopMove() {

        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("c8");
        Direction direction = Direction.SOUTH_EAST;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackBishop.isMovable(movement, chessBoard)).isFalse();
    }
}