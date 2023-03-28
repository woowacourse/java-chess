package database;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.Position;
import chess.history.Move;
import java.sql.SQLException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDAOTest {
    
    private final MoveDAO moveDAO = new MoveDAO("moves_test");
    
    @Test
    public void connection() {
        try (final var connection = this.moveDAO.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    @DisplayName("이동 기록을 추가한다.")
    public void addMove() {
        this.moveDAO.resetMoves();
        Move move = Move.create(Position.from("a2"), Position.from("a3"));
        this.moveDAO.addMove(move);
        Move move2 = Move.create(Position.from("a3"), Position.from("a4"));
        this.moveDAO.addMove(move2);
    }
    
    @Test
    @DisplayName("이동 기록을 모두 가져온다.")
    public void fetchMoves() {
        List<Move> moves = this.moveDAO.fetchAllMoves();
        Assertions.assertThat(moves).isNotNull();
        Assertions.assertThat(moves.size()).isEqualTo(2);
    }
}