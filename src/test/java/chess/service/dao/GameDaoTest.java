package chess.service.dao;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.player.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameDaoTest {

    private GameDao dao;
    @BeforeEach
    void init(){
        DBConfig dbConfig = new DBConfig();
        Connection connection = dbConfig.getConnection();
        dao = new GameDao(connection);
    }

    @DisplayName("Game의 현재 상태가 db에 저장되는지 확인한다.")
    @Test
    void testInsert(){
        Board board = BoardInitializer.initiateBoard();
        try{
            dao.save(0, Turn.BLACK, board);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            fail();
        }
    }
}