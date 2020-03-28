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
        Board board = Board.createEmpty().initialize();
        String map = board.getBoard()
                .values()
                .stream()
                .map(GamePiece::getName)
                .collect(Collectors.joining(""));
        assertThat(map).isEqualTo("RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr");
    }

    @ParameterizedTest
    @DisplayName("기물 이동")
    @MethodSource("createMovement")
    void move(GamePiece piece, Position source, Position target, int turn) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(source, piece);
        Board board = Board.from(map, new Status(turn, StatusType.PROCESSING));
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
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(Position.from("d5"), GamePiece.of(PAWN, BLACK));
        Board board = Board.from(map, new Status(1, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("d6"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("source 기물이 없는 경우")
    void moveWithEmptySource() {
        assertThatThrownBy(() -> {
            Board.createEmpty().initialize().move(Position.from("d3"), Position.from("d5"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n기물이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @DisplayName("자기턴이 아닌 때 움직이려고 하는 경우")
    @MethodSource("createBoardAndTurn")
    void moveWhenInvalidTurn(int turn, GamePiece gamePiece) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(Position.from("d5"), gamePiece);
        Board board = Board.from(map, new Status(turn, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("g8"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n해당 플레이어의 턴이 아닙니다.");
    }

    static Stream<Arguments> createBoardAndTurn() {
        return Stream.of(
                Arguments.of(1, GamePiece.of(ROOK, WHITE)),
                Arguments.of(0, GamePiece.of(ROOK, BLACK))
        );
    }

    @ParameterizedTest
    @DisplayName("경로에 기물이 있는 경우")
    @MethodSource("createPathWithObstacle")
    void moveWithObstacle(GamePiece gamePiece, Position source, Position target) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(source, gamePiece);
        map.put(Position.from("e3"), GamePiece.of(BISHOP, BLACK));
        Board board = Board.from(map, new Status(0, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move(source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n경로에 기물이 존재합니다.");
    }

    static Stream<Arguments> createPathWithObstacle() {
        return Stream.of(
                Arguments.of(GamePiece.of(PAWN, WHITE), Position.from("e2"), Position.from("e4")),
                Arguments.of(GamePiece.of(BISHOP, WHITE), Position.from("d2"), Position.from("f4")),
                Arguments.of(GamePiece.of(ROOK, WHITE), Position.from("e2"), Position.from("e5")),
                Arguments.of(GamePiece.of(QUEEN, WHITE), Position.from("e2"), Position.from("e4"))
        );
    }

    @Test
    @DisplayName("초기 board score 계산")
    void calculateScore() {
        Board board = Board.createEmpty().initialize();
        Map<Player, Score> scores = board.calculateScore();
        Map<Player, Score> expected = new HashMap<>();
        expected.put(BLACK, Score.from(38));
        expected.put(WHITE, Score.from(38));

        assertThat(scores).isEqualTo(expected);
    }

    @Test
    @DisplayName("같은 player, 같은 column의 pawn이 여러개 있는 경우 score 계산")
    void calculateScoreWhiteSameColumnPawn() {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(Position.from("d5"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("d6"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("f3"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("f4"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("f6"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("h3"), GamePiece.of(PAWN, WHITE));
        Board board = Board.from(map, new Status(0, StatusType.PROCESSING));

        assertThat(board.calculateScore().get(WHITE)).isEqualTo(Score.from(3.5));
    }

    @ParameterizedTest
    @DisplayName("Board가 Finish 여부 확인")
    @MethodSource("createFinish")
    void isBoardFinished(Position source, Position target, boolean expected) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(Position.from("c5"), GamePiece.of(KING, WHITE));
        map.put(Position.from("d6"), GamePiece.of(PAWN, BLACK));
        Board board = Board.from(map, new Status(1, StatusType.PROCESSING));
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