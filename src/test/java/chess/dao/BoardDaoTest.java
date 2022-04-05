package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.db.MySqlConnector;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.PawnPiece;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    private BoardDao boardDao;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = MySqlConnector.getConnection();
        connection.setAutoCommit(false);
        boardDao = new BoardDao(connection);
    }

    @Test
    @DisplayName("데이터베이스에 하나의 체스 칸의 정보를 업데이트한다.")
    void updateOnePosition() throws SQLException {
        boardDao.updateOnePosition(7, "c7", new PieceDto(new BishopPiece(Color.BLACK)));

        assertThat(boardDao.findByGameId(7).getBoard().get("c7").toPiece()).isEqualTo(new BishopPiece(Color.BLACK));
        connection.rollback();
    }

    @Test
    @DisplayName("데이터베이스에서 게임 ID를 이용해 체스판 정보를 불러온다.")
    void findByGameId() {
        BoardDto boardDto = boardDao.findByGameId(7);

        assertThat(boardDto.getBoard().get("c7").toPiece()).isEqualTo(new PawnPiece(Color.BLACK));
    }
}
