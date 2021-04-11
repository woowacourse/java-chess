package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.chess.ChessDTO;
import chess.domain.chess.Color;
import chess.domain.position.MovePosition;

public class PieceDAOTest {

    private final ChessDAO chessDAO = new ChessDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final long chessId = chessDAO.insert();

    @DisplayName("초기 기물 삽입 테스트")
    @Test
    void insertTest() {

        // given
        final ChessDTO chessDTO = chessDAO.findChessById(chessId);
        assertThat(chessDTO.getPieceDTOS()).size()
                                           .isEqualTo(0);

        // when
        final Chess chess = Chess.createWithEmptyBoard()
                                 .start();
        final BoardDTO boardDTO = BoardDTO.from(chess);
        pieceDAO.insert(chessId, boardDTO);

        // then
        final ChessDTO insertedChessDTO = chessDAO.findChessById(chessId);
        assertThat(insertedChessDTO.getPieceDTOS()).size()
                                                   .isEqualTo(64);
    }

    @DisplayName("기물 이동 테스트")
    @Test
    void moveTest() {

        // given
        final String source = "a2";
        final String target = "a4";
        MovePosition movePosition = new MovePosition(source, target);

        // when
        pieceDAO.move(chessId, movePosition);

        // then
        final ChessDTO chessDTO = chessDAO.findChessById(chessId);
        for (PieceDTO pieceDTO : chessDTO.getPieceDTOS()) {
            if (pieceDTO.getPosition().equals(source)) {
                assertThat(pieceDTO.getPosition()).isEqualTo(source);
                assertThat(pieceDTO.getColor()).isEqualTo(Color.BLANK.name());
                assertThat(pieceDTO.getName()).isEqualTo(Color.BLANK.name());
            }

            if (pieceDTO.getPosition().equals(target)) {
                assertThat(pieceDTO.getPosition()).isEqualTo(target);
                assertThat(pieceDTO.getColor()).isEqualTo(Color.WHITE.name());
                assertThat(pieceDTO.getName()).isEqualTo(Pawn.WHITE_INSTANCE.getName());
            }
        }
    }
}
