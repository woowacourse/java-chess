package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessBoardInitLogic;
import chess.domain.ChessBoardPosition;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import java.sql.Connection;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DbBoardDaoTest {

    @BeforeAll
    static  void setUp() {
        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.saveGame(1111, Team.of(Team.WHITE));
    }

    @Test
    void connection() {
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void updateAll() {
        DbGameDao dbGameDao = new DbGameDao();
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create(1111);
        chessGame.initialize(Team.WHITE, ChessBoardInitLogic.initialize());
        //dbGameDao.saveGame(GameInformationDto.of(1111, Team.WHITE));
        dbBoardDao.updateAll(chessGame.getGameId(), chessGame.getChessBoardInformation());
    }

    @Test
    void findAll() {
        DbGameDao dbGameDao = new DbGameDao();
        final DbBoardDao dbBoardDao = new DbBoardDao();
        final Connection connection = dbBoardDao.getConnection();
        ChessGame chessGame = ChessGame.create(1111);
        dbGameDao.saveGame(1111, Team.of(Team.WHITE));
        Map<ChessBoardPosition, ChessPiece> mapData = dbBoardDao.findAll(chessGame.getGameId());
        assertThat(mapData).isEmpty();
    }

    @AfterEach
    void afterEach() {
        DbBoardDao dbBoardDao = new DbBoardDao();
        dbBoardDao.deleteAll(1111);

        DbGameDao dbGameDao = new DbGameDao();
        dbGameDao.deleteGameData(1111);
    }
}
