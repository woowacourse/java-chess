package chess.domain.board;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @Test
    @DisplayName("보드 생성")
    void create() {
        Board board = Board.createEmpty().placeInitialPieces();
        String map = board.getBoard()
                .values()
                .stream()
                .map(GamePiece::getName)
                .collect(Collectors.joining(""));
        assertThat(map).isEqualTo("RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr");
    }

    @Test
    @DisplayName("Processing 상태 전에 move를 실행")
    void moveBeforeProcessing() {
        Board board = Board.createEmpty();
        assertThatThrownBy(() -> {
            board.move(Position.from("d4"), Position.from("d5"));
        }).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("먼저 게임을 실행해야합니다.");
    }

    @ParameterizedTest
    @DisplayName("기물 이동")
    @MethodSource("createMovement")
    void move(GamePiece piece, Position source, Position target, int turn) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(source, piece);
        Board board = Board.from(map, new Status(turn, StatusType.PROCESSING));
        board = board.move(source, target);

        assertThat(board.getBoard().get(source)).isEqualTo(EmptyPiece.getInstance());
        assertThat(board.getBoard().get(target)).isEqualTo(piece);
    }

    static Stream<Arguments> createMovement() {
        return Stream.of(
                Arguments.of(WHITE_ROOK.getGamePiece(), Position.from("d3"), Position.from("f3"), 0),
                Arguments.of(WHITE_PAWN.getGamePiece(), Position.from("d3"), Position.from("d4"), 0),
                Arguments.of(BLACK_PAWN.getGamePiece(), Position.from("d3"), Position.from("d2"), 1),
                Arguments.of(BLACK_ROOK.getGamePiece(), Position.from("d3"), Position.from("f3"), 1)
        );
    }

    @Test
    @DisplayName("Black Pawn이 위로 올라갈 경우")
    void moveWithImpossiblePawnMovement() {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(Position.from("d5"), BLACK_PAWN.getGamePiece());
        Board board = Board.from(map, new Status(1, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("d6"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("source 기물이 없는 경우")
    void moveWithEmptySource() {
        assertThatThrownBy(() -> {
            Board.createEmpty().placeInitialPieces().move(Position.from("d3"), Position.from("d5"));
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
                Arguments.of(1, WHITE_ROOK.getGamePiece()),
                Arguments.of(0, BLACK_ROOK.getGamePiece())
        );
    }

    @ParameterizedTest
    @DisplayName("경로에 기물이 있는 경우")
    @MethodSource("createPathWithObstacle")
    void moveWithObstacle(GamePiece gamePiece, Position source, Position target) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(source, gamePiece);
        map.put(Position.from("e3"), BLACK_BISHOP.getGamePiece());
        Board board = Board.from(map, new Status(0, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move(source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n경로에 기물이 존재합니다.");
    }

    static Stream<Arguments> createPathWithObstacle() {
        return Stream.of(
                Arguments.of(WHITE_PAWN.getGamePiece(), Position.from("e2"), Position.from("e4")),
                Arguments.of(WHITE_BISHOP.getGamePiece(), Position.from("d2"), Position.from("f4")),
                Arguments.of(WHITE_ROOK.getGamePiece(), Position.from("e2"), Position.from("e5")),
                Arguments.of(WHITE_QUEEN.getGamePiece(), Position.from("e2"), Position.from("e4"))
        );
    }

    @ParameterizedTest
    @DisplayName("Board가 Finish 여부 확인")
    @MethodSource("createFinish")
    void isBoardFinished(Position source, Position target, boolean expected) {
        Map<Position, GamePiece> map = new HashMap<>(Board.createEmpty().getBoard());
        map.put(Position.from("c5"), WHITE_KING.getGamePiece());
        map.put(Position.from("d6"), BLACK_PAWN.getGamePiece());
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