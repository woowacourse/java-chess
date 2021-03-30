package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private static final char[][] TEST_BOARD = {
        {'R', 'N', '.', 'Q', 'K', 'B', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', 'N', 'R'},
        {'.', '.', '.', '.', '.', '.', '.', 'p'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', 'B', '.', '.', 'p', '.', '.'},
        {'.', '.', '.', '.', 'P', '.', 'p', '.'},
        {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'}
    };

    private Board initialBoard;
    private Board testBoard;

    @BeforeEach
    void setUp() {
        initialBoard = Board.createWithInitialLocation();
        testBoard = BoardUtil.generateBoard(TEST_BOARD);
    }

    @DisplayName("기물들이 초기위치를 가지는 보드 생성 테스트")
    @Test
    void createWithInitialLocation() {
        Board initialBoard = Board.createWithInitialLocation();

        assertThat(initialBoard.toList())
            .containsExactlyInAnyOrderElementsOf(BoardUtil.generateInitialBoard().toList());
    }

    @DisplayName("체스 말은 시작위치와 목표위치가 같으면 이동하지 못한다.")
    @Test
    void move_samePosition() {
        // given, when
        Location source = Location.of(1, 1);
        Location target = Location.of(1, 1);

        // then
        assertThatThrownBy(() -> initialBoard.move(source, target, Team.WHITE))
            .isInstanceOf(MoveFailureException.class);
    }

    @DisplayName("목표위치에 같은 팀의 말이 있으면 이동하지 못한다.")
    @Test
    void move_sameTeamAtTarget() {
        // given, when
        Location rookLocation = Location.of(1, 1);
        Location pawnLocation = Location.of(1, 2);

        // then
        assertThatThrownBy(() -> initialBoard.move(rookLocation, pawnLocation, Team.WHITE))
            .isInstanceOf(MoveFailureException.class);
    }

    @DisplayName("이동 테스트 - 룩")
    @Test
    void move_rook() {
        // given
        Location source = Location.of(1, 1);
        Location target = Location.of(1, 5);
        Piece sourcePiece = testBoard.find(source);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
    }

    @DisplayName("공격 테스트 - 룩")
    @Test
    void attack_rook() {
        // given
        Location source = Location.of(1, 1);
        Location target = Location.of(1, 8);
        Piece sourcePiece = testBoard.find(source);
        Piece targetPiece = testBoard.find(target);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
        assertThat(testBoard.toList()).doesNotContain(targetPiece);
    }

    @DisplayName("이동 테스트 - 나이트")
    @Test
    void move_knight() {
        // given
        Location source = Location.of(2, 1);
        Location target = Location.of(1, 3);
        Piece sourcePiece = testBoard.find(source);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
    }

    @DisplayName("공격 테스트 - 나이트")
    @Test
    void attack_knight() {
        // given
        Location source = Location.of(2, 1);
        Location target = Location.of(3, 3);
        Piece sourcePiece = testBoard.find(source);
        Piece targetPiece = testBoard.find(target);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
        assertThat(testBoard.toList()).doesNotContain(targetPiece);
    }

    @DisplayName("이동 테스트 - 비숍")
    @Test
    void move_bishop() {
        // given
        Location source = Location.of(3, 1);
        Location target = Location.of(5, 3);
        Piece sourcePiece = testBoard.find(source);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
    }

    @DisplayName("공격 테스트 - 비숍")
    @Test
    void attack_bishop() {
        // given
        Location source = Location.of(3, 1);
        Location target = Location.of(8, 6);
        Piece sourcePiece = testBoard.find(source);
        Piece targetPiece = testBoard.find(target);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
        assertThat(testBoard.toList()).doesNotContain(targetPiece);
    }

    @DisplayName("이동 테스트 - 퀸")
    @Test
    void move_queen() {
        // given
        Location source = Location.of(4, 1);
        Location target = Location.of(4, 5);
        Piece sourcePiece = testBoard.find(source);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
    }

    @DisplayName("공격 테스트 - 퀸")
    @Test
    void attack_queen() {
        // given
        Location source = Location.of(4, 1);
        Location target = Location.of(4, 8);
        Piece sourcePiece = testBoard.find(source);
        Piece targetPiece = testBoard.find(target);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
        assertThat(testBoard.toList()).doesNotContain(targetPiece);
    }

    @DisplayName("이동 테스트 - 킹")
    @Test
    void move_king() {
        // given
        Location source = Location.of(5, 1);
        Location target = Location.of(6, 2);
        Piece sourcePiece = testBoard.find(source);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
    }

    @DisplayName("공격 테스트 - 킹")
    @Test
    void attack_king() {
        // given
        Location source = Location.of(5, 1);
        Location target = Location.of(5, 2);
        Piece sourcePiece = testBoard.find(source);
        Piece targetPiece = testBoard.find(target);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
        assertThat(testBoard.toList()).doesNotContain(targetPiece);
    }

    @DisplayName("이동 테스트 - 초기 위치 폰")
    @Test
    void move_pawnInInitial() {
        // given
        Location initialSource = Location.of(7, 2);
        Location initialTarget = Location.of(7, 4);
        Piece sourcePiece = testBoard.find(initialSource);

        // when
        testBoard.move(initialSource, initialTarget, Team.WHITE);

        // then
        assertThat(testBoard.find(initialTarget)).isEqualTo(sourcePiece);
    }

    @DisplayName("이동 테스트 - 초기 위치가 아닌 폰")
    @Test
    void move_pawnNotInInitial() {
        // given
        Location source = Location.of(6, 3);
        Location possibleTarget = Location.of(6, 4);
        Location impossibleTarget = Location.of(6, 6);
        Piece sourcePiece = testBoard.find(source);

        // when
        testBoard.move(source, possibleTarget, Team.WHITE);

        // then
        assertThat(testBoard.find(possibleTarget)).isEqualTo(sourcePiece);
        assertThatThrownBy(() -> testBoard.move(possibleTarget, impossibleTarget, Team.WHITE))
            .isInstanceOf(MoveFailureException.class);
    }

    @DisplayName("폰이 직선 이동하는 경우 목표 위치에 적의 말이 있으면 이동하지 못한다.")
    @Test
    void move_forward_pawn() {
        // given, when
        Location source = Location.of(8, 5);
        Location target = Location.of(8, 6);
        Piece sourcePiece = testBoard.find(target);

        // then
        assertThatThrownBy(() -> testBoard.move(source, target, Team.WHITE))
            .isInstanceOf(MoveFailureException.class);
    }

    @DisplayName("공격 테스트 - 폰")
    @Test
    void attack_pawn() {
        // given
        Location source = Location.of(8, 5);
        Location target = Location.of(7, 6);
        Piece sourcePiece = testBoard.find(source);
        Piece targetPiece = testBoard.find(target);

        // when
        testBoard.move(source, target, Team.WHITE);

        // then
        assertThat(testBoard.find(target)).isEqualTo(sourcePiece);
        assertThat(testBoard.toList()).doesNotContain(targetPiece);
    }

    @DisplayName("폰은 목표위치에 적이 있어야 공격할 수 있다.")
    @Test
    void move_diagonal_pawn() {
        // given, when
        Location source = Location.of(6, 3);
        Location target = Location.of(5, 4);

        // then
        assertThatThrownBy(() -> testBoard.move(source, target, Team.WHITE))
            .isInstanceOf(MoveFailureException.class);
    }

    @DisplayName("기물이 이동할 능력이 있어도, 중간에 말이 있으면 움직이지 못한다.")
    @Test
    void move_with_obstacle() {
        assertThatThrownBy(
            () -> initialBoard.move(Location.of(1, 1), Location.of(1, 3), Team.WHITE)
        ).isInstanceOf(MoveFailureException.class);
    }
}
