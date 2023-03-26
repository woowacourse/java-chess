package chess.domain.piece;

import chess.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {

    Knight whiteKnight = new Knight(Color.WHITE);
    Knight blackKnight = new Knight(Color.BLACK);

    @Test
    @DisplayName("color를 입력하면 Knight 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingKnight() {

        Color black = Color.BLACK;
        Color white = Color.WHITE;

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> new Knight(black)),
                () -> assertDoesNotThrow(() -> new Knight(white))
        );
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 북북동 방향이 반환된다.")
    void shouldSucceedToNorthNorthEastDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("b1");
        Position targetPosition = Position.findPosition("c3");

        assertThat(whiteKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_NORTH_EAST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 북북서 방향이 반환된다.")
    void shouldSucceedToNorthNorthWestDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("b1");
        Position targetPosition = Position.findPosition("a3");

        assertThat(whiteKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_NORTH_WEST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 북동동 방향이 반환된다.")
    void shouldSucceedToNorthEastEastDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("b1");
        Position targetPosition = Position.findPosition("d2");

        assertThat(whiteKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_EAST_EAST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 북서서 방향이 반환된다.")
    void shouldSucceedToNorthWestWestDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("g1");
        Position targetPosition = Position.findPosition("e2");

        assertThat(whiteKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH_WEST_WEST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 남남동 방향이 반환된다.")
    void shouldSucceedToSouthSouthEastDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("a8");
        Position targetPosition = Position.findPosition("b6");

        assertThat(blackKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_SOUTH_EAST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 남남서 방향이 반환된다.")
    void shouldSucceedToSouthSouthWestDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("h8");
        Position targetPosition = Position.findPosition("g6");

        assertThat(blackKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_SOUTH_WEST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 남동동 방향이 반환된다.")
    void shouldSucceedToSouthEastEastDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("a8");
        Position targetPosition = Position.findPosition("c7");

        assertThat(blackKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_EAST_EAST);
    }

    @Test
    @DisplayName("화이트 나이트의 현재 위치와 목표 위치를 입력할 때 남서서 방향이 반환된다.")
    void shouldSucceedToSouthWestWestDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("h8");
        Position targetPosition = Position.findPosition("f7");

        assertThat(blackKnight.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH_WEST_WEST);
    }


    @Test
    @DisplayName("나이트가 두번을 이동하면 예외가 발생한다.")
    void shouldFailToFindDistance() {
        Position sourcePosition1 = Position.findPosition("b1");
        Position targetPosition1 = Position.findPosition("d5");
        Position sourcePosition2 = Position.findPosition("b8");
        Position targetPosition2 = Position.findPosition("d4");
        Direction northDirection = Direction.NORTH_NORTH_EAST;
        Direction southDirection = Direction.SOUTH_SOUTH_EAST;

        assertAll(
                () -> assertThatThrownBy(() -> whiteKnight.findDistance(northDirection, sourcePosition1, targetPosition1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] Knight는 한 번만 이동할 수 있습니다."),
                () -> assertThatThrownBy(() -> blackKnight.findDistance(southDirection, sourcePosition2, targetPosition2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] Knight는 한 번만 이동할 수 있습니다.")
        );
    }

    @Test
    @DisplayName("이동 방향, 현재 위치, 목표 위치가 입력될 때 현재 위치에서 목표 위치까지의 단위 거리가 반환된다")
    void shouldSucceedToFindDistance() {
        Position sourcePosition1 = Position.findPosition("b1");
        Position targetPosition1 = Position.findPosition("c3");
        Position sourcePosition2 = Position.findPosition("b8");
        Position targetPosition2 = Position.findPosition("a6");
        Direction northDirection = Direction.NORTH_NORTH_EAST;
        Direction southDirection = Direction.SOUTH_SOUTH_WEST;
        int expectedDistance = 1;

        assertAll(
                () -> assertThat(whiteKnight.findDistance(northDirection, sourcePosition1, targetPosition1)).isEqualTo(expectedDistance),
                () -> assertThat(blackKnight.findDistance(southDirection, sourcePosition2, targetPosition2)).isEqualTo(expectedDistance)
        );
    }

    @Test
    @DisplayName("화이트 나이트가 이동할 수 있으면 true가 반환된다.")
    void shouldSucceedWhiteKnightMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("b1");
        Direction direction = Direction.NORTH_NORTH_EAST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whiteKnight.isMovable(movement, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("화이트 나이트가 이동할 수 없으면 false가 반환된다.")
    void shouldFailWhiteKnightMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("b1");
        Direction direction = Direction.NORTH_EAST_EAST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whiteKnight.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("블랙 나이트가 이동할 수 있으면 true가 반환된다.")
    void shouldSucceedBlackKnightMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("b8");
        Direction direction = Direction.SOUTH_SOUTH_EAST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackKnight.isMovable(movement, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("블랙 나이트가 이동할 수 없으면 false가 반환된다.")
    void shouldFailBlackKnightMove() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("b8");
        Direction direction = Direction.SOUTH_EAST_EAST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackKnight.isMovable(movement, chessBoard)).isFalse();
    }
}