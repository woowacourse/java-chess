package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.Player.BLACK;
import static chess.domain.player.Player.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class GamePieceTest {

    @ParameterizedTest
    @DisplayName("game piece 생성")
    @MethodSource("createGamePiece")
    void name() {
        GamePiece gamePiece = GamePiece.of(BISHOP, BLACK);

        assertThat(gamePiece.getName()).isEqualTo("B");
    }

    static Stream<Arguments> createGamePiece() {
        return Stream.of(
                Arguments.of(BISHOP, BLACK, "B"),
                Arguments.of(BISHOP, WHITE, "b"),
                Arguments.of(PAWN, BLACK, "P"),
                Arguments.of(QUEEN, WHITE, "q")
        );
    }

    @Test
    @DisplayName("플레이어별 game piece 생성")
    void list() {
        assertThat(GamePiece.list()).hasSize(12);
    }
}