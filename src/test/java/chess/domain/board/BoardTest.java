package chess.domain.board;

import static chess.domain.player.PlayerColor.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

class BoardTest {

    @Test
    @DisplayName("Processing 상태 전에 move를 실행")
    void moveBeforeProcessing() {
        Board board = BoardFactory.createEmptyBoard();
        assertThatThrownBy(() -> {
            board.move("d4", "d5");
        }).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("먼저 게임을 실행해야합니다.");
    }

    @ParameterizedTest
    @DisplayName("기물 이동")
    @MethodSource("createMovement")
    void move(GamePiece piece, String source, String target, int turn) {
        Map<Position, GamePiece> map = new HashMap<>(BoardFactory.createEmptyBoard().getBoard());
        map.put(Position.from(source), piece);
        Board board = Board.from(map, new Status(turn, StatusType.PROCESSING));
        board = board.move(source, target);

        assertThat(board.getBoard().get(Position.from(source))).isEqualTo(EmptyPiece.getInstance());
        assertThat(board.getBoard().get(Position.from(target))).isEqualTo(piece);
    }

    static Stream<Arguments> createMovement() {
        return Stream.of(
                Arguments.of(new Rook(WHITE), "d3", "f3", 0),
                Arguments.of(new Rook(BLACK), "d3", "f3", 1),
                Arguments.of(new Pawn(WHITE), "d3", "d4", 0),
                Arguments.of(new Pawn(BLACK), "d3", "d2", 1)
        );
    }

    @Test
    @DisplayName("Black Pawn이 위로 올라갈 경우")
    void moveWithImpossiblePawnMovement() {
        Map<Position, GamePiece> map = new HashMap<>(BoardFactory.createEmptyBoard().getBoard());
        map.put(Position.from("d5"), new Pawn(BLACK));
        Board board = Board.from(map, new Status(1, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move("d5", "d6");
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("source 기물이 없는 경우")
    void moveWithEmptySource() {
        assertThatThrownBy(() -> {
            BoardFactory.createInitialBoard().move("d3", "d5");
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n기물이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @DisplayName("자기턴이 아닌 때 움직이려고 하는 경우")
    @MethodSource("createBoardAndTurn")
    void moveWhenInvalidTurn(int turn, GamePiece gamePiece) {
        Map<Position, GamePiece> map = new HashMap<>(BoardFactory.createEmptyBoard().getBoard());
        map.put(Position.from("d5"), gamePiece);
        Board board = Board.from(map, new Status(turn, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move("d5", "g8");
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n해당 플레이어의 턴이 아닙니다.");
    }

    static Stream<Arguments> createBoardAndTurn() {
        return Stream.of(
                Arguments.of(1, new Rook(WHITE)),
                Arguments.of(0, new Rook(BLACK))
        );
    }

    @ParameterizedTest
    @DisplayName("경로에 기물이 있는 경우")
    @MethodSource("createPathWithObstacle")
    void moveWithObstacle(GamePiece gamePiece, String source, String target) {
        Map<Position, GamePiece> map = new HashMap<>(BoardFactory.createEmptyBoard().getBoard());
        map.put(Position.from(source), gamePiece);
        map.put(Position.from("e3"), new Bishop(BLACK));
        Board board = Board.from(map, new Status(0, StatusType.PROCESSING));

        assertThatThrownBy(() -> {
            board.move(source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n경로에 기물이 존재합니다.");
    }

    static Stream<Arguments> createPathWithObstacle() {
        return Stream.of(
                Arguments.of(new Pawn(WHITE), "e2", "e4"),
                Arguments.of(new Bishop(WHITE), "d2", "f4"),
                Arguments.of(new Rook(WHITE), "e2", "e5"),
                Arguments.of(new Queen(WHITE), "e2", "e4")
        );
    }

    @ParameterizedTest
    @DisplayName("Board가 Finish 여부 확인")
    @MethodSource("createFinish")
    void isBoardFinished(String source, String target, boolean expected) {
        Map<Position, GamePiece> map = new HashMap<>(BoardFactory.createEmptyBoard().getBoard());
        map.put(Position.from("c5"), new King(WHITE));
        map.put(Position.from("d6"), new Pawn(BLACK));
        Board board = Board.from(map, new Status(1, StatusType.PROCESSING));
        board = board.move(source, target);

        assertThat(board.isNotFinished()).isEqualTo(expected);
    }

    static Stream<Arguments> createFinish() {
        return Stream.of(
                Arguments.of("d6", "d5", true),
                Arguments.of("d6", "c5", false)
        );
    }
}