package chess.dao;

import chess.controller.dto.BoardDto;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Piece;
import chess.view.web.PieceSymbolMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardDaoTest {

    private BoardDao dao;

    @BeforeEach
    void init(){
        DBConfig dbConfig = new DBConfig();
        Connection connection = dbConfig.getConnection();
        dao = new BoardDao(connection);
    }

    @DisplayName("Board의 현재 상태가 db에 저장되는지 확인한다.")
    @Test
    void testInsert(){
        Board board = BoardInitializer.initiateBoard();
        BoardDto boardDto = new BoardDto();
        boardDto.setBoard(boardMapping(board));
        try{
            dao.insertBoard(boardDto);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            fail();
        }
    }

    // XXX :: 서비스로 빼기
    private String[][] boardMapping(final Board board){
        String[][] uniCodeBoard = new String[8][8];
        for(Vertical v : Vertical.values()){
            for(Horizontal h : Horizontal.values()){
                Piece piece = board.of(new Position(v,h));
                String uniCode = PieceSymbolMapper.parse(piece.owner(), piece.symbol());
                uniCodeBoard[v.getIndex()-1][h.getIndex()-1] = uniCode;
            }
        }
        return uniCodeBoard;
    }
}