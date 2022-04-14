package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.fixture.MockBoardDao;
import chess.dao.fixture.MockPieceDao;
import chess.dto.PieceDto;
import chess.game.Board;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;

class PieceDaoTest {

    @Test
    @DisplayName("피스들을 저장한다.")
    void saveBoardTurn() {
        final PieceDao pieceDao = new MockPieceDao();
        final Bishop bishop = new Bishop(Color.WHITE);
        final Position position = Position.of("a2");
        final PieceDto pieceDto = PieceDto.toDto(bishop, position);

        pieceDao.save(1, pieceDto);
    }

    @Test
    void saveAllPiecesOnMockBoard() {
        final MockPieceDao pieceDao = new MockPieceDao();
        final BoardDao boardDao = new MockBoardDao();
        final Map<Position, Piece> value = Board.create().getValue();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, value);

        assertThat(pieceDao.getPiece().size()).isEqualTo(value.size());
    }

    @Test
    void findAllByBoardIdOnMockBoard() {
        final MockPieceDao pieceDao = new MockPieceDao();
        final BoardDao boardDao = new MockBoardDao();
        final Map<Position, Piece> value = Board.create().getValue();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, value);

        assertThat(pieceDao.findAllByBoardId(boardId).size()).isEqualTo(value.size());
    }

    @Test
    void deletePieceByPosition() {
        final MockPieceDao pieceDao = new MockPieceDao();
        final BoardDao boardDao = new MockBoardDao();
        final Map<Position, Piece> value = Board.create().getValue();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, value);

        pieceDao.deletePieceByPosition(boardId, "12");
        final int expected = value.size() - 1;

        assertThat(pieceDao.findAllByBoardId(boardId).size()).isEqualTo(expected);
    }

    @Test
    void updatePieceByPosition() {
        final MockPieceDao pieceDao = new MockPieceDao();
        final BoardDao boardDao = new MockBoardDao();
        final Map<Position, Piece> value = Board.create().getValue();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, value);
        final Piece pieceKing = new King(Color.BLACK);
        final PieceDto pieceDto = PieceDto.toDto(pieceKing, Position.of("e7"));

        pieceDao.deletePieceByPosition(boardId, "57");
        pieceDao.updatePieceByPosition(boardId, "58", pieceDto);

        final PieceDto resultPieceDto = pieceDao.getPieceDtoByKey("57");
        assertThat(resultPieceDto.getName()).isEqualTo("BLACK-KING");
    }

    @Test
    void deleteAllByBoardIdOnMock() {
        final MockPieceDao pieceDao = new MockPieceDao();
        final BoardDao boardDao = new MockBoardDao();
        final Map<Position, Piece> value = Board.create().getValue();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, value);
        pieceDao.deleteAllById(boardId);

        assertThat(pieceDao.getPiece().size()).isEqualTo(0);
    }

    @Test
    void isExistOnMock() {
        final PieceDao pieceDao = new MockPieceDao();
        assertThat(pieceDao.isExist()).isFalse();
    }

    @Test
    void loadMockBoard() {
        final MockPieceDao pieceDao = new MockPieceDao();
        final BoardDao boardDao = new MockBoardDao();
        final Map<Position, Piece> value = Board.create().getValue();
        boardDao.save(Color.WHITE);
        final int boardId = boardDao.findLastlyUsedBoard();
        pieceDao.saveAll(boardId, value);
        System.out.println(pieceDao.getPiece().size());

        assertThat(pieceDao.load().size()).isEqualTo(value.size());
    }
}
