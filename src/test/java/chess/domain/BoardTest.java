package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @ParameterizedTest
    @CsvSource(value = {"a1:Rook", "b1:Knight", "c8:Bishop", "d1:Queen", "e1:King", "a2:Pawn", "a3:Blank"}, delimiter = ':')
    void findPiece(String position, String symbol) {
        Board board = new Board();
        assertThat(board.getPiece(Position.from(position)).getClass().getSimpleName()).isEqualTo(symbol);
    }

    @Test
    void movePiece() {
        Board board = new Board();
        String source = "f2";
        String destination = "f4";
        board.movePiece(source, destination, Team.WHITE);
        assertThat(board.getPiece(Position.from(destination)).isPawn()).isTrue();
        assertThat(board.getPiece(Position.from(source)).isBlank()).isTrue();
    }

    @Test
    @DisplayName("같은 팀 말 kill을 시도할 시, 예외가 발생한다.")
    void killSameTeam() {
        Board board = new Board();
        String source = "e1";
        String destination = "f1";
        Assertions.assertThatThrownBy(() -> board.movePiece(source, destination, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("같은 팀은 kill 할 수 없습니다.");
    }

    @Test
    @DisplayName("다른 팀 말을 움직일 시, 예외가 발생한다.")
    void otherTeamPieceMove() {
        Board board = new Board();
        String source = "e8";
        String destination = "e7";
        Assertions.assertThatThrownBy(() -> board.movePiece(source, destination, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 팀 말을 옮길 수 없습니다.");
    }

    @Test
    @DisplayName("화이트팀 말 점수를 조회한다.")
    void getWhiteTeamScore() {
        Board board = new Board();
        assertThat(board.getTeamScore(Team.WHITE)).isEqualTo(38);
    }

    @Test
    @DisplayName("블랙팀 말 점수를 조회한다.")
    void getBlackTeamScore() {
        Board board = new Board();
        assertThat(board.getTeamScore(Team.BLACK)).isEqualTo(38);
    }
}
