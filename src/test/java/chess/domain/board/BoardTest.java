package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {

    @DisplayName("기물을 각자의 자리에 생성한다.")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("boardInitTestSet")
    void test(Position position, Piece piece, Color color) {
        Board board = BasicChessBoardGenerator.generator();

        assertThat(board.findPieceBy(position).get().isSameColor(color)).isTrue();
        assertThat(board.findPieceBy(position).get()).isEqualTo(piece);
    }

    static Stream<Arguments> boardInitTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("a8"), new Rook(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("b8"), new Knight(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("c8"), new Bishop(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("d8"), new Queen(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("e8"), new King(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("f8"), new Bishop(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("g8"), new Knight(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("h8"), new Rook(Color.BLACK), Color.BLACK),

                Arguments.of(Position.valueOf("a7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("b7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("c7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("d7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("e7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("f7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("g7"), Pawn.of(Color.BLACK), Color.BLACK),
                Arguments.of(Position.valueOf("h7"), Pawn.of(Color.BLACK), Color.BLACK),

                Arguments.of(Position.valueOf("a1"), new Rook(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("b1"), new Knight(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("c1"), new Bishop(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("d1"), new Queen(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("e1"), new King(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("f1"), new Bishop(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("g1"), new Knight(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("h1"), new Rook(Color.WHITE), Color.WHITE),

                Arguments.of(Position.valueOf("a2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("b2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("c2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("d2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("e2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("f2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("g2"), Pawn.of(Color.WHITE), Color.WHITE),
                Arguments.of(Position.valueOf("h2"), Pawn.of(Color.WHITE), Color.WHITE)
        );
    }

    @DisplayName("폰이 한칸 전진하는데 도착 위치에 아군의 말이 있으면 에러가 발생한다.")
    @Test
    void pawnMoveCheckObstacleInPathOne() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a2"), Pawn.of(Color.WHITE));
        value.put(Position.valueOf("a3"), Pawn.of(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("a2"), Position.valueOf("a3"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("도착 위치에 아군이 있어 이동할 수 없습니다.");
    }

    @DisplayName("폰은 한 칸 앞에 적은 잡을 수 없다")
    @Test
    void pawnCannotCatchFront() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a2"), Pawn.of(Color.WHITE));
        value.put(Position.valueOf("a3"), Pawn.of(Color.BLACK));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("a2"), Position.valueOf("a3"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰은 직진으로 기물을 잡을 수 없습니다");
    }

    @DisplayName("화이트 폰이 두칸 전진하는데 중간에 말이 있으면 에러가 발생한다.")
    @Test
    void whitePawnMoveCheckObstacleInPathTwo() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a3"), Pawn.of(Color.WHITE));
        value.put(Position.valueOf("a2"), Pawn.of(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("a2"), Position.valueOf("a4"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("블랙 폰이 두칸 전진하는데 중간에 말이 있으면 에러가 발생한다.")
    @Test
    void blackPawnMoveCheckObstacleInPathTwo() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a7"), Pawn.of(Color.BLACK));
        value.put(Position.valueOf("a6"), Pawn.of(Color.BLACK));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("a7"), Position.valueOf("a5"), Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 있습니다.");
    }

    @DisplayName("폰이 성공적으로 이동한다")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("pawnMoveTestSet")
    void whitePawnOneMove(Position source, Position destination, Color color) {
        Map<Position, Piece> value = new HashMap<>();

        Piece piece = Pawn.of(color);
        value.put(source, piece);

        Board board = new Board(value);
        board.move(source, destination, color);

        assertThat(board.findPieceBy(source).isEmpty()).isTrue();
        assertThat(board.findPieceBy(destination).get()).isEqualTo(piece);
    }

    static Stream<Arguments> pawnMoveTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("a2"), Position.valueOf("a3"), Color.WHITE),
                Arguments.of(Position.valueOf("a7"), Position.valueOf("a6"), Color.BLACK),
                Arguments.of(Position.valueOf("a2"), Position.valueOf("a4"), Color.WHITE),
                Arguments.of(Position.valueOf("a7"), Position.valueOf("a5"), Color.BLACK)
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
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e7")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c7")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("b6")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("b4")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("f6")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("f4"))
        );
    }

    @DisplayName("나이트가 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void knightCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("d5"), new Knight(Color.WHITE));
        value.put(Position.valueOf("e7"), new Knight(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("d5"), Position.valueOf("e7"), Color.WHITE))
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
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d4")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d2")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e4")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e2")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c4")),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c2"))
        );
    }

    @DisplayName("킹은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void kingCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("d5"), new King(Color.WHITE));
        value.put(Position.valueOf("d6"), Pawn.of(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("d5"), Position.valueOf("d6"), Color.WHITE))
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
                Arguments.of(Position.valueOf("a3"), Position.valueOf("b3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("d3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("f3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("g3")),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("h3")),
                // 서
                Arguments.of(Position.valueOf("h3"), Position.valueOf("a3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("b3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("c3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("d3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("e3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("f3")),
                Arguments.of(Position.valueOf("h3"), Position.valueOf("g3")),
                // 남
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a7")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a6")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a5")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a4")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a3")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a2")),
                Arguments.of(Position.valueOf("a8"), Position.valueOf("a1")),
                // 북
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a8")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a7")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a6")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a5")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a4")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3")),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a2"))
        );
    }

    @DisplayName("룩은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void rookCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("d5"), new Rook(Color.WHITE));
        value.put(Position.valueOf("d8"), Pawn.of(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("d5"), Position.valueOf("d8"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("룩은 이동경로에 다른 기물이 있는 경우 예외를 던진다")
    @Test
    void rookCheckObstacleInPath() {

        Piece rook = new Rook(Color.WHITE);
        Piece obstacle = Pawn.of(Color.BLACK);
        Map<Position, Piece> value = new HashMap<>();
        Position source = Position.valueOf("d3");
        value.put(source, rook);
        value.put(Position.valueOf("f3"), obstacle);
        Board board = new Board(value);

        Position destination = Position.valueOf("h3");

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
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e6")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e4")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c6")),
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c4"))
        );
    }

    @DisplayName("비숍은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void bishopCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("d5"), new Bishop(Color.WHITE));
        value.put(Position.valueOf("g8"), Pawn.of(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("d5"), Position.valueOf("g8"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("비숍은 이동경로에 다른 기물이 있는 경우 예외를 던진다")
    @Test
    void bishopCheckObstacleInPath() {

        Piece bishop = new Bishop(Color.WHITE);
        Piece obstacle = Pawn.of(Color.BLACK);
        Map<Position, Piece> value = new HashMap<>();
        Position source = Position.valueOf("d3");
        value.put(source, bishop);
        value.put(Position.valueOf("f5"), obstacle);
        Board board = new Board(value);

        Position destination = Position.valueOf("g6");

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
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e5")), // 동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("h5")), // 동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c5")), // 서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("a5")), // 서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d4")), // 남
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d1")), // 남
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d6")), // 북
                Arguments.of(Position.valueOf("d5"), Position.valueOf("d8")), // 북
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e6")), // 북동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("g8")), // 북동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c6")), // 북서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("a8")), // 북서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("e4")), // 남동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("h1")), // 남동
                Arguments.of(Position.valueOf("d5"), Position.valueOf("c4")), // 남서
                Arguments.of(Position.valueOf("d5"), Position.valueOf("a2"))  // 남서
        );
    }

    @DisplayName("퀸은 이동할 위치에 아군이 있으면 이동할 수 없다.")
    @Test
    void queenCannotMove() {
        Map<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("d5"), new Queen(Color.WHITE));
        value.put(Position.valueOf("g8"), Pawn.of(Color.WHITE));
        Board board = new Board(value);

        assertThatThrownBy(() -> board.move(Position.valueOf("d5"), Position.valueOf("g8"), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군");
    }

    @DisplayName("퀸은 이동경로에 다른 기물이 있는 경우 예외를 던진다")
    @Test
    void queenCheckObstacleInPath() {

        Piece queen = new Queen(Color.WHITE);
        Piece obstacle = Pawn.of(Color.BLACK);
        Map<Position, Piece> value = new HashMap<>();
        Position source = Position.valueOf("d3");
        value.put(source, queen);
        value.put(Position.valueOf("f5"), obstacle);
        Board board = new Board(value);

        Position destination = Position.valueOf("g6");

        assertThatThrownBy(() -> board.move(source, destination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 기물");
    }

    @DisplayName("화이트 폰이 상대 기물을 잡는다")
    @Test
    void testWhitePawnCatch() {

        Piece pawn = Pawn.of(Color.WHITE);
        Piece target = Pawn.of(Color.BLACK);
        Position source = Position.valueOf("a2");
        Position destination = Position.valueOf("b3");

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

        Piece pawn = Pawn.of(Color.BLACK);
        Piece target = Pawn.of(Color.WHITE);
        Position source = Position.valueOf("a7");
        Position destination = Position.valueOf("b6");

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
        Board board = BasicChessBoardGenerator.generator();

        double score = board.calculateScore(Color.WHITE);

        assertThat(score).isEqualTo(38.0);
    }

    @DisplayName("같은 file에 여러 pawn이 있으면 0.5점으로 점수를 계산한다.")
    @Test
    void testCalculateScoreSameLinePawn() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("b2"), Pawn.of(Color.WHITE));
        value.put(Position.valueOf("b3"), Pawn.of(Color.WHITE));
        value.put(Position.valueOf("b4"), Pawn.of(Color.WHITE));
        value.put(Position.valueOf("a2"), Pawn.of(Color.WHITE));

        Board board = new Board(value);

        double score = board.calculateScore(Color.WHITE);

        assertThat(score).isEqualTo(2.5);
    }

    @DisplayName("검은 말의 점수를 계산한다.")
    @Test
    void testCalculateBlackScore() {
        Board board = BasicChessBoardGenerator.generator();

        double score = board.calculateScore(Color.BLACK);

        assertThat(score).isEqualTo(38.0);
    }

    @DisplayName("보드에 블랙 킹이 있으면 참을 반환한다.")
    @Test
    void testHasKing() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a7"), new King(Color.BLACK));
        Board board = new Board(value);

        assertThat(board.hasKing(Color.BLACK)).isTrue();
    }

    @DisplayName("보드에 블랙 킹이 없으면 거짓을 반환한다")
    @Test
    void testHasNotKing() {
        HashMap<Position, Piece> value = new HashMap<>();
        value.put(Position.valueOf("a7"), Pawn.of(Color.BLACK));
        Board board = new Board(value);

        assertThat(board.hasKing(Color.BLACK)).isFalse();
    }
}
