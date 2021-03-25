package chess.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardUtilTest {

    private static final char[][] TEST_BOARD = {
        {'R', 'N', 'B', 'Q', 'K', 'B', '.', '.'},
        {'.', '.', '.', '.', '.', '.', 'P', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', 'p', '.'},
        {'r', 'n', 'b', 'q', 'k', '.', '.', '.'}
    };

    @DisplayName("기물로 구성된 2차원 배열을 각 기물이 위치값을 가진 리스트로 변환할 수 있다. - 기물이 위치 하지 않을 경우 예외를 던진다.")
    @Test
    void convertToBoard_failure() {
        // given, when
        Board board = BoardUtil.convertToBoard(TEST_BOARD);

        // then
        assertThatThrownBy(() -> board.find(Location.of(1, 2)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기물로 구성된 2차원 배열을 각 기물이 위치값을 가진 리스트로 변환할 수 있다. - 화이트 ")
    @Test
    void convertToBoard_white() {
        // given, when
        Board board = BoardUtil.convertToBoard(TEST_BOARD);

        // then
        assertAll(
            () -> assertThat(board.find(Location.of(1, 1))).isEqualTo(Rook.of(Location.of(1, 1), Team.WHITE)),
            () -> assertThat(board.find(Location.of(2, 1))).isEqualTo(Knight.of(Location.of(2, 1), Team.WHITE)),
            () -> assertThat(board.find(Location.of(3, 1))).isEqualTo(Bishop.of(Location.of(3, 1), Team.WHITE)),
            () -> assertThat(board.find(Location.of(4, 1))).isEqualTo(Queen.of(Location.of(4, 1), Team.WHITE)),
            () -> assertThat(board.find(Location.of(5, 1))).isEqualTo(King.of(Location.of(5, 1), Team.WHITE)),
            () -> assertThat(board.find(Location.of(7, 2))).isEqualTo(Pawn.of(Location.of(7, 2), Team.WHITE))
            );
    }

    @DisplayName("기물로 구성된 2차원 배열을 각 기물이 위치값을 가진 리스트로 변환할 수 있다. - 블랙")
    @Test
    void convertToBoard_black() {
        // given, when
        Board board = BoardUtil.convertToBoard(TEST_BOARD);

        // then
        assertAll(
            () -> assertThat(board.find(Location.of(1, 8))).isEqualTo(Rook.of(Location.of(1, 8), Team.BLACK)),
            () -> assertThat(board.find(Location.of(2, 8))).isEqualTo(Knight.of(Location.of(2, 8), Team.BLACK)),
            () -> assertThat(board.find(Location.of(3, 8))).isEqualTo(Bishop.of(Location.of(3, 8), Team.BLACK)),
            () -> assertThat(board.find(Location.of(4, 8))).isEqualTo(Queen.of(Location.of(4, 8), Team.BLACK)),
            () -> assertThat(board.find(Location.of(5, 8))).isEqualTo(King.of(Location.of(5, 8), Team.BLACK)),
            () -> assertThat(board.find(Location.of(7, 7))).isEqualTo(Pawn.of(Location.of(7, 7), Team.BLACK))
        );
    }

    @DisplayName("Board를 넣으면 2차원 배열의 뷰보드로 변환할 수 있다.")
    @Test
    void convertToViewBoard() {
        // given
        Board board = BoardUtil.generateInitialBoard();
        char[][] defaultViewBoard = {
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}
        };

        // when
        char[][] viewBoard = BoardUtil.generateViewBoard(board);

        // then
        assertThat(viewBoard).isEqualTo(defaultViewBoard);
    }
}
