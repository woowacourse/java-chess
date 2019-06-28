package chess.dao;

import chess.domain.board.Tile;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.dto.ChessBoardDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessBoardDaoTest {
    ChessBoardDao chessBoardDAO;
    Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = DBUtil.getConnection();
        connection.setAutoCommit(false);
        chessBoardDAO = ChessBoardDao.getInstance();
    }

    //에러 발생 (해당 게임ID 값이 chess_turn 테이블에 존재하지 않아 외래키가 없음)
    @Test
    void insertTest1() throws Exception {
        ChessBoardDto chessBoardDTO = new ChessBoardDto(
                new HashMap<Tile, Piece>() {{
                    put(Tile.of("a1"), PieceType.KING.generate(PieceColor.WHITE));
                }}
        );
        assertThrows(SQLException.class, () -> {
            chessBoardDAO.insertChessBoard(1, chessBoardDTO);
        });
    }

    //정상 실행 (외래키 존재)
    @Test
    void insertTest2() throws Exception {
        ChessTurnDao chessTurnDAO = ChessTurnDao.getInstance();
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int id = chessTurnDAO.selectMaxGameId();

        ChessBoardDto chessBoardDTO = new ChessBoardDto(
                new HashMap<Tile, Piece>() {{
                    put(Tile.of("a1"), PieceType.KING.generate(PieceColor.WHITE));
                }}
        );

        assertDoesNotThrow(() -> {
            chessBoardDAO.insertChessBoard(id, chessBoardDTO);
        });
    }

    //해당 체스보드 정보 존재하는 경우
    @Test
    public void selectTest1() throws Exception {
        ChessTurnDao chessTurnDAO = ChessTurnDao.getInstance();
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int id = chessTurnDAO.selectMaxGameId();

        ChessBoardDto chessBoardDTO = new ChessBoardDto(
                new HashMap<Tile, Piece>() {{
                    put(Tile.of("a1"), PieceType.KING.generate(PieceColor.WHITE));
                }}
        );
        chessBoardDAO.insertChessBoard(id, chessBoardDTO);

        assertThat(chessBoardDAO.selectChessBoard(id).getBoard())
                .isEqualTo(chessBoardDTO.getBoard());
    }

    //존재하지 않는 경우
    @Test
    public void selectTest2() throws SQLException {
        ChessTurnDao chessTurnDAO = ChessTurnDao.getInstance();
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int id = chessTurnDAO.selectMaxGameId();

        assertThat(chessBoardDAO.selectChessBoard(id).getBoard()).isEmpty();
    }

    @Test
    public void deleteTest() throws Exception {
        ChessTurnDao chessTurnDAO = ChessTurnDao.getInstance();
        chessTurnDAO.insertChessTurn(PieceColor.BLACK);
        int id = chessTurnDAO.selectMaxGameId();

        ChessBoardDto chessBoardDTO = new ChessBoardDto(
                new HashMap<Tile, Piece>() {{
                    put(Tile.of("a1"), PieceType.KING.generate(PieceColor.WHITE));
                }}
        );
        chessBoardDAO.insertChessBoard(id, chessBoardDTO);
        assertThat(chessBoardDAO.selectChessBoard(id).getBoard()).isEqualTo(chessBoardDTO.getBoard());

        chessBoardDAO.deleteChessBoard(id);
        assertThat(chessBoardDAO.selectChessBoard(id).getBoard()).isEmpty();
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }
}