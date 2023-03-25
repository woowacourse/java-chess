package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("체스판을 생성한다.")
    void createBoard() {
        assertDoesNotThrow(Board::new);
    }

    @Test
    @DisplayName("보드가 생성되면 32개의 Piece를 가진다.")
    void containsPieces() {
        assertThat(board.getBoard()).hasSize(32);
    }

    @Test
    @DisplayName("말은 규칙에 따라 움직인다.")
    void move() {
        board.move(of("a2"), of("a3"));

        assertThat(board.getBoard().keySet()).contains(of("a3"));
    }

    @Test
    @DisplayName("knight는 가는 길목에 말이 있어도 움직일 수 있다.")
    void knight_can_move() {
        board.move(of("b1"), of("a3"));

        assertThat(board.getBoard().keySet()).contains(of("a3"));
    }

    @Test
    @DisplayName("knight는 이동할 칸에 말이 있으면 이동할 수 없다.")
    void knight_cannot_move() {
        board.move(of("a2"), of("a3"));

        assertThrows(IllegalArgumentException.class,
                () -> board.move(of("b1"), of("a3")));
    }

    @Test
    @DisplayName("이동하고자 하는 길목에 같은 팀 말이 있으면 이동이 불가능하고, 예외가 발생한다.")
    void cannot_move_by_sameTeamPiece() {
        assertThatThrownBy(() -> board.move(of("a1"), of("a3")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 칸입니다.");
    }

    @Test
    @DisplayName("이동하고자 하는 길목에 다른 팀 말이 있으면 그 말을 대체하고 그 위치로 이동한다.")
    void can_move_by_enemy() {
        board.move(of("a2"), of("a4"));
        board.move(of("a4"), of("a5"));
        board.move(of("a5"), of("a6"));

        assertDoesNotThrow(() -> board.move(of("a6"), of("a7")));
    }

    @ParameterizedTest(name = "source 위치에 있는 말의 색이 현재 차례의 팀의 색과 같은지 확인한다.")
    @CsvSource({"a2,WHITE,true", "b7,BLACK,true", "c8,WHITE,false"})
    void isSameColor(String source, Team team, boolean expected) {
        assertThat(board.isSameColor(of(source), team)).isEqualTo(expected);
    }

    @DisplayName("팀의 남아있는 말들로 점수를 계산한다.")
    @Test
    void calculate_score() {
        board.move(of("a2"), of("a4"));
        board.move(of("b7"), of("b5"));
        board.move(of("a4"), of("b5"));
        board.move(of("b2"), of("b4"));
        board.move(of("a1"), of("a7"));


        assertThat(board.calculateTotalScoreBy(Team.WHITE)).isEqualTo(37.0);
        assertThat(board.calculateTotalScoreBy(Team.BLACK)).isEqualTo(36.0);
    }

    @DisplayName("세로줄에 같은 팀의 Pawn이 있으면 Pawn의 점수는 0.5점으로 한다.")
    @Test
    void calculate_score_by_Pawn() {
        board.move(of("a2"), of("a4"));
        board.move(of("b7"), of("b5"));
        board.move(of("a4"), of("b5"));

        assertThat(board.calculateTotalScoreBy(Team.WHITE)).isEqualTo(37.0);
        assertThat(board.calculateTotalScoreBy(Team.BLACK)).isEqualTo(37.0);
    }

    private Square of(String input) {
        File file = File.findFileBy(input.split("")[0]);
        Rank rank = Rank.findRankBy(input.split("")[1]);

        return Square.of(file, rank);
    }
}
