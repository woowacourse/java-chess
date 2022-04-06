package chess.dao;

import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
import chess.dto.TurnDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameDAOTest {

    private static final String NOT_EXIST_GAME = "해당 게임이 존재하지 않습니다.";

    GameDAO gameDAO;
    GameDTO gameDTO;
    int gameId;

    @BeforeEach
    void setUp() {
        gameDAO = new GameDAO();
        gameDTO = new GameDTO("green", "lawn");
        gameDAO.saveGameInformation(gameDTO, new TurnDTO("white"));
        gameId = gameDAO.findGameIdByUser(gameDTO);
    }

    @AfterEach
    void delete() {
        gameDAO.deleteGame(new GameIdDTO(gameId));
    }

    @Test
    @DisplayName("게임 정보 저장")
    void saveGame() {
        gameDAO = new GameDAO();
        GameDTO gameTestDTO = new GameDTO("greengreen", "lawn");

        assertThatNoException().isThrownBy(() -> gameDAO.saveGameInformation(gameTestDTO, new TurnDTO("white")));
        int id = gameDAO.findGameIdByUser(gameTestDTO);
        gameDAO.deleteGame(new GameIdDTO(id));
    }

    @Test
    @DisplayName("경기를 진행하고 있는 유저의 이름으로 game id 검색")
    void findGameIdByUser() {
        int gameId = gameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }
        assertThat(gameId).isEqualTo(gameDAO.findGameIdByUser(gameDTO));
    }

    @Test
    @DisplayName("game id가 일치하는 게임 정보 삭제")
    void deleteGame() {
        int gameId = gameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }
        assertThatNoException().isThrownBy(() -> gameDAO.deleteGame(new GameIdDTO(gameId)));
    }

    @Test
    @DisplayName("game id에 해당하는 turn 업데이트")
    void updateTurn() {
        int gameId = gameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }
        assertThatNoException().isThrownBy(() -> gameDAO.updateTurn(new GameIdDTO(gameId), "black"));
    }

    @Test
    @DisplayName("game id의 turn 찾기")
    void findTurn() {
        int gameId = gameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }
        assertThat(gameDAO.findTurn(new GameIdDTO(gameId))).isEqualTo("white");
    }
}