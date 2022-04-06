package chess.domain.dao;

import chess.domain.dto.PieceDto;
import chess.domain.game.board.ChessBoardFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardDaoTest {

    private Connection connection;
    private BoardDao boardDao;
    private GameDao gameDao;

    @BeforeEach
    void set() throws SQLException {
        connection = Connector.getConnection();
        boardDao = new BoardDao(connection);
        gameDao = new GameDao(connection);
        connection.setAutoCommit(false);
    }

    @Test
    @DisplayName("체스 기물을 저장한다.")
    void save() {
        gameDao.save(ChessBoardFactory.initBoard());
        boardDao.save(1,"a1","Pawn","WHITE");
    }

    @Test
    @DisplayName("가장 최근 게임의 기물 정보를 삭제한다.")
    void delete() throws SQLException {
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        boardDao.save(gameDao.findLastGame(),"a1","Pawn","WHITE");
        boardDao.save(gameDao.findLastGame(),"a2","Pawn","WHITE");
        //when
        boardDao.delete(gameDao.findLastGame());
        //then
    }

    @Test
    @DisplayName("게임 id로 기물을 찾는다.")
    void findByGameId() throws SQLException {
        //given
        gameDao.save(ChessBoardFactory.initBoard());
        boardDao.save(gameDao.findLastGame(),"a1","Pawn","WHITE");
        boardDao.save(gameDao.findLastGame(),"a2","Pawn","WHITE");
        //when
        List<PieceDto> actual = boardDao.findByGameId(gameDao.findLastGame());
        //then
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getGameId()).isEqualTo(1);
        assertThat(actual.get(0).getPosition()).isEqualTo("a1");
        assertThat(actual.get(0).getPiece()).isEqualTo("Pawn");
    }


    @AfterEach
    void end() throws SQLException {
        connection.rollback();
    }
}