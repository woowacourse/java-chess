package chess.domain.piece;

import chess.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RookTest {

    Rook whiteRook = new Rook(Color.WHITE);
    Rook blackRook = new Rook(Color.BLACK);

    @Test
    @DisplayName("color를 입력하면 Rook 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingRook() {

        Color black = Color.BLACK;
        Color white = Color.WHITE;

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> new Rook(black)),
                () -> assertDoesNotThrow(() -> new Rook(white))
        );
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 동쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindEastDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("h2");

        assertThat(whiteRook.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.EAST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 서쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindWestDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("c2");

        assertThat(whiteRook.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.WEST);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 남쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindSouthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("f1");

        assertThat(whiteRook.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH);
    }

    @Test
    @DisplayName("현재 위치와 목표 위치가 입력될 때 북쪽 이동 방향을 리턴한다")
    void shouldSucceedToFindNorthDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("f4");

        assertThat(whiteRook.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH);
    }

    @Test
    @DisplayName("동서남북 방향 어디로도 이동할 수 없을 때 예외가 발생한다.")
    void shouldFailToFindDirection() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("g4");

        assertThatThrownBy(() -> whiteRook.findMovableDirection(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 동서남북 중 이동 가능한 방향이 없습니다.");
    }

    @Test
    @DisplayName("이동 방향, 현재 위치, 목표 위치가 입력될 때 현재 위치에서 목표 위치까지의 단위 거리가 반환된다")
    void shouldSucceedToFindDistance() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("c2");
        Direction direction = Direction.WEST;
        int expectedDistance = 3;

        assertThat(whiteRook.findDistance(direction, sourcePosition, targetPosition)).isEqualTo(expectedDistance);
    }

    @Test
    @DisplayName("이동 방향이 맞지 않는 포지션 사이의 간격을 구할 경우 예외가 발생한다.")
    void shouldFailToFindToFindDistance() {
        Position sourcePosition = Position.findPosition("f2");
        Position targetPosition = Position.findPosition("c3");
        Direction direction = Direction.WEST;

        assertThatThrownBy(() -> whiteRook.findDistance(direction, sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 포지션은 체스판 범위 밖입니다.");
    }

    @Test
    @DisplayName("화이트룩이 이동할 수 없을 경우 false를 반환한다.")
    void shouldFailWhiteRookMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a1");
        Direction direction = Direction.NORTH;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whiteRook.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("블랙룩이 이동할 수 없을 경우 false를 반환한다.")
    void shouldFailBlackRookMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a8");
        Direction direction = Direction.SOUTH;
        int distance = 3;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackRook.isMovable(movement, chessBoard)).isFalse();
    }
}