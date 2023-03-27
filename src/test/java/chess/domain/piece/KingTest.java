package chess.domain.piece;

import chess.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KingTest {

    King whiteKing = new King(Color.WHITE);
    King blackKing = new King(Color.BLACK);

    @Test
    @DisplayName("color를 입력하면 King 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingKing() {

        Color black = Color.BLACK;
        Color white = Color.WHITE;

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> new King(black)),
                () -> assertDoesNotThrow(() -> new King(white))
        );
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 동쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindEastDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("g2");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.EAST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 서쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindWestDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("d2");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.WEST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 남쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindSouthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("f1");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 북쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindNorthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("f3");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 북동쪽 방향을 반환한다.")
    void shouldSucceedToFindNorthEastDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("e4");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_EAST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 북서쪽 방향을 반환한다.")
    void shouldSucceedToFindNorthWestDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("c4");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_WEST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 남동쪽 방향을 반환한다.")
    void shouldSucceedToFindSouthEastDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("e2");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_EAST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력됐을 때 남서쪽 방향을 반환한다.")
    void shouldSucceedToFindSouthWestDirection() {

        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("c2");

        assertThat(whiteKing.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_WEST);
    }

    @Test
    @DisplayName("동서남북 방향 어디로도 이동할 수 없을 때 예외가 발생한다.")
    void shouldFailToFindDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("g4");

        assertThatThrownBy(() -> whiteKing.findMovableDirection(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 동,서,남,북,북동,북서,남동,남서 방향 중 이동 가능한 방향이 없습니다.");
    }

    @Test
    @DisplayName("이동 방향, 현재 위치, 목표 위치가 입력될 때 현재 위치에서 목표 위치까지의 단위 거리가 반환된다")
    void shouldSucceedToFindDistance() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("e4");
        Direction direction = Direction.NORTH_EAST;
        int expectedDistance = 1;

        assertThat(whiteKing.findDistance(direction, sourcePosition, targetPosition)).isEqualTo(expectedDistance);
    }

    @Test
    @DisplayName("King이 2칸 이동 후 이동 거리를 구할 경우 예외가 발생한다.")
    void shouldFailToFindToFindDistance() {
        Position sourcePosition = Position.findPosition("d3");
        Position targetPosition = Position.findPosition("e5");
        Direction direction = Direction.NORTH_EAST;

        assertThatThrownBy(() -> whiteKing.findDistance(direction, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] King은 한 칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("whiteKing이 움직일 수 없다면 false를 리턴한다.")
    void shouldFailWhiteKingMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("e1");
        Direction direction = Direction.NORTH;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whiteKing.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("blackKing이 움직일 수 없다면 false를 리턴한다.")
    void shouldFailBlackKingMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("e8");
        Direction direction = Direction.SOUTH;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackKing.isMovable(movement, chessBoard)).isFalse();
    }
}