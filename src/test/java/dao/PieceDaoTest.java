package dao;

import static org.assertj.core.api.Assertions.assertThat;
import static domain.Fixture.Positions.*;

import domain.game.PieceFactory;
import domain.game.PieceType;
import domain.position.Position;
import dto.PieceDto;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private final PieceDao pieceDao = PieceDao.getInstance();

    @BeforeEach
    void setUp() {
        final var query = "TRUNCATE TABLE piece";
        try (final var connection = pieceDao.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

//        pieceDao.addPiece(PieceDto.of(F6, PieceFactory.create(PieceType.WHITE_QUEEN)));
//        pieceDao.addPiece(PieceDto.of(D4, PieceFactory.create(PieceType.BLACK_ROOK)));
    }

    @Test
    @DisplayName("DB 커넥션 테스트")
    void connectionTest() throws SQLException {
        try (final var connection = pieceDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

//    @Test
//    @DisplayName("Piece 하나를 DB에 저장할 수 있다.")
//    void addPieceTest() {
//        Position position = A1;
//        PieceDto pieceDto = PieceDto.of(position, PieceFactory.create(PieceType.BLACK_PAWN));
//        pieceDao.addPiece(pieceDto);
//
//        PieceDto result = pieceDao.findPiece(position.columnIndex(), position.rowIndex());
//
//        assertThat(result).isEqualTo(pieceDto);
//    }

//    @Test
//    @DisplayName("저장된 모든 Piece 정보를 삭제할 수 있다.")
//    void deleteAllPiecesTest() {
//        Position position = B2;
//        PieceDto pieceDto = PieceDto.of(position, PieceFactory.create(PieceType.BLACK_PAWN));
//        pieceDao.addPiece(pieceDto);
//        pieceDao.removeAllPieces();
//
//        assertThat(pieceDao.tupleCount()).isZero();
//    }

}
