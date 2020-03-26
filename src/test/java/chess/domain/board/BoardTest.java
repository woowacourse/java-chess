package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.exception.InvalidTurnException;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Position;
import chess.domain.piece.exception.NotMovableException;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initialize();
    }

    @Test
    @DisplayName("체스판이 정상적으로 생성된 경우")
    void constructor() {
        assertThat(new Board()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("체스판 초기화")
    void initialize() {
        assertThat(board.getRanks().size()).isEqualTo(8);
    }

    @Test
    @DisplayName("체스판 말 위치 이동")
    void move() {
        Position source = Position.from("a2");
        Position target = Position.from("a3");
        board.move(source, target, Color.WHITE);
        assertThat(board.getRanks().get(target.getY()).findPiece(target.getX())).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("체스판 말 이동 경로에 장애물이 존재하는 경우 예외 발생")
    void move_invalid_path_by_obstacle() {
        Position source = Position.from("d1");
        Position target = Position.from("d7");
        assertThatExceptionOfType(NotMovableException.class).isThrownBy(
            () -> board.move(source, target, Color.WHITE));
    }

    @Test
    void move_invalid_turn() {
        Position source = Position.from("a2");
        Position target = Position.from("a3");
        assertThatExceptionOfType(InvalidTurnException.class).isThrownBy(
            () -> board.move(source, target, Color.BLACK));
    }

    @Test
    @DisplayName("체스판 초기화 상태의 점수 계산")
    void calculateScore() {
        assertThat(board.calculateScore(Color.WHITE)).isEqualTo(38);
        assertThat(board.calculateScore(Color.BLACK)).isEqualTo(38);
    }
}
