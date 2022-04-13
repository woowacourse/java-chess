package chess.db;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.position.Position;
import chess.domain.state.State;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DAOTest {

    private final BoardDAO boardDAO = new BoardDAO("1");
    private final StateDAO stateDAO = new StateDAO("1");

    @BeforeEach
    void setUp() {
        stateDAO.initializeID();
        stateDAO.initializeColor();
        boardDAO.initializePieces(State.create(Map.of(new Position("a1"), Pawn.createBlack(),
                new Position("b2"), Pawn.createWhite())));
    }

    @AfterEach
    void delete() {
        stateDAO.terminateDB();
    }

    @Test
    void connection_not_null() {
        assertThat(boardDAO.getConnection()).isNotNull();
    }

    @Test
    void saved_test() {
        assertThat(stateDAO.findAllUsers()).isEqualTo("1");
    }

    @Test
    void saved_color() {
        assertThat(stateDAO.findColor()).isEqualTo(Color.WHITE);
    }

    @Test
    void find_all_pieces() {
        assertThat(boardDAO.findAllPieces().size()).isEqualTo(2);
    }

    @Test
    void saved_convert_color() {
        stateDAO.convertColor(Color.BLACK);
        assertThat(stateDAO.findColor()).isEqualTo(Color.BLACK);
    }

    @Test
    void delete_one_piece() {
        boardDAO.delete(new Position("a1"));
        assertThat(boardDAO.findAllPieces().size()).isEqualTo(1);
    }
}
