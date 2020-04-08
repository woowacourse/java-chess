package chess.service;

import chess.domain.ChessBoard;
import chess.domain.MoveState;
import chess.domain.Player;
import chess.domain.Square;
import chess.domain.piece.Color;
import chess.dto.MoveStateDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveStateServiceTest {

    @Test
    @DisplayName("move 내역 테이블 받아서 맵으로 가공해 컨트롤러에게 전달")
    void searchMoveHistory() throws SQLException {
        MoveStateService moveStateService = new MoveStateService();
        MoveState moveState = new MoveState(new Player("player"));
        moveState.move("move a2 a3", new ChessBoard("player", Color.WHITE));
        MoveStateDTO moveStateDTO = new MoveStateDTO(moveState);

        Map<Square, Square> moveHistory = new LinkedHashMap<>();

        moveHistory.put(Square.of("a2"), Square.of("a3"));
        moveHistory.put(Square.of("a7"), Square.of("a5"));
        assertThat(moveStateService.searchMoveHistory(moveStateDTO)).isEqualTo(moveHistory);

    }
}
