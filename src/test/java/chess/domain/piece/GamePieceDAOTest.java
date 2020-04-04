package chess.domain.piece;

import chess.domain.board.BoardDAO;
import chess.domain.board.BoardDAOTest;
import chess.domain.board.Position;
import chess.domain.board.PositionDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GamePieceDAOTest {

    private BoardDAO boardDAO;
    private PositionDAO positionDAO;
    private GamePieceDAO gamePieceDAO;

    @BeforeEach
    void setUp() {
        boardDAO = new BoardDAO();
        positionDAO = new PositionDAO();
        gamePieceDAO = new GamePieceDAO();
    }

    @Test
    void addGamePiece() throws Exception {
        BoardDAOTest.deleteBoard(boardDAO);

        int boardId = BoardDAOTest.addBoard(boardDAO);
        int positionId = positionDAO.addPosition(boardId, Position.from("d5"));

        GamePiece piece = ChessPiece.BLACK_KNIGHT.getGamePiece();
        gamePieceDAO.addGamePiece(positionId, piece);

        GamePiece changedPiece = ChessPiece.WHITE_KNIGHT.getGamePiece();
        gamePieceDAO.updatePieceNameByPositionId(positionId, changedPiece.getName());

        assertThat(gamePieceDAO.findPieceByPositionId(positionId)).isEqualTo(changedPiece);
    }
}