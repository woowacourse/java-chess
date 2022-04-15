package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import chess.model.status.Running;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessSquareDaoTest {

    private final ChessSquareDao dao = new ChessSquareDao(new ConnectionManager());
    private final ChessBoardDao chessBoardDao = new ChessBoardDao(new ConnectionManager());
    private int boardId;
    private Square square;

    @BeforeEach
    void setup() {
        final Board board = chessBoardDao.save(new Board(new Running()));
        this.boardId = board.getId();
        this.square = dao.save(new Square(File.A, Rank.TWO, boardId));
    }

    @AfterEach
    void setDown() {
        chessBoardDao.deleteAll();
    }

    @Test
    void save() {
        final Square square = dao.save(new Square(File.B, Rank.TWO, boardId));

        assertAll(
                () -> assertThat(square.getFile()).isEqualTo(File.B),
                () -> assertThat(square.getRank()).isEqualTo(Rank.TWO)
        );
    }

    @Test
    void getBySquareTest() {
        final Square square = dao.getBySquareAndBoardId(new Square(File.A, Rank.TWO), boardId);

        assertAll(
                () -> assertThat(square.getFile()).isEqualTo(File.A),
                () -> assertThat(square.getRank()).isEqualTo(Rank.TWO)
        );
    }

    @Test
    void saveAllSquares() {
        int saveCount = dao.saveAllSquare(boardId);

        assertThat(saveCount).isEqualTo(64);
    }

    @Test
    void getSquareIdBySquare() {
        int squareId = dao.getSquareIdBySquare(new Square(File.A, Rank.TWO), boardId);

        assertThat(squareId).isEqualTo(square.getId());
    }

    @Test
    void findAllSquaresAndPieces() {
        Map<Square, Piece> all = dao.findAllSquaresAndPieces(boardId);

        for (Square Square : all.keySet()) {
            assertThat(all.get(Square).name()).isEqualTo("p");
        }
    }
}
