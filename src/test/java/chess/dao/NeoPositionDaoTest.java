package chess.dao;

import chess.domain.game.NeoBoard;
import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Pawn;
import chess.domain.position.Column;
import chess.domain.position.NeoPosition;
import chess.domain.position.Row;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class NeoPositionDaoTest {

    private final NeoPositionDao dao = new NeoPositionDao(new ChessConnectionManager());
    private final NeoBoardDao neoBoardDao = new NeoBoardDao(new ChessConnectionManager());
    private final NeoPieceDao neoPieceDao = new NeoPieceDao(new ChessConnectionManager());
    private int neoBoardId;

    @BeforeEach
    void setup() {
        final NeoBoard neoBoard = neoBoardDao.save(new NeoBoard("코린파이팅"));
        this.neoBoardId = neoBoard.getId();
        final NeoPosition neoPosition = dao.save(new NeoPosition(Column.A, Row.TWO, neoBoardId));
        final NeoPiece neoPiece = neoPieceDao.save(new NeoPiece(new Pawn(), Color.WHITE, neoPosition.getId()));
    }

    @AfterEach
    void setDown() {
        neoBoardDao.deleteAll();
    }

    @Test
    void save() {
        final NeoPosition neoPosition = dao.save(new NeoPosition(Column.B, Row.TWO, neoBoardId));
        assertAll(
                () -> assertThat(neoPosition.getColumn()).isEqualTo(Column.B),
                () -> assertThat(neoPosition.getRow()).isEqualTo(Row.TWO)
        );
    }

    @Test
    void findByColumnAndRowAndBoardId() {
        NeoPosition neoPosition = dao.findByColumnAndRowAndBoardId(Column.A, Row.TWO, neoBoardId);
        assertAll(
                () -> assertThat(neoPosition.getColumn()).isEqualTo(Column.A),
                () -> assertThat(neoPosition.getRow()).isEqualTo(Row.TWO)
        );
    }

    @Test
    void findAllPositionsAndPieces() {
        Map<NeoPosition, NeoPiece> all = dao.findAllPositionsAndPieces(neoBoardId);
        assertThat(all.size()).isEqualTo(1);
    }
}
