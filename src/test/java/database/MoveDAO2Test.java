package database;

import chess.domain.position.Position;
import chess.history.Move;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveDAO2Test {
    
    private static final MoveDAO2 MOVE_DAO = new MoveDAO2("move_test");
    
    @Test
    @DisplayName("이동 기록을 추가합니다.")
    void addMove() {
        Move move = Move.create(Position.from("a2"), Position.from("a3"));
        MOVE_DAO.addMove(move, 1);
    }
    
    @Test
    @DisplayName("이동 기록을 가져옵니다.")
    void fetchMoves() {
        List<Move> moves = MOVE_DAO.fetchMoves(1);
        Assertions.assertThat(moves).hasSize(1);
        Move move = moves.get(0);
        Assertions.assertThat(move.getFromLabel()).isEqualTo("a2");
        Assertions.assertThat(move.getToLabel()).isEqualTo("a3");
    }
}