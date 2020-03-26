package chess.domain.board;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.GamePiece;
import chess.domain.player.Player;
import chess.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.Player.BLACK;
import static chess.domain.player.Player.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("보드 생성")
    void create() {
        Board board = Board.createInitial();
        String map = board.getBoard()
                .values()
                .stream()
                .map(GamePiece::getName)
                .collect(Collectors.joining(""));
        assertThat(map).isEqualTo("RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr");
    }

    // TODO: 2020/03/25 테스트 케이스 추가

    @ParameterizedTest
    @DisplayName("기물 이동")
    @MethodSource("createMovement")
    void move(GamePiece piece, Position source, Position target, int turn) {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(source, piece);
        Board board = Board.from(map, turn);
        board = board.move(source, target);

        assertThat(board.getBoard().get(source)).isEqualTo(GamePiece.EMPTY);
        assertThat(board.getBoard().get(target)).isEqualTo(piece);
    }

    static Stream<Arguments> createMovement() {
        return Stream.of(
                Arguments.of(GamePiece.of(ROOK, WHITE), Position.from("d3"), Position.from("f3"), 0),
                Arguments.of(GamePiece.of(PAWN, WHITE), Position.from("d3"), Position.from("d4"), 0),
                Arguments.of(GamePiece.of(PAWN, BLACK), Position.from("d3"), Position.from("d2"), 1),
                Arguments.of(GamePiece.of(ROOK, BLACK), Position.from("d3"), Position.from("f3"), 1)
        );
    }

    @Test
    @DisplayName("Black Pawn이 위로 올라갈 경우")
    void moveWithImpossiblePawnMovement() {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(Position.from("d5"), GamePiece.of(PAWN, BLACK));
        Board board = Board.from(map, 1);

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("d6"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("source 기물이 없는 경우")
    void moveWithEmptySource() {
        assertThatThrownBy(() -> {
            Board.EMPTY.move(Position.from("d3"), Position.from("d5"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("자기턴이 아닌 때 움직이려고 하는 경우")
    @MethodSource("createBoardAndTurn")
    void moveWhenInvalidTurn(int turn, GamePiece gamePiece) {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(Position.from("d5"), gamePiece);
        Board board = Board.from(map, turn);

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("g8"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    static Stream<Arguments> createBoardAndTurn() {
        return Stream.of(
                Arguments.of(1, GamePiece.of(ROOK, WHITE)),
                Arguments.of(0, GamePiece.of(ROOK, BLACK))
        );
    }

    @Test
    @DisplayName("경로에 기물이 있는 경우")
    void moveWithObstacle() {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(Position.from("d5"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("d6"), GamePiece.of(BISHOP, BLACK));
        Board board = Board.from(map, 0);

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("d6"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("초기 board score 계산")
    void calculateScore() {
        Board board = Board.createInitial();
        Map<Player, Score> scores = board.calculateScore();
        Map<Player, Score> expected = new HashMap<>();
        expected.put(BLACK, Score.from(38));
        expected.put(WHITE, Score.from(38));

        assertThat(scores).isEqualTo(expected);
    }

    @Test
    @DisplayName("같은 팀, 같은 화일의 pawn이 여러개 있는 경우 score 계산")
    void calculateScoreWhiteSameFilePawn() {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(Position.from("d5"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("d6"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("f3"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("f4"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("f6"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("h3"), GamePiece.of(PAWN, WHITE));
        Board board = Board.from(map, 0);

        assertThat(board.calculateScore().get(WHITE)).isEqualTo(Score.from(3.5));
    }

    @ParameterizedTest
    @DisplayName("Board가 Finish 여부 확인")
    @MethodSource("createFinish")
    void isBoardFinished(Position source, Position target, boolean expected) {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(Position.from("c5"), GamePiece.of(KING, WHITE));
        map.put(Position.from("d6"), GamePiece.of(PAWN, BLACK));
        Board board = Board.from(map, 1);
        board = board.move(source, target);

        assertThat(board.isNotFinished()).isEqualTo(expected);
    }

    static Stream<Arguments> createFinish() {
        return Stream.of(
                Arguments.of(Position.from("d6"), Position.from("d5"), true),
                Arguments.of(Position.from("d6"), Position.from("c5"), false)
        );
    }
}