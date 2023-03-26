package chess.database.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.board.Position;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Team;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessDaoTest {

    private final ChessDao chessDao = new ChessDao();

    @Test
    @DisplayName("DB가 잘 연동되었는지 학인하는 테스트")
    void connection() {
        try (final var connection = chessDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("움직임이 일어날 때 source 위치, target 위치, turn을 저장한다.")
    void add_notation_to_database() {
        // given
        Position source = Position.from("a2");
        Position target = Position.from("a4");
        Pawn pawn = new Pawn(Team.WHITE);

        // when + then
        chessDao.addNotation(source, target, pawn);
    }
}