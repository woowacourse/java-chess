package chess.domain.dao;

import chess.domain.ChessBoard;
import chess.domain.dto.*;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessDaoTest {
    private ChessDao chessDao;

    @BeforeEach
    void setUp() {
        chessDao = new ChessDao();
    }

    @Test
    void connection() {
        Connection connection = chessDao.getConnection();
        assertNotNull(connection);
    }

    @Test
    void addChessRoom() throws SQLException {
        ChessBoardDto chessBoardDto = new ChessBoardDto(ChessBoard.generate().getChessBoard());
        ChessRoomDto chessRoomDto = new ChessRoomDto(chessBoardDto.getChessBoard(), Color.WHITE.name(), 38, 38);
        chessDao.addChessRoom(chessRoomDto);
    }

//    @Test
//    void findChessRoomById() throws SQLException {
//        ChessRoomDto chessRoomDto = chessDao.findChessRoomById(19);
////      1
//    }
}
