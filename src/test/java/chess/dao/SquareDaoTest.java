package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.piece.Piece;
import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareDaoTest {

    @DisplayName("데이터가 저장되는지 확인한다.")
    @Test()
    void save() {
        SquareDao squareDao = new SquareDao();
        squareDao.delete();
        squareDao.save(Position.from("a1"), Piece.getPiece("BLACK_KING"));

        assertThat(squareDao.find().get("a1")).isNotNull();
    }

    @DisplayName("데이터가 삭제되는지 확인한다.")
    @Test()
    void delete() {
        SquareDao squareDao = new SquareDao();
        squareDao.save(Position.from("a1"), Piece.getPiece("BLACK_KING"));
        squareDao.delete();

        assertThat(squareDao.find().size()).isEqualTo(0);
    }

    @DisplayName("데이터가 최신화 되는지 확인한다.")
    @Test()
    void update() {
        SquareDao squareDao = new SquareDao();
        squareDao.save(Position.from("a1"), Piece.getPiece("BLACK_KING"));
        squareDao.update("a1", Piece.getPiece("BLACK_PAWN"));

        assertThat(squareDao.find().get("a1")).isEqualTo("BLACK_PAWN");
    }
}
