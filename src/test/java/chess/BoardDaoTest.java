package chess;

import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dao.BoardDao;
import chess.model.piece.Piece;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardDaoTest {

    @Test
    @DisplayName("체스판이 db에 저장되었는지 확인한다")
    void init() {
        BoardDao boardDao = new BoardDao();
        Board board = BoardFactory.create();
        boardDao.init(board);

        Map<Position, Piece> boardMap = boardDao.findAll();

        assertThat(boardMap.entrySet().size()).isEqualTo(64);
    }
}
