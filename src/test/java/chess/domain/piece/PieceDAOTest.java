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
        final Chess chess = chessDAO.findChessById(chessId);
        final ChessDTO chessDTO = new ChessDTO(chess);
        assertThat(chessDTO.getBoardDTO()
                           .getPieceDTOS()).size()
                                           .isEqualTo(0);

        // when
        final Chess newChess = Chess.createWithEmptyBoard()
                                    .start();
        final BoardDTO boardDTO = BoardDTO.from(newChess);
        pieceDAO.insert(chessId, boardDTO);

        // then
        final Chess insertedChess = chessDAO.findChessById(chessId);
        final ChessDTO insertedChessDTO = new ChessDTO(insertedChess);
        assertThat(insertedChessDTO.getBoardDTO()
                                   .getPieceDTOS()).size()
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
        final Chess chess = chessDAO.findChessById(chessId);
        final ChessDTO chessDTO = new ChessDTO(chess);
        for (PieceDTO pieceDTO : chessDTO.getBoardDTO()
                                         .getPieceDTOS()) {
            if (pieceDTO.getPosition()
                        .equals(source)) {
                assertThat(pieceDTO.getPosition()).isEqualTo(source);
                assertThat(pieceDTO.getColor()).isEqualTo(Color.BLANK.name());
                assertThat(pieceDTO.getName()).isEqualTo(Color.BLANK.name());
            }

            if (pieceDTO.getPosition()
                        .equals(target)) {
                assertThat(pieceDTO.getPosition()).isEqualTo(target);
                assertThat(pieceDTO.getColor()).isEqualTo(Color.WHITE.name());
                assertThat(pieceDTO.getName()).isEqualTo(Pawn.WHITE_INSTANCE.getName());
            }
        }
    }
}
