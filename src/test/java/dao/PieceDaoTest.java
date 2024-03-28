package dao;

import static org.assertj.core.api.Assertions.assertThat;
import static domain.Fixture.Positions.*;

import domain.game.PieceFactory;
import domain.game.PieceType;
import domain.position.Position;
import dto.PieceDto;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    private final GameDao gameDao = GameDao.getInstance();
    private final PieceDao pieceDao = PieceDao.getInstance();

    @BeforeEach
    void setUp() {
        pieceDao.removeAllPieces();
        gameDao.removeAllGames();
        int gameId1 = gameDao.addGame();
        int gameId2 = gameDao.addGame();
        pieceDao.addPiece(PieceDto.of(F6, PieceFactory.create(PieceType.WHITE_QUEEN)), gameId1);
        pieceDao.addPiece(PieceDto.of(D4, PieceFactory.create(PieceType.BLACK_ROOK)), gameId2);
        pieceDao.addPiece(PieceDto.of(F3, PieceFactory.create(PieceType.WHITE_BISHOP)), gameId2);
        pieceDao.addPiece(PieceDto.of(A2, PieceFactory.create(PieceType.WHITE_PAWN)), gameId2);
    }

    @AfterEach
    void tearDown() {
        pieceDao.removeAllPieces();
        gameDao.removeAllGames();
    }

    @Test
    @DisplayName("Piece 하나를 DB에 저장할 수 있다.")
    void addPieceTest() {
        Position position = A1;
        PieceDto pieceDto = PieceDto.of(position, PieceFactory.create(PieceType.BLACK_PAWN));
        int gameId = gameDao.addGame();
        pieceDao.addPiece(pieceDto, gameId);

        PieceDto result = pieceDao.findPiece(position.columnIndex(), position.rowIndex(), gameId);

        assertThat(result).isEqualTo(pieceDto);
    }

    @Test
    @DisplayName("저장된 모든 Piece 정보를 가져올 수 있다.")
    void findAllPieceTest() {
        List<PieceDto> result = pieceDao.findAllPieces(2);

        List<PieceDto> expected = List.of(
                PieceDto.of(D4, PieceFactory.create(PieceType.BLACK_ROOK)),
                PieceDto.of(F3, PieceFactory.create(PieceType.WHITE_BISHOP)),
                PieceDto.of(A2, PieceFactory.create(PieceType.WHITE_PAWN))
        );

        assertThat(result).containsAll(expected);
    }

    @Test
    @DisplayName("저장된 모든 Piece 정보를 삭제할 수 있다.")
    void deleteAllPiecesTest() {
        pieceDao.removeAllPieces();

        assertThat(pieceDao.tupleCount()).isZero();
    }
}
