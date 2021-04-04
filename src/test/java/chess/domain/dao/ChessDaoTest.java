package chess.domain.dao;

import chess.domain.ChessBoard;
import chess.domain.dto.*;
import chess.domain.piece.info.Color;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void addChessRoom() throws SQLException {
        ChessBoardDto chessBoardDto = new ChessBoardDto(ChessBoard.generate().getChessBoard());
        ChessRoomDto chessRoomDto = new ChessRoomDto(chessBoardDto.getChessBoard(), Color.WHITE.name(), 38, 38);
        chessDao.addChessRoom(chessRoomDto);
    }

    @Test
    @Order(2)
    void findChessRoomByNo() throws SQLException {
        ChessBoardDto chessBoardDto = new ChessBoardDto(ChessBoard.generate().getChessBoard());
        ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(1);
        assertThat(chessRoomDto).isEqualTo(new ChessRoomDto(1, chessBoardDto.getChessBoard(), Color.WHITE.name(), 38, 38));
    }

    @Test
    @Order(3)
    void updateChessRoom() throws SQLException {
        ChessRoomDto chessRoomDto = new ChessRoomDto(Color.BLACK.name(), 23, 23);
        PieceDto pieceDto = new PieceDto("p", "WHITE");
        PositionDto source = new PositionDto("g2");
        PositionDto target = new PositionDto("g4");
        assertThat(chessDao.updateChessRoom(chessRoomDto, pieceDto, source, target)).isEqualTo(1);
    }

    @Test
    @Order(4)
    void deleteChessRoomByRoomNo() throws SQLException {
        assertThat(chessDao.deleteChessRoomByRoomNo(1)).isEqualTo(1);
    }
}
