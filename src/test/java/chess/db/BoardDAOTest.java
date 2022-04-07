package chess.db;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class BoardDAOTest {

    private static final FakeBoardDAO fakeBoardDAO = new FakeBoardDAO("123");

    @Test
    void insert_test() {
        fakeBoardDAO.insert(new Position("a2"), Pawn.createBlack());
        assertThat(fakeBoardDAO.isInDB("a2 123")).isTrue();
    }

    @Test
    void delete_test() {
        fakeBoardDAO.delete(new Position("a2"));
        assertThat(fakeBoardDAO.isInDB("a2 123")).isFalse();
    }

    @Test
    void find_all_test() {
        fakeBoardDAO.insert(new Position("a2"), Pawn.createBlack());
        fakeBoardDAO.insert(new Position("a3"), Pawn.createBlack());
        fakeBoardDAO.insert(new Position("a4"), Pawn.createBlack());
        Map<Position, Piece> all = fakeBoardDAO.findAllPieces();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void delete_by_id_test() {
        fakeBoardDAO.deleteById("123");
        assertThat(fakeBoardDAO.getSize()).isEqualTo(0);
    }
}
