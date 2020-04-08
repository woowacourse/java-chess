package chess.domain;

import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MoveStateDAOTest {

    private MoveStateDAO moveStateDAO = new MoveStateDAO();
    private ChessBoard chessBoard = new ChessBoard("player1", Color.WHITE);
    private MoveState moveState = chessBoard.getMoveState();

    @BeforeEach
    public void setup() {
        moveStateDAO = new MoveStateDAO();
    }

    @Test
    public void connection() {
        Connection con = moveStateDAO.getConnection();
        assertNotNull(con);
    }

    @Test
    public void addMoveState() throws Exception {
        moveState.move("move a2 a3", chessBoard);
        moveStateDAO.addMoveState(new MoveStateDTO(moveState));
        moveState.move("move a7 a5", chessBoard);
        moveStateDAO.addMoveState(new MoveStateDTO(moveState));
    }

    @Test
    public void findByPlayerId() throws Exception {
        ResultSet rs = moveStateDAO.findByPlayerId("player1");
        rs.last();
        int size = rs.getRow();
        rs.beforeFirst();
        assertThat(size).isEqualTo(2);
    }


    @Test
    public void deleteMoveStateById() throws Exception {
        moveStateDAO.deleteMoveStateById("player1");
        assertNull(moveStateDAO.findByPlayerId("player1"));
    }

}
