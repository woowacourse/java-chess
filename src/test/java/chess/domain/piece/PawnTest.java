package chess.domain.piece;

import chess.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PawnTest {

    Pawn whitePawn = new Pawn(Color.WHITE);
    Pawn blackPawn = new Pawn(Color.BLACK);

    @Test
    @DisplayName("color를 입력하면 Pawn 객체가 정상적으로 생성된다.")
    void shouldSuccessGeneratingPawn() {

        Color black = Color.BLACK;
        Color white = Color.WHITE;

        Assertions.assertAll(
                () -> assertDoesNotThrow(() -> new Pawn(black)),
                () -> assertDoesNotThrow(() -> new Pawn(white))
        );
    }

    @Test
    @DisplayName("화이트 폰의 현재 위치와 목표 위치를 입력할 때 북쪽 방향이 반환된다.")
    void shouldSucceedToNorthDirectionOfWhitePawn() {
        Position sourcePosition = Position.findPosition("a2");
        Position targetPosition = Position.findPosition("a3");

        assertThat(whitePawn.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.NORTH);
    }

    @Test
    @DisplayName("블랙 폰의 현재 위치와 목표 위치를 입력할 때 남쪽 방향이 반환된다.")
    void shouldSucceedToSouthDirectionOfBlackPawn() {
        Position sourcePosition = Position.findPosition("a7");
        Position targetPosition = Position.findPosition("a6");

        assertThat(blackPawn.findMovableDirection(sourcePosition, targetPosition)).isEqualTo(Direction.SOUTH);
    }

    @Test
    @DisplayName("화이트폰이 북쪽으로 이동할 수 없을 때 예외가 발생한다.")
    void shouldFailToFindNorthDirection() {
        Position sourcePosition = Position.findPosition("a2");
        Position targetPosition = Position.findPosition("b4");

        assertThatThrownBy(() -> whitePawn.findMovableDirection(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 북쪽(화이트폰) 또는 남쪽(블랙폰) 중 이동 가능한 방향이 없습니다.");
    }

    @Test
    @DisplayName("화이트폰이 북쪽으로 이동할 수 없을 때 예외가 발생한다.")
    void shouldFailToFindSouthDirection() {
        Position sourcePosition = Position.findPosition("a7");
        Position targetPosition = Position.findPosition("b5");

        assertThatThrownBy(() -> blackPawn.findMovableDirection(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 북쪽(화이트폰) 또는 남쪽(블랙폰) 중 이동 가능한 방향이 없습니다.");
    }

    @Test
    @DisplayName("이동 방향, 현재 위치, 목표 위치가 입력될 때 현재 위치에서 목표 위치까지의 단위 거리가 반환된다")
    void shouldSucceedToFindDistance() {
        Position sourcePosition1 = Position.findPosition("a2");
        Position targetPosition1 = Position.findPosition("a3");
        Position sourcePosition2 = Position.findPosition("a7");
        Position targetPosition2 = Position.findPosition("a6");
        Direction northDirection = Direction.NORTH;
        Direction southDirection = Direction.SOUTH;
        int expectedDistance = 1;

        assertAll(
                () -> assertThat(whitePawn.findDistance(northDirection, sourcePosition1, targetPosition1)).isEqualTo(expectedDistance),
                () -> assertThat(whitePawn.findDistance(southDirection, sourcePosition2, targetPosition2)).isEqualTo(expectedDistance)
        );
    }

    @Test
    @DisplayName("초기 위치에 존재하지 않은 폰이 2칸을 이동한다면 예외가 발생한다.")
    void shouldFailToFindDistanceWhenPawnNotInInitPositionTakesTwoSteps() {
        Position sourcePositionWhitePawn = Position.findPosition("a3");
        Position targetPositionWhitePawn = Position.findPosition("a5");
        Position sourcePositionBlackPawn = Position.findPosition("a6");
        Position targetPositionBlackPawn = Position.findPosition("a4");
        Direction northDirection = Direction.NORTH;
        Direction southDirection = Direction.SOUTH;

        assertAll(
                () -> assertThatThrownBy(() -> whitePawn.findDistance(northDirection, sourcePositionWhitePawn, targetPositionWhitePawn))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] Pawn은 한 칸만 이동할 수 있습니다."),
                () -> assertThatThrownBy(() -> blackPawn.findDistance(southDirection, sourcePositionBlackPawn, targetPositionBlackPawn))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] Pawn은 한 칸만 이동할 수 있습니다.")
        );
    }

    @Test
    @DisplayName("초기 위치에 존재하는 폰이 2칸을 이동한다면 예외가 발생하지 않는다.")
    void shouldSucceedToFindDistanceWhenPawnInInitPositionTakesTwoSteps() {
        Position sourcePositionWhitePawn = Position.findPosition("a2");
        Position targetPositionWhitePawn = Position.findPosition("a4");
        Position sourcePositionBlackPawn = Position.findPosition("a7");
        Position targetPositionBlackPawn = Position.findPosition("a5");
        Direction northDirection = Direction.NORTH;
        Direction southDirection = Direction.SOUTH;

        assertAll(
                () -> assertDoesNotThrow(() -> whitePawn.findDistance(northDirection, sourcePositionWhitePawn, targetPositionWhitePawn)),
                () -> assertDoesNotThrow(() -> blackPawn.findDistance(southDirection, sourcePositionBlackPawn, targetPositionBlackPawn))
        );
    }

    @Test
    @DisplayName("초기 위치에 존재하는 폰이 3칸을 이동한다면 예외가 발생한다.")
    void shouldFailToFindDistanceWhenPawnInInitPositionTakesThreeSteps() {
        Position sourcePositionWhitePawn = Position.findPosition("a2");
        Position targetPositionWhitePawn = Position.findPosition("a5");
        Position sourcePositionBlackPawn = Position.findPosition("a7");
        Position targetPositionBlackPawn = Position.findPosition("a4");
        Direction northDirection = Direction.NORTH;
        Direction southDirection = Direction.SOUTH;

        assertAll(
                () -> assertThatThrownBy(() -> whitePawn.findDistance(northDirection, sourcePositionWhitePawn, targetPositionWhitePawn))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 초기 상태의 폰은 최대 두칸까지 이동 가능합니다."),
                () -> assertThatThrownBy(() -> blackPawn.findDistance(southDirection, sourcePositionBlackPawn, targetPositionBlackPawn))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 초기 상태의 폰은 최대 두칸까지 이동 가능합니다.")
        );
    }


    @Test
    @DisplayName("남,북,동남,서남,동북,동서 방향이 입력될 때 폰의 이동 가능 방향 범위에 포함된다.")
    void shouldSucceedToMatchMovableDirection() {

        assertAll(
                () -> assertDoesNotThrow(() -> whitePawn.validateDirection(Direction.SOUTH)),
                () -> assertDoesNotThrow(() -> whitePawn.validateDirection(Direction.NORTH)),
                () -> assertDoesNotThrow(() -> whitePawn.validateDirection(Direction.SOUTH_EAST)),
                () -> assertDoesNotThrow(() -> whitePawn.validateDirection(Direction.SOUTH_WEST)),
                () -> assertDoesNotThrow(() -> whitePawn.validateDirection(Direction.NORTH_EAST)),
                () -> assertDoesNotThrow(() -> whitePawn.validateDirection(Direction.NORTH_WEST))
        );
    }

    @Test
    @DisplayName("동서남북이 아닌 방향이 입력될 때 예외가 발생한다.")
    void shouldFailToMatchMovableDirection() {

        assertAll(
                () -> assertThatThrownBy(() -> whitePawn.validateDirection(Direction.NORTH_EAST_EAST))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 해당 방향으로는 이동할 수 없습니다."),
                () -> assertThatThrownBy(() -> whitePawn.validateDirection(Direction.EAST))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 해당 방향으로는 이동할 수 없습니다."),
                () -> assertThatThrownBy(() -> whitePawn.validateDirection(Direction.SOUTH_EAST_EAST))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 해당 방향으로는 이동할 수 없습니다."),
                () -> assertThatThrownBy(() -> whitePawn.validateDirection(Direction.WEST))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 해당 방향으로는 이동할 수 없습니다.")
        );
    }

    @Test
    @DisplayName("화이트 폰이 초기화 위치에 존재할 때 true를 반환한다.")
    void shouldSucceedToCheckWhitePawnInInitialPositionTrue() {

        Position position1 = Position.findPosition("a2");
        Position position2 = Position.findPosition("d2");
        Position position3 = Position.findPosition("h2");

        assertAll(
                () -> assertThat(whitePawn.isFirstMovement(position1)).isTrue(),
                () -> assertThat(whitePawn.isFirstMovement(position2)).isTrue(),
                () -> assertThat(whitePawn.isFirstMovement(position3)).isTrue()
        );
    }

    @Test
    @DisplayName("화이트 폰이 초기화 위치에 존재하지 않을 때 false를 반환한다.")
    void shouldSucceedToCheckWhitePawnInInitialPositionFalse() {

        Position position1 = Position.findPosition("a3");
        Position position2 = Position.findPosition("d5");
        Position position3 = Position.findPosition("h7");

        assertAll(
                () -> assertThat(whitePawn.isFirstMovement(position1)).isFalse(),
                () -> assertThat(whitePawn.isFirstMovement(position2)).isFalse(),
                () -> assertThat(whitePawn.isFirstMovement(position3)).isFalse()
        );
    }

    @Test
    @DisplayName("블랙 폰이 초기화 위치에 존재할 때 true를 반환한다.")
    void shouldSucceedToCheckBlackPawnInInitialPositionTrue() {

        Position position1 = Position.findPosition("a7");
        Position position2 = Position.findPosition("d7");
        Position position3 = Position.findPosition("h7");

        assertAll(
                () -> assertThat(blackPawn.isFirstMovement(position1)).isTrue(),
                () -> assertThat(blackPawn.isFirstMovement(position2)).isTrue(),
                () -> assertThat(blackPawn.isFirstMovement(position3)).isTrue()
        );
    }

    @Test
    @DisplayName("블랙 폰이 초기화 위치에 존재하지 않을 때 false를 반환한다.")
    void shouldSucceedToCheckBlackPawnInInitialPositionFalse() {

        Position position1 = Position.findPosition("a3");
        Position position2 = Position.findPosition("d5");
        Position position3 = Position.findPosition("h8");

        assertAll(
                () -> assertThat(blackPawn.isFirstMovement(position1)).isFalse(),
                () -> assertThat(blackPawn.isFirstMovement(position2)).isFalse(),
                () -> assertThat(blackPawn.isFirstMovement(position3)).isFalse()
        );
    }

    @Test
    @DisplayName("화이트폰이 북쪽으로 이동할 수 있을 경우 true를 반환한다.")
    void shouldSucceedWhitePawnMoveToNorth() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a2");
        Direction direction = Direction.NORTH;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whitePawn.isMovable(movement, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("화이트폰이 북동쪽으로 이동할 수 없을 경우 false를 반환한다.")
    void shouldFailWhitePawnMoveToNorthEast() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a2");
        Direction direction = Direction.NORTH_EAST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whitePawn.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("화이트폰이 북서쪽으로 이동할 수 없을 경우 false를 반환한다.")
    void shouldFailWhitePawnMoveToNorthWest() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("b2");
        Direction direction = Direction.NORTH_WEST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(whitePawn.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("블랙폰이 남쪽으로 이동할 수 있을 경우 true를 반환한다.")
    void shouldSucceedBlackPawnMoveToSouth() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a7");
        Direction direction = Direction.SOUTH;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackPawn.isMovable(movement, chessBoard)).isTrue();
    }

    @Test
    @DisplayName("블랙폰이 남동쪽으로 이동할 수 없을 경우 false를 반환한다.")
    void shouldFailBlackPawnMoveToSouthEast() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("a7");
        Direction direction = Direction.SOUTH_EAST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackPawn.isMovable(movement, chessBoard)).isFalse();
    }

    @Test
    @DisplayName("블랙폰이 남서쪽으로 이동할 수 없을 경우 false를 반환한다.")
    void shouldFailBlackPawnMoveToSouthWest() {
        ChessBoard chessBoard = ChessBoard.GenerateChessBoard();
        Position sourcePosition = Position.findPosition("h7");
        Direction direction = Direction.SOUTH_WEST;
        int distance = 1;
        Movement movement = new Movement(sourcePosition, direction, distance);

        assertThat(blackPawn.isMovable(movement, chessBoard)).isFalse();
    }
}