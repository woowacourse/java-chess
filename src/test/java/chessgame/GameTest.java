package chessgame;


import chessgame.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chessgame.point.PointFixture.*;
import static chessgame.point.PointFixture.E8;

class GameTest {
    Game game;

    @BeforeEach
    void before() {
        game = new Game();
    }

    @Test
    @DisplayName("상태를 변경할 수 있다.")
    void Should_ChangeStatus_When_SetState() {
        Assertions.assertThatNoException().isThrownBy(() -> game.setState(Command.of("start")));
    }

    @Test
    @DisplayName("상태가 end인지 확인할 수 있다.")
    void Should_Check_When_IsEnd() {
        game.setState(Command.of("end"));
        Assertions.assertThat(game.isEnd()).isTrue();
    }

    @Test
    @DisplayName("king이 제거되지 않고 게임이 끝났음을 알 수 있다.")
    void Should_CheckEnd_When_KingNotRemove(){
        game.setState(Command.of("end"));
        Assertions.assertThat(game.isEndByKing()).isFalse();
    }

    @Test
    @DisplayName("king이 제거되고 게임이 끝났음을 알 수 있다.")
    void Should_CheckEnd_When_KingRemove(){
        Board board = game.board();
        game.setState(Command.of("start"));

        board.move(B1, A3, Team.WHITE);
        board.move(H7, H6, Team.BLACK);
        board.move(A3, B5, Team.WHITE);
        board.move(H8, H7, Team.BLACK);
        board.move(B5, D6, Team.WHITE);
        board.move(A7, A5, Team.BLACK);
        board.move(D6, E8, Team.WHITE);

        Assertions.assertThat(game.isEndByKing()).isTrue();
    }

    @Test
    @DisplayName("Black팀의 king이 제거되었을 때 White팀이 승리한다.")
    void Should_GetWinTeam_When_KingRemove(){
        Board board = game.board();
        game.setState(Command.of("start"));

        board.move(B1, A3, Team.WHITE);
        board.move(H7, H6, Team.BLACK);
        board.move(A3, B5, Team.WHITE);
        board.move(H8, H7, Team.BLACK);
        board.move(B5, D6, Team.WHITE);
        board.move(A7, A5, Team.BLACK);
        board.move(D6, E8, Team.WHITE);

        Assertions.assertThat(game.winTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    @DisplayName("Score를 읽어온다.")
    void Should_GetScore_When_ScoreBoard(){
        Map<Team,Double> score = game.scoreBoard();
        Assertions.assertThat(score.get(Team.WHITE)).isEqualTo(38);
        Assertions.assertThat(score.get(Team.BLACK)).isEqualTo(38);
    }
}
