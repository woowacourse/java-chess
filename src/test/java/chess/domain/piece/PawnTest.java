package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.location.Direction;
import chess.domain.location.Location;
import chess.domain.location.LocationDiff;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {
    @Test
    @DisplayName("White 폰에게 허용하지 않는 이동 명령을 했을 경우 false 반환")
    void whitePawnNoMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovableDirection(Direction.of(-1, -1))).isFalse();
    }

    @Test
    @DisplayName("Black 폰에게 허용하지 않는 이동 명령을 했을 경우 false 반환")
    void blackPawnNoMove() {
        Pawn pawn = new Pawn(Team.BLACK);
        assertThat(pawn.isMovableDirection(Direction.of(1, 1))).isFalse();
    }

    @Test
    @DisplayName("White 폰에게 허용된 이동 명령을 했을 경우 true 반환 ")
    void whitePawnMove() {
        Pawn pawn = new Pawn(Team.WHITE);
        assertThat(pawn.isMovableDirection(Direction.of(0, 1))).isTrue();
    }

    @ParameterizedTest
    @DisplayName("White 폰이 이동할 수 있는 거리인지 확인")
    @MethodSource("distanceParameter")
    void whitePawnMovableDistance(int xDiff, int yDiff, boolean result) {
        Pawn pawn = new Pawn(Team.WHITE);
        LocationDiff locationDiff = new LocationDiff(xDiff, yDiff);
        assertThat(pawn.isMovableDistance(locationDiff)).isEqualTo(result);
    }

    public static Stream<Arguments> distanceParameter() {
        return Stream.of(
                Arguments.arguments(0, 1, true),
                Arguments.arguments(0, 2, true),
                Arguments.arguments(1, 1, true),
                Arguments.arguments(0, 3, false),
                Arguments.arguments(2, 2, false)
        );
    }

    @Test
    @DisplayName("White 폰앞에 기물이 있을 경우 전진 못함")
    void whitePawnCantMovableForward() {
        ChessGame chessGame = new ChessGame();

        chessGame.start();
        chessGame.move(Location.of("a2"), Location.of("a4"));
        chessGame.move(Location.of("a7"), Location.of( "a5"));
        assertThatThrownBy(() -> chessGame.move(Location.of("a4"), Location.of( "a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 앞에 기물이 존재하면 직진할 수 없습니다.");
    }

    @Test
    @DisplayName("White 폰 대각선 앞에 기물이 없을 경우 전진 못함")
    void whitePawnCantMovableForwardWhenEmptyPiece() {
        ChessGame chessGame = new ChessGame();

        chessGame.start();
        assertThatThrownBy(() -> chessGame.move(Location.of("a2"), Location.of( "b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 대각선에 상대 기물이 있을때만 움직일 수 있습니다.");
    }

    @Test
    @DisplayName("White 폰 대각선 앞에 기물이 있을 경우 전진 못함")
    void whitePawnMovableForwardWhenEmptyPiece() {
        ChessGame chessGame = new ChessGame();

        chessGame.start();
        assertThatThrownBy(() -> chessGame.move(Location.of("a2"), Location.of( "b3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 폰은 대각선에 상대 기물이 있을때만 움직일 수 있습니다.");
    }
}