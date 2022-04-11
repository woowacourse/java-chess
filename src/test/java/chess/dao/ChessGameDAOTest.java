package chess.dao;

import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
import chess.dto.TurnDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ChessGameDAOTest {

    private static final String NOT_EXIST_GAME = "해당 게임이 존재하지 않습니다.";

    private ChessGameDAO chessGameDAO;
    private GameDTO gameDTO;
    private int gameId;

    @BeforeEach
    void setUp() {
        chessGameDAO = new ChessGameDAO();
        gameDTO = new GameDTO("green", "lawn");
        chessGameDAO.saveGame(gameDTO, new TurnDTO("white"));
        gameId = chessGameDAO.findGameIdByUser(gameDTO);
    }

    @AfterEach
    void delete() {
        chessGameDAO.deleteGame(new GameIdDTO(gameId));
    }

    @Test
    @DisplayName("게임 정보 저장")
    void saveGame() {
        chessGameDAO = new ChessGameDAO();
        GameDTO gameTestDTO = new GameDTO("greengreen", "lawn");

        assertThatNoException().isThrownBy(() -> chessGameDAO.saveGame(gameTestDTO, new TurnDTO("white")));
        int id = chessGameDAO.findGameIdByUser(gameTestDTO);
        chessGameDAO.deleteGame(new GameIdDTO(id));
    }

    @Test
    @DisplayName("경기를 진행하고 있는 유저의 이름으로 game id 검색")
    void findGameIdByUser() {
        int gameId = chessGameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }

        assertThat(gameId).isEqualTo(chessGameDAO.findGameIdByUser(gameDTO));
    }

    @Test
    @DisplayName("game id가 일치하는 게임 정보 삭제")
    void deleteGame() {
        int gameId = chessGameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }

        assertThatNoException().isThrownBy(() -> chessGameDAO.deleteGame(new GameIdDTO(gameId)));
    }

    @Test
    @DisplayName("game id에 해당하는 turn 업데이트")
    void updateTurn() {
        int gameId = chessGameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }

        assertThatNoException().isThrownBy(() -> chessGameDAO.updateTurn(new GameIdDTO(gameId), "black"));
    }

    @Test
    @DisplayName("game id의 turn 찾기")
    void findTurn() {
        int gameId = chessGameDAO.findGameIdByUser(gameDTO);
        if (gameId == 0) {
            throw new IllegalArgumentException(NOT_EXIST_GAME);
        }

        assertThat(chessGameDAO.findTurn(new GameIdDTO(gameId))).isEqualTo("white");
    }
}