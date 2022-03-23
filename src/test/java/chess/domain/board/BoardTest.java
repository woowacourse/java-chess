package chess.domain.board;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @DisplayName("기물을 각자의 자리에 생성한다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("boardInitTestSet")
    void test(Position position, Class<? extends Piece> type, Color color) {
        Board board = Board.getInstance();
        boolean exist = board.exist(position, type, color);

        assertThat(exist).isTrue();
    }

    static Stream<Arguments> boardInitTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a8"), Rook.class, Color.BLACK),
                Arguments.of(Position.of("b8"), Knight.class, Color.BLACK),
                Arguments.of(Position.of("c8"), Bishop.class, Color.BLACK),
                Arguments.of(Position.of("d8"), Queen.class, Color.BLACK),
                Arguments.of(Position.of("e8"), King.class, Color.BLACK),
                Arguments.of(Position.of("f8"), Bishop.class, Color.BLACK),
                Arguments.of(Position.of("g8"), Knight.class, Color.BLACK),
                Arguments.of(Position.of("h8"), Rook.class, Color.BLACK),

                Arguments.of(Position.of("a7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("b7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("c7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("d7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("e7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("f7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("g7"), Pawn.class, Color.BLACK),
                Arguments.of(Position.of("h7"), Pawn.class, Color.BLACK),

                Arguments.of(Position.of("a1"), Rook.class, Color.WHITE),
                Arguments.of(Position.of("b1"), Knight.class, Color.WHITE),
                Arguments.of(Position.of("c1"), Bishop.class, Color.WHITE),
                Arguments.of(Position.of("d1"), Queen.class, Color.WHITE),
                Arguments.of(Position.of("e1"), King.class, Color.WHITE),
                Arguments.of(Position.of("f1"), Bishop.class, Color.WHITE),
                Arguments.of(Position.of("g1"), Knight.class, Color.WHITE),
                Arguments.of(Position.of("h1"), Rook.class, Color.WHITE),

                Arguments.of(Position.of("a2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("b2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("c2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("d2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("e2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("f2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("g2"), Pawn.class, Color.WHITE),
                Arguments.of(Position.of("h2"), Pawn.class, Color.WHITE)
        );
    }

    @DisplayName("폰이 한칸 전진하는데 도착 위치에 아군의 말이 있으면 에러가 발생한다.")
    @Test
    void pawnMoveCheckObstacleInPathOne() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a2"), new Pawn(Color.WHITE));
        value.put(Position.of("a3"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("도착 위치에 아군이 있어 이동할 수 없습니다.");
    }

    @DisplayName("화이트 폰이 두칸 전진하는데 중간에 말이 있으면 에러가 발생한다.")
    @Test
    void whitePawnMoveCheckObstacleInPathTwo() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a2"), new Pawn(Color.WHITE));
        value.put(Position.of("a3"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a4")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("블랙 폰이 두칸 전진하는데 중간에 말이 있으면 에러가 발생한다.")
    @Test
    void blackPawnMoveCheckObstacleInPathTwo() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a7"), new Pawn(Color.BLACK));
        value.put(Position.of("a6"), new Pawn(Color.BLACK));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("a7"), Position.of("a5")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("폰이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("pawnMoveTestSet")
    void whitePawnOneMove(Position src, Position dest, Color color) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new Pawn(color);
        value.put(src, piece);
        Board board = new Board(value);

        board.move(src, dest);

        assertThat(board.findPieceBy(src).isEmpty()).isTrue();
        assertThat(board.findPieceBy(dest).get()).isEqualTo(piece);
    }

    static Stream<Arguments> pawnMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a3"), Color.WHITE),
                Arguments.of(Position.of("a7"), Position.of("a6"), Color.BLACK),
                Arguments.of(Position.of("a2"), Position.of("a4"), Color.WHITE),
                Arguments.of(Position.of("a7"), Position.of("a5"), Color.BLACK)
        );
    }
}
