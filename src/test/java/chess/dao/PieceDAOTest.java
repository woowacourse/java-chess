package chess.dao;

import chess.db.ConnectionManager;
import chess.dto.PieceDTO;
import chess.model.piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDAOTest {
    private static final ConnectionManager CONNECTION_MANAGER = ConnectionManager.getTest();
    private final static List<PieceDTO> SAVED_PIECES = List.of(
            new PieceDTO(3, 2, Type.BLACK_PAWN.name()),
            new PieceDTO(4, 4, Type.WHITE_KING.name()),
            new PieceDTO(7, 8, Type.BLACK_KING.name())
    );

    private final PieceDAO pieceDAO = new PieceDAO(CONNECTION_MANAGER);

    @BeforeEach
    void initPieceTable() {
        try (Connection connection = CONNECTION_MANAGER.getConnection()) {
            PreparedStatement truncateStatement = connection.prepareStatement("TRUNCATE TABLE pieces");
            truncateStatement.executeUpdate();
            pieceDAO.saveAll(SAVED_PIECES);
        } catch (final SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void 입력된_데이터_전체를_추가한다() {
        List<PieceDTO> pieceDTOs = List.of(
                new PieceDTO(7, 7, Type.WHITE_ROOK.name()),
                new PieceDTO(3, 4, Type.WHITE_KNIGHT.name())
        );
        pieceDAO.saveAll(pieceDTOs);
        assertThat(pieceDAO.findAll()).containsAll(pieceDTOs);
    }

    @Test
    void 데이터_전체를_삭제한다() {
        pieceDAO.deleteAll();
        assertThat(pieceDAO.findAll().isEmpty()).isTrue();
    }

    @Test
    void 데이터_전체를_조회한다() {
        assertThat(pieceDAO.findAll()).containsAll(SAVED_PIECES);
    }
}
