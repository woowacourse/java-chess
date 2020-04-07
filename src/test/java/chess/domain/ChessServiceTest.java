package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.board.Path;
import chess.domain.board.Position;
import chess.domain.piece.Side;
import chess.domain.player.Player;
import chess.domain.player.Result;

class ChessServiceTest {
    private FakeChessServiceImpl service;
    private Player white;
    private Player black;

    @BeforeEach
    void setUp() {
        service = new FakeChessServiceImpl();
        white = new Player(1, "hodol", "password");
        black = new Player(2, "pobi", "password");
    }

    @DisplayName("새 체스 게임 추가 테스트")
    @Test
    void addNewGame() {
        // when
        Map<Integer, Map<Side, Player>> playerContexts = service.addGame(white, black);

        // then
        assertThat(playerContexts.get(1)).isNotNull();
        assertThat(playerContexts.size()).isEqualTo(1);
    }

    @DisplayName("체스게임 읽어오기 테스트")
    @Test
    void findGameById() {
        // given
        service.addGame(white, black);
        int gameId = (int)service.getPlayerContexts().keySet().toArray()[0];

        // when
        Game gameFoundById = service.findGameById(gameId);

        // then
        assertThat(gameFoundById.getPlayers().get(Side.WHITE)).isEqualTo(white);
        assertThat(gameFoundById.getPlayers().get(Side.BLACK)).isEqualTo(black);
    }

    @DisplayName("체스게임 초기화 테스트")
    @Test
    void resetGameById() {
        // given
        service.addGame(white, black);
        int gameId = (int)service.getPlayerContexts().keySet().toArray()[0];

        // when
        Game game = service.findGameById(gameId);
        assertThat(game.isWhiteTurn()).isTrue();
        game.move("b2", "b4");
        assertThat(game.isWhiteTurn()).isFalse();
        service.resetGameById(gameId);

        // then
        assertThat(service.findGameById(gameId).isWhiteTurn()).isTrue();
    }

    @DisplayName("체스게임 moves를 통해 원상복귀 테스트")
    @Test
    void recoverMovesById() {
        // given
        service.addGame(white, black);
        int gameId = (int)service.getPlayerContexts().keySet().toArray()[0];

        // when
        List<Path> paths = new ArrayList<Path>() {{
            add(new Path(Position.of("b2"), Position.of("b4")));
            add(new Path(Position.of("b4"), Position.of("b5")));
            add(new Path(Position.of("b5"), Position.of("b6")));
            add(new Path(Position.of("b6"), Position.of("c7")));
        }};

        // then
        Game game = service.findGameById(gameId);
        paths.forEach(path -> service.addMoveByGameId(gameId, path.getStart(), path.getEnd()));
        assertThat(game.move("b6", "c7")).isFalse();
    }

    @DisplayName("체스게임 삭제 테스트")
    @Test
    void deleteGameById() {
        // given
        service.addGame(white, black);
        int gameId = (int)service.getPlayerContexts().keySet().toArray()[0];

        // when
        service.finishGameById(gameId);

        // then
        assertThat(service.getPlayerContexts()).isEmpty();
    }

    @DisplayName("체스게임 점수 업데이트 테스트")
    @Test
    void getScoreById() {
        // given
        service.addGame(white, black);
        int gameId = (int)service.getPlayerContexts().keySet().toArray()[0];
        List<Path> paths = new ArrayList<Path>() {{
            add(new Path(Position.of("b2"), Position.of("b4")));
            add(new Path(Position.of("b4"), Position.of("b5")));
            add(new Path(Position.of("b5"), Position.of("b6")));
            add(new Path(Position.of("b6"), Position.of("c7")));
            add(new Path(Position.of("c7"), Position.of("d8")));
        }};

        // when
        service.findGameById(gameId);
        paths.forEach(path -> service.addMoveByGameId(gameId, path.getStart(), path.getEnd()));

        // then
        assertThat(service.getScoreById(gameId, Side.WHITE)).isEqualTo(37);
        assertThat(service.getScoreById(gameId, Side.BLACK)).isEqualTo(28);
    }

    @DisplayName("플레이어 승패 기록 테스트")
    @Test
    void playerStatisticsTest() {
        // given
        service.addGame(white, black);
        int gameId = (int)service.getPlayerContexts().keySet().toArray()[0];
        List<Path> paths = new ArrayList<Path>() {{
            add(new Path(Position.of("b2"), Position.of("b4")));
            add(new Path(Position.of("b4"), Position.of("b5")));
            add(new Path(Position.of("b5"), Position.of("b6")));
            add(new Path(Position.of("b6"), Position.of("c7")));
            add(new Path(Position.of("c7"), Position.of("d8")));
        }};

        // when
        service.findGameById(gameId);
        paths.forEach(path -> service.addMoveByGameId(gameId, path.getStart(), path.getEnd()));
        service.finishGameById(gameId);

        // then
        assertThat(white.recordOf(Result.WIN)).isEqualTo(1);
        assertThat(black.recordOf(Result.LOSE)).isEqualTo(1);
    }
}
