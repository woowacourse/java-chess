package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.ChessGame;
import chess.model.domain.board.TeamScore;
import chess.model.domain.piece.Color;
import chess.model.dto.GameResultDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessResultDaoTest {

    private static final ChessResultDao CHESS_RESULT_DAO = ChessResultDao.getInstance();
    private static final int GAME_ID = 1;
    private static final Map<Color, Double> TEAM_SCORE;

    static {
        TeamScore defaultTeamScore = new ChessGame().getTeamScore();
        TEAM_SCORE = Collections.unmodifiableMap(new HashMap<>(defaultTeamScore.getTeamScore()));
    }

    @BeforeEach
    void setUp() {
        CHESS_RESULT_DAO.insert(GAME_ID, new TeamScore(TEAM_SCORE));
    }

    @AfterEach
    void tearDown() {
        CHESS_RESULT_DAO.delete(GAME_ID);
    }

    @Test
    void getInstance() {
        ChessResultDao chessResultDao1 = ChessResultDao.getInstance();
        ChessResultDao chessResultDao2 = ChessResultDao.getInstance();
        assertThat(chessResultDao1 == chessResultDao2).isTrue();
    }

    @Test
    void update() {
        Map<Color, Double> teamScoreUpdate = new HashMap<>(TEAM_SCORE);
        teamScoreUpdate.put(Color.BLACK, 1.0);
        CHESS_RESULT_DAO.update(GAME_ID, new TeamScore(teamScoreUpdate));
        assertThat(CHESS_RESULT_DAO.select(GAME_ID)).isEqualTo(teamScoreUpdate);
    }

    @Test
    void insert() {
        CHESS_RESULT_DAO.delete(GAME_ID);
        assertThat(CHESS_RESULT_DAO.select(GAME_ID)).isEmpty();

        CHESS_RESULT_DAO.insert(GAME_ID, new TeamScore(TEAM_SCORE));
        assertThat(CHESS_RESULT_DAO.select(GAME_ID)).isEqualTo(TEAM_SCORE);
    }

    @Test
    void delete() {
        assertThat(CHESS_RESULT_DAO.select(GAME_ID)).isEqualTo(TEAM_SCORE);

        CHESS_RESULT_DAO.delete(GAME_ID);
        assertThat(CHESS_RESULT_DAO.select(GAME_ID)).isEmpty();
    }

    @Test
    void select() {
        assertThat(CHESS_RESULT_DAO.select(GAME_ID)).isEqualTo(TEAM_SCORE);
    }

    @Test
    void getWinOrDraw() {
        Optional<GameResultDto> gameResult = CHESS_RESULT_DAO.getWinOrDraw("BLACK");
        assertThat(gameResult.isPresent()).isTrue();
        GameResultDto gameResultDto = gameResult.orElseThrow(IllegalArgumentException::new);
        assertThat(gameResultDto.getWinCount()).isGreaterThanOrEqualTo(0);
        assertThat(gameResultDto.getDrawCount()).isGreaterThanOrEqualTo(0);
        assertThat(gameResultDto.getLoseCount()).isGreaterThanOrEqualTo(0);
    }
}