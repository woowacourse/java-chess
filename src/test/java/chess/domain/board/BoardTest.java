package chess.domain.board;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.Player.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.exception.InvalidMovementException;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.GamePiece;

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
}