package chess.service;

import chess.dao.TestPieceDao;
import chess.domains.board.BoardFactory;
import chess.domains.piece.Piece;
import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessWebServiceTest {

    @DisplayName("이어하기 가능 여부 테스트 : 저장된 정보가 체스 보드 칸 64개를 복구할 수 있는 경우")
    @Test
    void test() throws SQLException {
        Map<Position, Piece> saved = BoardFactory.getBoard();
        TestPieceDao dao = new TestPieceDao("guest", saved);
        ChessWebService webService = new ChessWebService(dao);

        boolean actual = webService.canContinue("guest");

        assertThat(actual).isEqualTo(true);
    }


    @DisplayName("이어하기 가능 여부 테스트 : 저장된 정보가 체스 보드 칸 64개를 복구할 수 없는 경우")
    @Test
    void test2() throws SQLException {
        Map<Position, Piece> saved = BoardFactory.getBoard();
        saved.remove(Position.ofPositionName("a1"));
        TestPieceDao dao = new TestPieceDao("guest", saved);
        ChessWebService webService = new ChessWebService(dao);

        boolean actual = webService.canContinue("guest");

        assertThat(actual).isEqualTo(false);
    }
}
