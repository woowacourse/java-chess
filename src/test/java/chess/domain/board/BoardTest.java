package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.getInitialPieces());
    }

    @Test
    @DisplayName("선택한 Position에 말이 없으면, 예외를 발생시킨다")
    void notExistPieceInPosition() {
        Position fromPosition = Position.valueOf(File.A, Rank.THREE);
        Position toPosition = Position.valueOf(File.A, Rank.FOUR);
        assertThatThrownBy(() -> board.movePiece(fromPosition, toPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 입력한 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("말을 이동시키고, 이동한 위치가 보드에 존재하면 True를 반환")
    void movePieceAndCheckExistPosition() {
        Position fromPosition = Position.valueOf(File.A, Rank.TWO);
        Position toPosition = Position.valueOf(File.A, Rank.THREE);

        board.movePiece(fromPosition, toPosition);

        assertThat(board.getValue().containsKey(toPosition)).isTrue();
    }

    @Test
    @DisplayName("말을 이동시키고, 이동하기 전 위치가 보드에 존재하지 않는지 확인")
    void movePieceAndCheckNotExistPosition() {
        Position fromPosition = Position.valueOf(File.A, Rank.TWO);
        Position toPosition = Position.valueOf(File.A, Rank.THREE);

        board.movePiece(fromPosition, toPosition);

        assertThat(board.getValue().containsKey(fromPosition)).isFalse();
    }

    @Test
    @DisplayName("말이 움직일 수 없는 위치일 때 예외를 발생시킨다")
    void nonMovablePositionException() {
        Position fromPosition = Position.valueOf(File.A, Rank.TWO);
        Position toPosition = Position.valueOf(File.B, Rank.THREE);

        assertThatThrownBy(() -> board.movePiece(fromPosition, toPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 해당 위치는 말이 움직일 수 없습니다.");
    }
}
