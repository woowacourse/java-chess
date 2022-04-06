package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.dao.PieceDao;
import chess.model.piece.Piece;
import chess.model.piece.pawn.Pawn;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceDaoTest {

    private PieceDao pieceDao;
    @BeforeEach
    void setUp() {
        pieceDao = new PieceDao();
    }

    @Test
    void connection() {
        final Connection connection = pieceDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:Pawn:true", "2:Bishop:true", "3:Rook:true", "4:Knight:true", "5:King:true", "6:Queen:true"
            ,"7:Pawn:false", "8:Bishop:false", "9:Rook:false", "10:Knight:false", "11:King:false", "12:Queen:false", "13:Empty:false"}, delimiter = ':')
    void findById(int id, String pieceType, boolean isBlack) {
        Piece piece = pieceDao.findById(id);
        assertThat(piece.getClass().getSimpleName()).contains(pieceType);
        assertThat(piece.isBlack()).isEqualTo(isBlack);
    }
}