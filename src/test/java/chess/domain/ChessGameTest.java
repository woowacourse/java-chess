package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = ChessGame.createGame();
    }

    @Test
    @DisplayName("source 위치에 기물이 없으면 예외가 발생한다.")
    void validateSource() {
        Position source = new Position(3, 4);
        Position target = new Position(4, 4);

        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] source 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("현재 팀이 아닌 기물을 움직이려하면 예외가 발생한다.")
    void validateTurn() {
        Position source = new Position(7, 4);
        Position target = new Position(5, 4);

        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 현재는 WHITE팀 차례입니다.");
    }

    @Test
    @DisplayName("모든 팀의 점수를 구한다.")
    void getScoreAllTeam() {
        Map<Team, Score> scoreAllTeam = chessGame.getScoreAllTeam();
        Score score = new Score(38);

        assertAll(
                () -> assertThat(scoreAllTeam.get(Team.WHITE)).isEqualTo(score),
                () -> assertThat(scoreAllTeam.get(Team.BLACK)).isEqualTo(score)
        );
    }
}
