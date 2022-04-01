package chess.model.board;

import static chess.model.Team.BLACK;
import static chess.model.Team.WHITE;
import static chess.model.position.File.A;
import static chess.model.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @DisplayName("8x8의 보드판이 생성되는지 확인한다.")
    @Test
    void construct_board() {
        Board board = new Board();

        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @DisplayName("상대편 기물을 선택하면 예외를 발생한다.")
    @Test
    void checkSameTeam() {
        Board board = new Board();

        assertThatThrownBy(() -> board.checkSameTeam(BLACK, Position.of(TWO, A)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대편 기물은 움직일 수 없습니다.");
    }

    @DisplayName("선택한 위치에 기물이 없으면 예외를 발생 시킨다.")
    @Test
    void move_none_exception() {
        Board board = new Board();

        assertThatThrownBy(() -> board.move(Position.of(Rank.THREE, File.D), Position.of(Rank.FOUR, File.D)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 위치에 기물이 없습니다.");
    }

    @DisplayName("선택한 기물을 이동 시킬수 없는 위치가 입력 되면 예외를 발생한다.")
    @Test
    void move_can_not_exception() {
        Board board = new Board();

        assertThatThrownBy(() -> board.move(Position.of(Rank.TWO, File.D), Position.of(Rank.FIVE, File.D)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 기물을 이동 시킬수 없는 위치가 입력 됬습니다.");
    }

    @DisplayName("죽은 King이 없으면 false를 반환한다.")
    @Test
    void isKingDead() {
        Board board = new Board();

        assertThat(board.isKingDead()).isFalse();
    }

    @DisplayName("화이트가 폰으로 블랙팀의 폰을 잡으면 각각 37점, 37점을 반환한다.")
    @Test
    void calculateScore() {
        Board board = new Board();
        board.move(Position.from("b2"), Position.from("b4"));
        board.move(Position.from("c7"), Position.from("c5"));
        board.move(Position.from("b4"), Position.from("c5"));

        GameResult gameResult = board.calculateScore();
        double blackScore = gameResult.getScores().get(BLACK);
        double whiteScore = gameResult.getScores().get(WHITE);
        assertThat(blackScore * 100 / 100.0).isEqualTo(37.0);
        assertThat(whiteScore * 100 / 100.0).isEqualTo(37.0);
    }

    @DisplayName("화이트가 나이트로 블랙팀의 룩을 잡으면 각각 38점, 33점을 반환한다.")
    @Test
    void calculateScore_2() {
        Board board = new Board();
        board.move(Position.from("b1"), Position.from("c3"));
        board.move(Position.from("h7"), Position.from("h6"));
        board.move(Position.from("c3"), Position.from("d5"));
        board.move(Position.from("h6"), Position.from("h5"));
        board.move(Position.from("d5"), Position.from("b6"));
        board.move(Position.from("h5"), Position.from("h4"));
        board.move(Position.from("b6"), Position.from("a8"));

        GameResult gameResult = board.calculateScore();
        double blackScore = gameResult.getScores().get(BLACK);
        double whiteScore = gameResult.getScores().get(WHITE);
        assertThat(blackScore * 100 / 100.0).isEqualTo(33.0);
        assertThat(whiteScore * 100 / 100.0).isEqualTo(38.0);
    }
}
