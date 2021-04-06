package chess.domain.piece;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.position.MovePositionDTO;

public class PieceDAOTest {

    private final ChessDAO chessDAO = new ChessDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private Long chessId;

    @BeforeEach
    void setUp() throws SQLException {
        Optional<Long> optionalPreviousChessId = chessDAO.findChessId();
        if (optionalPreviousChessId.isPresent()) {
            final Long previousChessId = optionalPreviousChessId.get();
            pieceDAO.delete(previousChessId);
            chessDAO.delete(previousChessId);
        }

        chessDAO.insert();

        chessId = chessDAO.findChessId().get();
        final Chess chess = Chess.createWithEmptyBoard()
                                 .start();
        final BoardDTO boardDTO = BoardDTO.from(chess);
        pieceDAO.insert(chessId, boardDTO);
    }

    @DisplayName("체스의 기물들 가져오기 테스트")
    @Test
    void findPiecesByChessIdTest() throws SQLException {

        // when
        final List<PieceDTO> pieces = pieceDAO.findPiecesByChessId(chessId);

        // then
        assertThat(pieces).size().isEqualTo(64);
    }

    @DisplayName("체스 초기 위치의 기물들 삽입 테스트")
    @Test
    void insertTest() throws SQLException {

        // then
        final List<PieceDTO> pieces = pieceDAO.findPiecesByChessId(chessId);
        assertThat(pieces).size().isEqualTo(64);
    }

    @DisplayName("기물 이동 테스트")
    @Test
    void moveTest() throws SQLException {

        // given
        MovePositionDTO movePositionDTO = new MovePositionDTO("a2", "a4");

        // when
        pieceDAO.move(chessId, movePositionDTO);

        // then
        final List<PieceDTO> pieces = pieceDAO.findPiecesByChessId(chessId);
        for (PieceDTO piece : pieces) {
            if (piece.getPosition().equals("a2")) {
                assertThat(piece.getName()).isEqualTo("BLANK");
            }

            if (piece.getPosition().equals("a4")) {
                assertThat(piece.getName()).isEqualTo("PAWN");
            }
        }
    }

    @DisplayName("체스의 기물들 삭제 테스트")
    @Test
    void deleteTest() throws SQLException {

        // when
        pieceDAO.delete(chessId);

        // then
        final List<PieceDTO> pieces = pieceDAO.findPiecesByChessId(chessId);
        assertThat(pieces).isEmpty();
    }
}
