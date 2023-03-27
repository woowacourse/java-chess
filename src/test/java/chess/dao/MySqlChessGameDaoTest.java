package chess.dao;

import chess.database.DatabaseConnector;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MySqlChessGameDaoTest {

    private final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(new DatabaseConnector());

    @Test
    @DisplayName("id로 검색 테스트")
    void find_by_id_test() {
        // a1 white king
        Map<Square, Piece> board = mySqlChessGameDao.findById(1).getBoard().getBoard();

        assertThat(board.get(Square.of(File.A, Rank.ONE))).isInstanceOf(King.class);
        assertThat(board.get(Square.of(File.A, Rank.ONE)).isWhite()).isTrue();
    }
}