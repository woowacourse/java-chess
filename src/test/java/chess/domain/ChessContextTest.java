package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.domain.player.Result;
import chess.dto.MoveRequestDto;

class ChessContextTest {
    private TestGameContext context;
    private Player white;
    private Player black;

    @BeforeEach
    void setUp() {
        context = new TestGameContext();
        white = new Player("hodol", "password");
        black = new Player("pobi", "password");
    }

    @DisplayName("새 체스 게임 추가 테스트")
    @Test
    void addNewGame() {
        // when
        int gameId = context.addGame(white, black);

        // then
        assertThat(context.isEmpty()).isEqualTo(false);
        assertThat(gameId).isEqualTo(1);
    }

    @DisplayName("체스게임 읽어오기 테스트")
    @Test
    void findGameById() {
        // given
        int gameId = context.addGame(white, black);

        // when
        Game gameFoundById = context.findGameById(gameId);

        // then
        assertThat(gameFoundById.getPlayers().get(Side.WHITE)).isEqualTo(white);
        assertThat(gameFoundById.getPlayers().get(Side.BLACK)).isEqualTo(black);
    }

    @DisplayName("체스게임 초기화 테스트")
    @Test
    void resetGameById() {
        // given
        int gameId = context.addGame(white, black);

        // when
        Game game = context.findGameById(gameId);
        assertThat(game.isWhiteTurn()).isTrue();
        game.move("b2", "b4");
        assertThat(game.isWhiteTurn()).isFalse();
        context.resetGameById(gameId);

        // then
        assertThat(context.findGameById(gameId).isWhiteTurn()).isTrue();
    }

    @DisplayName("체스게임 moves를 통해 원상복귀 테스트")
    @Test
    void recoverMovesById() {
        // given
        int gameId = context.addGame(white, black);

        // when
        List<MoveRequestDto> moves = new ArrayList<MoveRequestDto>() {{
            add(new MoveRequestDto("b2", "b4"));
            add(new MoveRequestDto("b4", "b5"));
            add(new MoveRequestDto("b5", "b6"));
            add(new MoveRequestDto("b6", "c7"));
        }};
        context.recoverMovesById(gameId, moves);

        // then
        Game game = context.findGameById(gameId);
        assertThat(game.move("b6", "c7")).isFalse();
    }

    @DisplayName("체스게임 삭제 테스트")
    @Test
    void deleteGameById() {
        // given
        int gameId = context.addGame(white, black);

        // when
        context.finishGameById(gameId);

        // then
        assertThat(context.isEmpty()).isEqualTo(true);
    }

    @DisplayName("체스게임 점수 업데이트 테스트")
    @Test
    void getScoreById() {
        // given
        int gameId = context.addGame(white, black);
        List<MoveRequestDto> moves = new ArrayList<MoveRequestDto>() {{
            add(new MoveRequestDto("b2", "b4"));
            add(new MoveRequestDto("b4", "b5"));
            add(new MoveRequestDto("b5", "b6"));
            add(new MoveRequestDto("b6", "c7"));
            add(new MoveRequestDto("c7", "d8"));
        }};

        // when
        context.recoverMovesById(gameId, moves);

        // then
        assertThat(context.getScoreById(gameId, Side.WHITE)).isEqualTo(37);
        assertThat(context.getScoreById(gameId, Side.BLACK)).isEqualTo(28);
    }

    @DisplayName("플레이어 승패 기록 테스트")
    @Test
    void playerStatisticsTest() {
        // given
        int gameId = context.addGame(white, black);
        List<MoveRequestDto> moves = new ArrayList<MoveRequestDto>() {{
            add(new MoveRequestDto("b2", "b4"));
            add(new MoveRequestDto("b4", "b5"));
            add(new MoveRequestDto("b5", "b6"));
            add(new MoveRequestDto("b6", "c7"));
            add(new MoveRequestDto("c7", "d8"));
        }};

        // when
        context.recoverMovesById(gameId, moves);
        context.finishGameById(gameId);

        // then
        assertThat(white.recordOf(Result.WIN)).isEqualTo(1);
        assertThat(black.recordOf(Result.LOSE)).isEqualTo(1);
    }
}
