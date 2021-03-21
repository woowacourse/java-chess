package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.location.Location;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardUtil.generateInitialBoard();
    }

    @DisplayName("기물 이동 - 출발 위치와 목표 위치가 같을 수 없다.")
    @Test
    void nonMovable_same() {
        // given
        Location source = Location.of(1, 1);
        Location target = Location.of(1, 1);

        // then
        assertThatThrownBy(() -> board.move(source, target, Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기물 이동 - 현재 턴의 기물만 이동할 수 있다.")
    @Test
    void nonMovable_turn() {
        // given
        Location blackPawnLocation = Location.of(1, 7);
        Location target = Location.of(1, 5);

        // then
        assertThatThrownBy(() -> board.move(blackPawnLocation, target, Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("검색 - 위치값에 따라 기물을 찾을 수 있다.")
    @Test
    void find() {
        // given
        Location whitePawn = Location.of(1,2);
        Location blackPawn = Location.of(1, 7);
        Location whiteQueen = Location.of(4, 1);
        Location blackQueen = Location.of(4, 8);

        // then
        assertAll(
            () -> assertThat(board.find(whitePawn)).isEqualTo(Pawn.of(Location.of(1,2), Team.WHITE)),
            () -> assertThat(board.find(blackPawn)).isEqualTo(Pawn.of(Location.of(1,7), Team.BLACK)),
            () -> assertThat(board.find(whiteQueen)).isEqualTo(Queen.of(Location.of(4,1), Team.WHITE)),
            () -> assertThat(board.find(blackQueen)).isEqualTo(Queen.of(Location.of(4,8), Team.BLACK))
        );
    }

    @DisplayName("존재 여부 - 위치값에 따라 기물이 존재하는지 확인할 수 있다.")
    @Test
    void isPieceExistIn() {
        // given
        Location existLocation1 = Location.of(1, 2);
        Location existLocation2 = Location.of(5, 1);
        Location existLocation3 = Location.of(6, 1);
        Location nonExistLocation1 = Location.of(3, 3);
        Location nonExistLocation2 = Location.of(4, 4);
        Location nonExistLocation3 = Location.of(5, 3);

        // then
        assertThat(board.isPieceExistIn(existLocation1)).isTrue();
        assertThat(board.isPieceExistIn(existLocation2)).isTrue();
        assertThat(board.isPieceExistIn(existLocation3)).isTrue();
        assertThat(board.isPieceExistIn(nonExistLocation1)).isFalse();
        assertThat(board.isPieceExistIn(nonExistLocation2)).isFalse();
        assertThat(board.isPieceExistIn(nonExistLocation3)).isFalse();
    }

    @DisplayName("점수 계산 - 현재 보드의 기물을 이용해 각 팀의 점수를 계산할 수 있다.")
    @Test
    void calculateScore() {
        // given
        char[][] testBoard = {
            {'.', 'K', 'R', '.', '.', '.', '.', '.'},
            {'P', '.', 'P', 'B', '.', '.', '.', '.'},
            {'.', 'P', '.', '.', 'Q', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', 'n', 'q', '.'},
            {'.', '.', '.', '.', '.', 'p', '.', 'p'},
            {'.', '.', '.', '.', '.', 'p', 'p', '.'},
            {'.', '.', '.', '.', 'r', 'k', '.', '.'}
        };
        Board board = BoardUtil.convertToBoard(testBoard);

        // then
        assertThat(board.score(Team.BLACK)).isEqualTo(20);
        assertThat(board.score(Team.WHITE)).isEqualTo(19.5);
    }
}
