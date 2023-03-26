package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.dao.HistoryFakeDao;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.dto.MoveDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    @Test
    @DisplayName("기물들의 움직임을 저장한다.")
    void saveMoveHistory() {
        // given
        ChessGame chessGame = new ChessGame();
        HistoryFakeDao historyFakeDao = new HistoryFakeDao();

        // when
        chessGame.start();
        chessGame.move(Square.of(File.A, Rank.TWO), Square.of(File.A, Rank.FOUR), historyFakeDao);
        List<MoveDto> moveDtos = historyFakeDao.selectAllHistory();
        MoveDto moveDto = moveDtos.get(0);

        // expected
        assertThat(moveDto.getSource()).isEqualTo("a2");
        assertThat(moveDto.getTarget()).isEqualTo("a4");
    }
}
