package chess.domain.board;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.Player.*;
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
import chess.domain.piece.GamePiece;

class BoardTest {

    static Stream<Arguments> createMovement() {
        return Stream.of(
                Arguments.of(GamePiece.of(ROOK, WHITE), Position.from("d3"), Position.from("f3"))
        );
    }

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
    void move(GamePiece piece, Position source, Position target) {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(source, piece);
        Board board = Board.from(map);
        board = board.move(source, target);

        assertThat(board.getBoard().get(source)).isEqualTo(GamePiece.EMPTY);
        assertThat(board.getBoard().get(target)).isEqualTo(piece);
    }

    @Test
    @DisplayName("source 기물이 없는 경우")
    void moveWithEmptySource() {
        assertThatThrownBy(() -> {
            Board.EMPTY.move(Position.from("d3"), Position.from("d5"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    @Test
    @DisplayName("경로에 기물이 있는 경우")
    void moveWithObstacle() {
        Map<Position, GamePiece> map = new HashMap<>(Board.EMPTY.getBoard());
        map.put(Position.from("d5"), GamePiece.of(PAWN, WHITE));
        map.put(Position.from("d6"), GamePiece.of(BISHOP, BLACK));
        Board board = Board.from(map);

        assertThatThrownBy(() -> {
            board.move(Position.from("d5"), Position.from("d6"));
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }
}