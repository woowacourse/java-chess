package chess.dao;

import chess.controller.dto.BoardDto;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameDaoTest {

    private GameDao dao;
    private BoardParser boardParser = new BoardParser();
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
        BoardDto boardDto = new BoardDto();
        boardDto.setBoard(boardParser.parseBoardAsUnicode(board));
        try{
            dao.addGameStatus(0,"black", boardDto);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    @DisplayName("게임 Room id에 해당하는 가장 최신의 게임 정보를 가져온다.")
    @Test
    void testSelect() {
        try{
            ResultSet rs = dao.selectLastGameStatus(0);
            rs.next();
            assertThat(rs.getString("turn")).isEqualTo("black");
        }catch (SQLException e){
            System.out.println(e.getMessage());
            fail();
        }
    }
}