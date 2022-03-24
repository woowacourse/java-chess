package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @DisplayName("체스판 초기화 시 모든 기물이 제자리에 생성되었는지 확인")
    @Test
    void initializeBoard() {
        Board board = new Board();
        board.initialize();

        List<List<String>> signatures = List.of(
                List.of("R", "N", "B", "Q", "K", "B", "N", "R"),
                List.of("P", "P", "P", "P", "P", "P", "P", "P"),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of("p", "p", "p", "p", "p", "p", "p", "p"),
                List.of("r", "n", "b", "q", "k", "b", "n", "r")
        );

        for (int i = 0; i < 8; i++) {
            assertThat(board.getRank(7 - i).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.toList())
            ).isEqualTo(signatures.get(i));
        }
    }

    @DisplayName("상대방 말을 선택할 시 예외가 발생한다.")
    @Test
    void moveWrongPiece_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("b8"), new Position("a6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("본인의 기물이 아닙니다.");
    }

    @DisplayName("대각선 직선 이동 시 중간에 기물이 존재한다면 이동할 수 없다.")
    @Test
    void diagonalStraightMove_Obstacle_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("c1"), new Position("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재합니다.");
    }

    @DisplayName("대각선으로 이동하려는 위치에 아군 말이 존재하면 예외가 발생한다.")
    @Test
    void diagonalStraightMove_ObstacleAlly_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("c1"), new Position("b2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @DisplayName("세로로 직선 이동 시 중간에 기물이 존재한다면 이동할 수 없다.")
    @Test
    void verticalStraightMove_Obstacle_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("a1"), new Position("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재합니다.");
    }

    @DisplayName("세로로 이동하려는 위치에 아군 말이 존재하면 예외가 발생한다.")
    @Test
    void verticalStraightMove_ObstacleAlly_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("a1"), new Position("a2")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }

    @DisplayName("가로로 직선 이동 시 중간에 기물이 존재한다면 이동할 수 없다.")
    @Test
    void horizontalStraightMove_Obstacle_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("a1"), new Position("c1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 존재합니다.");
    }

    @DisplayName("가로로 이동하려는 위치에 아군 말이 존재하면 예외가 발생한다.")
    @Test
    void horizontalStraightMove_ObstacleAlly_Fails() {
        Board board = new Board();
        board.initialize();

        assertThatThrownBy(() -> board.move(new Position("a1"), new Position("b1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동이 불가능한 위치입니다.");
    }
}
