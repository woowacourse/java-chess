package chess.domain.dao;

import chess.domain.ChessBoard;
import chess.domain.dto.*;
import chess.domain.piece.info.Color;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChessDaoTest {
    private ChessDao chessDao;

    @BeforeEach
    void setUp() throws SQLException {
        chessDao = new ChessDao();
        addChessRoom();
    }

    @Test
    void connection() {
        Connection connection = chessDao.getConnection();
        assertNotNull(connection);
    }

    @Test
    void addChessRoom() throws SQLException {
        deleteChessRoomByRoomNo();
        ChessBoardDto chessBoardDto = new ChessBoardDto(ChessBoard.generate().getChessBoard());
        chessDao.addChessRoom(chessBoardDto.getChessBoard(), Color.WHITE.name(), 38, 38);
    }

    @Test
    void findChessRoomByNo() throws SQLException {
        ChessBoardDto chessBoardDto = new ChessBoardDto(ChessBoard.generate().getChessBoard());
        ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(1);
        assertThat(chessRoomDto).isEqualTo(new ChessRoomDto(1, chessBoardDto.getChessBoard(), Color.WHITE.name(), 38, 38));
    }

    @Test
    void updateChessRoom() throws SQLException {
        MoveResultDto moveResultDto = new MoveResultDto(Color.BLACK.name(), 23, 23);
        PieceDto pieceDto = new PieceDto("p", "WHITE");
        PositionDto source = new PositionDto("g2");
        PositionDto target = new PositionDto("g4");
        chessDao.updateChessRoom(moveResultDto, pieceDto, source, target);
    }

    @Test
    void deleteChessRoomByRoomNo() throws SQLException {
        chessDao.deleteChessRoomByRoomNo(1);
    }
}
