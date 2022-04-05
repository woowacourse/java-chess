package chess.dao;

import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Map.Entry;
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
        Map<Position, Piece> chess = dao.findAllByGameName("chess");

        for (Entry<Position, Piece> positionPieceEntry : chess.entrySet()) {
            System.out.println(positionPieceEntry.getKey().toString() + positionPieceEntry.getValue().toString());
        }
    }

    @Test
    @DisplayName("이름으로 탐색 확인")
    void movePiece() {
//        dao.movePiece();
//
//        Map<Position, Piece> chess = dao.findAllByGameName("chess");
//
//        for (Entry<Position, Piece> positionPieceEntry : chess.entrySet()) {
//            System.out.println(positionPieceEntry.getKey().toString() + positionPieceEntry.getValue().toString());
//        }
    }

}