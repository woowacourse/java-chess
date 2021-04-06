package chess.controller;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

import chess.controller.dto.PieceDTO;
import chess.controller.dto.RoundStatusDTO;
import chess.dao.ChessDAO;
import chess.dao.ChessDAO.Fake;
import chess.domain.TeamColor;
import chess.service.ChessService;
import chess.service.ChessServiceImpl;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessControllerTest {

    ChessDAO chessDAO;
    ChessService chessService;
    ChessController chessController;

    @BeforeEach
    void init() {
        chessDAO = new Fake();
        chessService = new ChessServiceImpl(chessDAO);
        chessController = new ChessController(chessService);
    }

    @Test
    @DisplayName("시작했을 때 piece 리스트가 비어있지 않은 지 확인")
    void startGame() {
        List<PieceDTO> pieceDTOs = chessController.startGame(1L);
        Assertions.assertThat(pieceDTOs).isNotEmpty();
    }

    @Test
    @DisplayName("처음 시작했을 때 하얀팀 차례, 움직일 수 있는 거리들 확인")
    void movablePositions() {
        chessController.startGame(1L);
        RoundStatusDTO roundStatusDTO = chessController.roundStatus(1L);

        TeamColor currentColor = roundStatusDTO.getCurrentColor();
        boolean kingDead = roundStatusDTO.isKingDead();
        Map<String, List<String>> movablePositions = roundStatusDTO.getMovablePositions();

        Assertions.assertThat(currentColor).isEqualTo(WHITE);
        Assertions.assertThat(kingDead).isFalse();
        Assertions.assertThat(movablePositions).isNotEmpty();
    }

    @Test
    @DisplayName("한 번 움직이면 화이트에서 검은색 턴으로 넘어가는 지 확인")
    void move() {
        chessController.startGame(1L);
        chessController.move(1L, "01", "02");

        RoundStatusDTO roundStatusDTO = chessController.roundStatus(1L);
        TeamColor currentColor = roundStatusDTO.getCurrentColor();

        Assertions.assertThat(currentColor).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("restart를 하게 되면 새로운 게임이 시작되는 지 확인")
    void restart() {
        chessController.startGame(1L);
        chessController.move(1L, "01", "02");


        chessController.restart(1L);
        RoundStatusDTO roundStatusDTO = chessController.roundStatus(1L);
        TeamColor currentColor = roundStatusDTO.getCurrentColor();

        Assertions.assertThat(currentColor).isEqualTo(WHITE);
    }

    @Test
    @DisplayName("exit할 때 dao에서 데이터가 사라지는 지 확인")
    void exitGame() {
        long gameId = 1L;
        chessController.startGame(gameId);
        chessController.exitGame(gameId);

        Assertions.assertThat(chessDAO.loadGame(gameId)).isEmpty();
    }

    @Test
    @DisplayName("한 번 움직인 게임을 저장하고 불러올 때 검은색 턴인지 확인")
    void saveGame() {
        long gameId = 1L;
        chessController.startGame(gameId);
        chessController.move(gameId, "01", "02");

        chessController.saveGame(gameId);

        chessController.startGame(gameId);
        RoundStatusDTO roundStatusDTO = chessController.roundStatus(gameId);
        TeamColor currentColor = roundStatusDTO.getCurrentColor();

        Assertions.assertThat(currentColor).isEqualTo(BLACK);
    }
}