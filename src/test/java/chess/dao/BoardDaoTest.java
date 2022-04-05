package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.dto.BoardDto;
import chess.dto.PieceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDaoTest {

    @Test
    @DisplayName("데이터베이스에 체스판 정보를 저장한다.")
    void save() {
        Board board = new Board(new BasicBoardFactory());

        BoardDao boardDao = new BoardDao();
        boardDao.save(BoardDto.of(1, board));
    }

    @Test
    @DisplayName("데이터베이스에 하나의 체스 칸의 정보를 업데이트한다.")
    void updateOnePosition() {
        BoardDao boardDao = new BoardDao();
        boardDao.updateOnePosition(1, "c7", new PieceDto(new BishopPiece(Color.BLACK)));
    }

    @Test
    @DisplayName("데이터베이스에서 게임 ID를 이용해 체스판 정보를 불러온다.")
    void findByGameId() {
        BoardDao boardDao = new BoardDao();
        BoardDto boardDto = boardDao.findByGameId(1);
        assertThat(boardDto.getBoard().get("c7").toPiece()).isEqualTo(new BishopPiece(Color.BLACK));
    }
}
