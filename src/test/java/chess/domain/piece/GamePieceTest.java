package chess.domain.piece;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.Player.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Position;

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

    @ParameterizedTest
    @DisplayName("GamePiece별 초기 위치")
    @MethodSource("createPositions")
    void getInitialPositions(GamePiece piece, List<Position> expected) {
        assertThat(piece.getInitialPositions()).isEqualTo(expected);
    }

    static Stream<Arguments> createPositions() {
        return Stream.of(
                Arguments.of(GamePiece.of(ROOK, BLACK), Arrays.asList(Position.from("a8"), Position.from("h8"))),
                Arguments.of(GamePiece.of(ROOK, WHITE), Arrays.asList(Position.from("a1"), Position.from("h1")))
        );
    }
}