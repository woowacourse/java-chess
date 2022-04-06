package chess.dao;

import chess.domain.game.Board;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Pawn;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class PositionDaoTest {

    private final PositionDao dao = new PositionDao(new ChessConnectionManager());
    private final BoardDao boardDao = new BoardDao(new ChessConnectionManager());
    private final PieceDao pieceDao = new PieceDao(new ChessConnectionManager());
    private int boardId;
    private Position Position;

    @BeforeEach
    void setup() {
        final Board board = boardDao.save(new Board("코린파이팅"));
        this.boardId = board.getId();
        Position Position = dao.save(new Position(Column.A, Row.TWO, boardId));
        final Piece piece = pieceDao.save(new Piece(Color.WHITE, new Pawn(), Position.getId()));
    }

    @AfterEach
    void setDown() {
        boardDao.deleteAll();
    }

    @Test
    void save() {
        final Position Position = dao.save(new Position(Column.B, Row.TWO, boardId));
        assertAll(
                () -> assertThat(Position.getColumn()).isEqualTo(Column.B),
                () -> assertThat(Position.getRow()).isEqualTo(Row.TWO)
        );
    }

    @Test
    void findByColumnAndRowAndBoardId() {
        Position Position = dao.getByColumnAndRowAndBoardId(Column.A, Row.TWO, boardId);
        assertAll(
                () -> assertThat(Position.getColumn()).isEqualTo(Column.A),
                () -> assertThat(Position.getRow()).isEqualTo(Row.TWO)
        );
    }

    @Test
    void findAllPositionsAndPieces() {
        Map<Position, Piece> all = dao.findAllPositionsAndPieces(boardId);

        for(Position position : all.keySet()) {
            assertThat(all.get(position).getType()).isInstanceOf(Pawn.class);
        }
    }
}
