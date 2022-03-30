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
        Board board = Board.getBasicInstance();

        assertThat(board.findPieceBy(position).get().isSameColor(color)).isTrue();
        assertThat(board.findPieceBy(position).get().isSameType(type)).isTrue();
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

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a3"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 직진으로 기물을 잡을 수 없습니다");
    }

    @DisplayName("폰은 한 칸 앞에 적은 잡을 수 없다")
    @Test
    void pawnCannotCatchFront() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a2"), new Pawn(Color.WHITE));
        value.put(Position.of("a3"), new Pawn(Color.BLACK));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a3"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 직진으로 기물을 잡을 수 없습니다");
    }

    @DisplayName("화이트 폰이 두칸 전진하는데 중간에 말이 있으면 에러가 발생한다.")
    @Test
    void whitePawnMoveCheckObstacleInPathTwo() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a2"), new Pawn(Color.WHITE));
        value.put(Position.of("a3"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("a2"), Position.of("a4"), Color.WHITE))
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

        assertThatThrownBy(() -> board.move(Position.of("a7"), Position.of("a5"), Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("폰이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("pawnMoveTestSet")
    void whitePawnOneMove(Position source, Position destination, Color color) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new Pawn(color);
        value.put(source, piece);

        Board board = new Board(value);
        board.move(source, destination, color);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> pawnMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a3"), Color.WHITE),
                Arguments.of(Position.of("a7"), Position.of("a6"), Color.BLACK),
                Arguments.of(Position.of("a2"), Position.of("a4"), Color.WHITE),
                Arguments.of(Position.of("a7"), Position.of("a5"), Color.BLACK)
        );
    }

    @DisplayName("나이트가 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("knightMoveTestSet")
    void knightMove(Position source, Position destination) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new Knight(Color.BLACK);
        value.put(source, piece);
        Board board = new Board(value);

        board.move(source, destination, Color.BLACK);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> knightMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("e7")),
                Arguments.of(Position.of("d5"), Position.of("e3")),
                Arguments.of(Position.of("d5"), Position.of("c7")),
                Arguments.of(Position.of("d5"), Position.of("c3")),
                Arguments.of(Position.of("d5"), Position.of("b6")),
                Arguments.of(Position.of("d5"), Position.of("b4")),
                Arguments.of(Position.of("d5"), Position.of("f6")),
                Arguments.of(Position.of("d5"), Position.of("f4"))
        );
    }

    @DisplayName("나이트가 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void knightCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new Knight(Color.WHITE));
        value.put(Position.of("e7"), new Knight(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("d5"), Position.of("e7"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("킹이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("kingMoveTestSet")
    void kingMove(Position source, Position destination) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new King(Color.BLACK);
        value.put(source, piece);
        Board board = new Board(value);

        board.move(source, destination, Color.BLACK);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> kingMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d3"), Position.of("d4")),
                Arguments.of(Position.of("d3"), Position.of("d2")),
                Arguments.of(Position.of("d3"), Position.of("e3")),
                Arguments.of(Position.of("d3"), Position.of("c3")),
                Arguments.of(Position.of("d3"), Position.of("e4")),
                Arguments.of(Position.of("d3"), Position.of("e2")),
                Arguments.of(Position.of("d3"), Position.of("c4")),
                Arguments.of(Position.of("d3"), Position.of("c2"))
        );
    }

    @DisplayName("킹은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void kingCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new King(Color.WHITE));
        value.put(Position.of("d6"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("d5"), Position.of("d6"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("룩이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("rookMoveTestSet")
    void rookMove(Position source, Position destination) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new Rook(Color.BLACK);
        value.put(source, piece);
        Board board = new Board(value);

        board.move(source, destination, Color.BLACK);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> rookMoveTestSet() {
        return Stream.of(
                // 동
                Arguments.of(Position.of("a3"), Position.of("b3")),
                Arguments.of(Position.of("a3"), Position.of("c3")),
                Arguments.of(Position.of("a3"), Position.of("d3")),
                Arguments.of(Position.of("a3"), Position.of("e3")),
                Arguments.of(Position.of("a3"), Position.of("f3")),
                Arguments.of(Position.of("a3"), Position.of("g3")),
                Arguments.of(Position.of("a3"), Position.of("h3")),
                // 서
                Arguments.of(Position.of("h3"), Position.of("a3")),
                Arguments.of(Position.of("h3"), Position.of("b3")),
                Arguments.of(Position.of("h3"), Position.of("c3")),
                Arguments.of(Position.of("h3"), Position.of("d3")),
                Arguments.of(Position.of("h3"), Position.of("e3")),
                Arguments.of(Position.of("h3"), Position.of("f3")),
                Arguments.of(Position.of("h3"), Position.of("g3")),
                // 남
                Arguments.of(Position.of("a8"), Position.of("a7")),
                Arguments.of(Position.of("a8"), Position.of("a6")),
                Arguments.of(Position.of("a8"), Position.of("a5")),
                Arguments.of(Position.of("a8"), Position.of("a4")),
                Arguments.of(Position.of("a8"), Position.of("a3")),
                Arguments.of(Position.of("a8"), Position.of("a2")),
                Arguments.of(Position.of("a8"), Position.of("a1")),
                // 북
                Arguments.of(Position.of("a1"), Position.of("a8")),
                Arguments.of(Position.of("a1"), Position.of("a7")),
                Arguments.of(Position.of("a1"), Position.of("a6")),
                Arguments.of(Position.of("a1"), Position.of("a5")),
                Arguments.of(Position.of("a1"), Position.of("a4")),
                Arguments.of(Position.of("a1"), Position.of("a3")),
                Arguments.of(Position.of("a1"), Position.of("a2"))
        );
    }

    @DisplayName("룩은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void rookCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new Rook(Color.WHITE));
        value.put(Position.of("d8"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("d5"), Position.of("d8"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("룩은 이동경로에 다른 기물이 있는 경우 예외를 던진다")
    @Test
    void rookCheckObstacleInPath() {

        Piece rook = new Rook(Color.WHITE);
        Piece obstacle = new Pawn(Color.BLACK);
        Map<Position, Piece> value = new HashMap<>();
        Position source = Position.of("d3");
        value.put(source, rook);
        value.put(Position.of("f3"), obstacle);
        Board board = new Board(value);

        Position destination = Position.of("h3");

        assertThatThrownBy(() -> board.move(source, destination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 기물");
    }

    @DisplayName("비숍이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("bishopMoveTestSet")
    void bishopMove(Position source, Position destination) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new Bishop(Color.BLACK);
        value.put(source, piece);
        Board board = new Board(value);

        board.move(source, destination, Color.BLACK);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> bishopMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("e6")),
                Arguments.of(Position.of("d5"), Position.of("e4")),
                Arguments.of(Position.of("d5"), Position.of("c6")),
                Arguments.of(Position.of("d5"), Position.of("c4"))
        );
    }

    @DisplayName("비숍은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void bishopCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new Bishop(Color.WHITE));
        value.put(Position.of("g8"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("d5"), Position.of("g8"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("비숍은 이동경로에 다른 기물이 있는 경우 예외를 던진다")
    @Test
    void bishopCheckObstacleInPath() {

        Piece bishop = new Bishop(Color.WHITE);
        Piece obstacle = new Pawn(Color.BLACK);
        Map<Position, Piece> value = new HashMap<>();
        Position source = Position.of("d3");
        value.put(source, bishop);
        value.put(Position.of("f5"), obstacle);
        Board board = new Board(value);

        Position destination = Position.of("g6");

        assertThatThrownBy(() -> board.move(source, destination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 기물");
    }

    @DisplayName("퀸이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName}")
    @MethodSource("queenMoveTestSet")
    void queenMove(Position source, Position destination) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = new Queen(Color.BLACK);
        value.put(source, piece);
        Board board = new Board(value);

        board.move(source, destination, Color.BLACK);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> queenMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.of("d5"), Position.of("e5")), // 동
                Arguments.of(Position.of("d5"), Position.of("h5")), // 동
                Arguments.of(Position.of("d5"), Position.of("c5")), // 서
                Arguments.of(Position.of("d5"), Position.of("a5")), // 서
                Arguments.of(Position.of("d5"), Position.of("d4")), // 남
                Arguments.of(Position.of("d5"), Position.of("d1")), // 남
                Arguments.of(Position.of("d5"), Position.of("d6")), // 북
                Arguments.of(Position.of("d5"), Position.of("d8")), // 북
                Arguments.of(Position.of("d5"), Position.of("e6")), // 북동
                Arguments.of(Position.of("d5"), Position.of("g8")), // 북동
                Arguments.of(Position.of("d5"), Position.of("c6")), // 북서
                Arguments.of(Position.of("d5"), Position.of("a8")), // 북서
                Arguments.of(Position.of("d5"), Position.of("e4")), // 남동
                Arguments.of(Position.of("d5"), Position.of("h1")), // 남동
                Arguments.of(Position.of("d5"), Position.of("c4")), // 남서
                Arguments.of(Position.of("d5"), Position.of("a2"))  // 남서
        );
    }

    @DisplayName("퀸은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void queenCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.of("d5"), new Queen(Color.WHITE));
        value.put(Position.of("g8"), new Pawn(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.of("d5"), Position.of("g8"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("퀸은 이동경로에 다른 기물이 있는 경우 예외를 던진다")
    @Test
    void queenCheckObstacleInPath() {

        Piece queen = new Queen(Color.WHITE);
        Piece obstacle = new Pawn(Color.BLACK);
        Map<Position, Piece> value = new HashMap<>();
        Position source = Position.of("d3");
        value.put(source, queen);
        value.put(Position.of("f5"), obstacle);
        Board board = new Board(value);

        Position destination = Position.of("g6");

        assertThatThrownBy(() -> board.move(source, destination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 기물");
    }

    @DisplayName("화이트 폰이 상대 기물을 잡는다")
    @Test
    void testWhitePawnCatch() {

        Piece pawn = new Pawn(Color.WHITE);
        Piece target = new Pawn(Color.BLACK);
        Position source = Position.of("a2");
        Position destination = Position.of("b3");

        Map<Position, Piece> value = new HashMap<>();
        value.put(source, pawn);
        value.put(destination, target);

        Board board = new Board(value);

        board.move(source, destination, Color.WHITE);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(pawn);
    }

    @DisplayName("블랙 폰이 상대 기물을 잡는다")
    @Test
    void testBlackPawnCatch() {

        Piece pawn = new Pawn(Color.BLACK);
        Piece target = new Pawn(Color.WHITE);
        Position source = Position.of("a7");
        Position destination = Position.of("b6");

        Map<Position, Piece> value = new HashMap<>();
        value.put(source, pawn);
        value.put(destination, target);

        Board board = new Board(value);

        board.move(source, destination, Color.BLACK);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(pawn);
    }

    @DisplayName("흰 말의 점수를 계산한다.")
    @Test
    void testCalculateWhiteScore() {
        Board board = Board.getBasicInstance();

        double score = board.calculateScore(Color.WHITE);

        assertThat(score).isEqualTo(38.0);
    }

    @DisplayName("같은 file에 여러 pawn이 있으면 0.5점으로 점수를 계산한다.")
    @Test
    void testCalculateScoreSameLinePawn() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.of("b2"), new Pawn(Color.WHITE));
        value.put(Position.of("b3"), new Pawn(Color.WHITE));
        value.put(Position.of("b4"), new Pawn(Color.WHITE));
        value.put(Position.of("a2"), new Pawn(Color.WHITE));

        Board board = new Board(value);

        double score = board.calculateScore(Color.WHITE);

        assertThat(score).isEqualTo(2.5);
    }

    @DisplayName("검은 말의 점수를 계산한다.")
    @Test
    void testCalculateBlackScore() {
        Board board = Board.getBasicInstance();

        double score = board.calculateScore(Color.BLACK);

        assertThat(score).isEqualTo(38.0);
    }

    @DisplayName("보드에 블랙 킹이 있으면 참을 반환한다.")
    @Test
    void testHasKing() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a7"), new King(Color.BLACK));
        Board board = new Board(value);

        assertThat(board.hasKing(Color.BLACK)).isTrue();
    }

    @DisplayName("보드에 블랙 킹이 없으면 거짓을 반환한다")
    @Test
    void testHasNotKing() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.of("a7"), new Pawn(Color.BLACK));
        Board board = new Board(value);

        assertThat(board.hasKing(Color.BLACK)).isFalse();
    }
}
