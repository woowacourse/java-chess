package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.utils.MoveInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static chess.domain.position.Fixtures.A3;
import static chess.domain.position.Fixtures.A4;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ChessServiceTest {
    private Board board;

    @BeforeEach
    void setUp() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(A3, new Empty(A3, Team.NONE));
        map.put(A4, new Rook(A4, Team.BLACK));
        board = Board.of(map);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a3 a5", "a4 a5"})
    void move_Fail_When_SelectNotAlly(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ChessService.of(board, Team.WHITE).move(MoveInfo.of(input)))
                .withMessage("아군 기물의 위치가 아닙니다.");
    }
}