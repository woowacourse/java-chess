package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDaoTest {

    private PieceDao dao;

    @BeforeEach
    void init() {
        dao = new PieceDao();
    }

    @Test
    @DisplayName("저장 확인")
    void save() {
        dao.save(BoardFactory.createBoard().getValue());
    }

    @Test
    @DisplayName("이름으로 탐색 확인")
    void findByName() {
        dao.save(BoardFactory.createBoard().getValue());
        Map<Position, Piece> chess = dao.findAllByGameName("chess");
        assertThat(chess).containsAllEntriesOf(BoardFactory.createBoard().getValue());
    }

    @Test
    @DisplayName("피스 정상 이동 확인")
    void movePiece() {
        //given
        Board board = BoardFactory.createBoard();
        board.move("b2", "b4", Color.WHITE);

        //when
        dao.movePiece(board.getValue(), "chess");
        Map<Position, Piece> chess = dao.findAllByGameName("chess");

        //then
        assertThat(chess).containsAllEntriesOf(board.getValue());
    }

    @AfterEach
    void delete(){
        dao.deleteByGameName("chess");
    }
}