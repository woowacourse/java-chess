package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.dto.BoardDto;
import chess.dto.GameDto;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private GameDao gameDao;
    private BoardDao boardDao;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = MySqlConnector.getConnection();
        connection.setAutoCommit(false);
        gameDao = new GameDao(connection);
        boardDao = new BoardDao(connection);
    }

    @Test
    void save() throws SQLException {
        gameDao.save(new GameDto("테스트 게임", "Started", "black"));
        BoardDto boardDto = BoardDto.of("테스트 게임", new Board(new BasicBoardFactory()));
        boardDao.save(boardDto);
        boardDto = boardDao.findByGameId("테스트 게임");

        assertThat(boardDto.getBoard().get("b1").toPiece()).isEqualTo(new KnightPiece(Color.WHITE));
        connection.rollback();
    }

    @Test
    @DisplayName("데이터베이스에 하나의 체스 칸의 정보를 업데이트한다.")
    void updateOnePosition() throws SQLException {
        boardDao.updateOnePosition("First Game", "a1", new PieceDto(new BishopPiece(Color.BLACK)));

        assertThat(boardDao.findByGameId("First Game").getBoard().get("a1").toPiece()).isEqualTo(
                new BishopPiece(Color.BLACK));
        connection.rollback();
    }

    @Test
    @DisplayName("데이터베이스에서 게임 ID를 이용해 체스판 정보를 불러온다.")
    void findByGameId() {
        BoardDto boardDto = boardDao.findByGameId("First Game");

        assertThat(boardDto.getBoard().get("a1").toPiece()).isEqualTo(new PawnPiece(Color.WHITE));
    }
}
