package chess.service;

import chess.db.MoveHistoryDao;
import chess.db.TestPieceDao;
import chess.domains.board.BoardFactory;
import chess.domains.piece.Piece;
import chess.domains.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessWebServiceTest {

    @DisplayName("이어하기 가능 여부 테스트 : 저장된 정보가 체스 보드 칸 64개를 복구할 수 있는 경우")
    @Test
    void test() {
        Map<Position, Piece> boardStatus = BoardFactory.getBoard();
        TestPieceDao dao = new TestPieceDao("guest", boardStatus);
        ChessWebService webService = new ChessWebService(dao, new MoveHistoryDao());

        boolean actual = webService.canResume(new HashMap<>(), "guest");

        assertThat(actual).isTrue();
    }


    @DisplayName("이어하기 가능 여부 테스트 : 저장된 정보가 체스 보드 칸 64개를 복구할 수 없는 경우")
    @Test
    void test2() {
        Map<Position, Piece> boardStatus = BoardFactory.getBoard();
        boardStatus.remove(Position.ofPositionName("a1"));
        TestPieceDao dao = new TestPieceDao("guest", boardStatus);
        ChessWebService webService = new ChessWebService(dao, new MoveHistoryDao());

        boolean actual = webService.canResume(new HashMap<>(), "guest");

        assertThat(actual).isFalse();
    }

}
