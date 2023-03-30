package chess.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.piece.Empty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MysqlChessBoardDaoTest {

    private final ChessBoardDao chessBoardDao = new MysqlChessBoardDao();
    private long chessGameId;

    @BeforeEach
    void setUp() {
        ChessGameDao chessGameDao = new MysqlChessGameDao();
        chessGameId = chessGameDao.create();
    }
    @Test
    @DisplayName("체스판을 저장한다.")
    void save() {
        assertDoesNotThrow(
                () -> chessBoardDao.save(chessGameId, Board.init())
        );
    }

    @Test
    @DisplayName("기물의 위치를 수정한다.")
    void update() {
        assertDoesNotThrow(
                () -> chessBoardDao.update(chessGameId, new Position(1, 1), new Empty(Team.EMPTY))
        );
    }
}
