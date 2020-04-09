package chess.service;

import chess.dao.ChessDao;
import chess.dao.InMemoryChessDao;
import chess.dao.InMemoryMovementDao;
import chess.dao.MovementDao;
import chess.entity.ChessGame;
import chess.piece.Team;
import chess.service.dto.ChessBoardResponse;
import chess.service.dto.MoveRequest;
import chess.service.dto.MoveResponse;
import chess.service.dto.SavedGameBundleResponse;
import chess.service.dto.SurrenderRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class ChessServiceTest {

    private ChessService chessService;

    private MovementDao movementDAO;
    private ChessDao chessDAO;

    @DisplayName("메모리로 테스트")
    @BeforeEach
    void setUp() {
        chessDAO = new InMemoryChessDao();
        movementDAO = new InMemoryMovementDao();
        chessService = new ChessService(chessDAO, movementDAO);
    }

    @AfterEach
    void tearDown() throws SQLException {
        movementDAO.deleteAll();
        chessDAO.deleteAll();
    }

    @DisplayName("정상적으로 움직임 성공시 상대 턴으로 바뀐다.")
    @Test
    void move() throws SQLException {
        //given
        ChessGame chessGame = new ChessGame(true);
        chessGame = chessDAO.save(chessGame);
        MoveRequest moveRequest = new MoveRequest(chessGame.getId(), "a2", "a3");

        //when
        MoveResponse moveResponse = chessService.move(moveRequest);

        //then
        assertThat(moveResponse.getNowTurn()).isEqualTo(Team.BLACK);
    }

    @DisplayName("새로운 게임 생성시 화이트부터 시작, 점수는 각각 38점")
    @Test
    void save() throws SQLException {
        //when
        ChessBoardResponse chessBoardResponse = chessService.save();

        //then
        assertThat(chessBoardResponse.getTurn()).isEqualTo(Team.WHITE.name());
        assertThat(chessBoardResponse.getTeamScoreDto().getBlackScore()).isEqualTo(38);
        assertThat(chessBoardResponse.getTeamScoreDto().getWhiteScore()).isEqualTo(38);
    }

    @DisplayName("활성화된 저장된 게임 목록 가져오기")
    @Test
    void loadAllSavedGames() throws SQLException {
        //given
        ChessGame chessGame1 = new ChessGame(true);
        ChessGame chessGame2 = new ChessGame(false);
        chessDAO.save(chessGame1);
        chessDAO.save(chessGame2);

        //when
        SavedGameBundleResponse savedGameBundleResponse = chessService.loadAllSavedGames();

        //then
        assertThat(savedGameBundleResponse.getSavedGameResponses()).hasSize(1);
    }

    @DisplayName("활성화 되어있는 저장된 게임 불러오기")
    @Test
    void loadSavedGame() throws SQLException {
        //given
        ChessGame chessGame1 = new ChessGame(true);
        chessGame1 = chessDAO.save(chessGame1);

        //when
        ChessBoardResponse chessBoardResponse = chessService.loadSavedGame(chessGame1.getId());

        //then
        assertThat(chessBoardResponse.getId()).isEqualTo(chessGame1.getId());
    }

    @DisplayName("항복하기")
    @Test
    void surrender() throws SQLException {
        //given
        ChessGame chessGame1 = new ChessGame(true);
        chessGame1 = chessDAO.save(chessGame1);
        SurrenderRequest surrenderRequest = new SurrenderRequest(chessGame1.getId(), "WHITE");

        //when
        chessService.surrender(surrenderRequest);
        ChessGame chessGame = chessDAO.findById(chessGame1.getId())
                .orElseThrow(NoSuchElementException::new);

        //then
        assertThat(chessGame.isActive()).isFalse();
    }
}