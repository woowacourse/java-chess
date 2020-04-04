package chess.domain.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PositionDAOTest {

    private BoardDAO boardDAO;
    private PositionDAO positionDAO;

    @BeforeEach
    void setUp() {
        boardDAO = new BoardDAO();
        positionDAO = new PositionDAO();
    }

    @Test
    void addGamePiece() throws Exception {
        BoardDAOTest.deleteBoard(boardDAO);

        int boardId = BoardDAOTest.addBoard(boardDAO);
        List<Position> pieces = Arrays.asList(Position.from("d5"),
                Position.from("e6"), Position.from("a1"));
        positionDAO.addPosition(boardId, pieces.get(0));
        positionDAO.addPosition(boardId, pieces.get(1));
        positionDAO.addPosition(boardId, pieces.get(2));

        assertThat(positionDAO.findPositionsByBoardId(boardId)).isEqualTo(pieces);
    }
}