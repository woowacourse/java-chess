package chess.domain.piece.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RookStrategyTest {
    private Piece whiteRook;
    private Map<Position, Team> boardDto;

    @BeforeEach
    void setUp() {
        whiteRook = Rook.of(Team.WHITE, Position.of("c4"));
        boardDto = new HashMap<>();
        boardDto.put(Position.of("c4"), Team.WHITE);
    }

    @ParameterizedTest
    @DisplayName("")
    @ValueSource(strings = {"c2", "c8", "a4", "h4"})
    void rookMoveStrategy(String target) {
        assertThat(whiteRook.move(Position.of("c4"), Position.of(target), boardDto))
                .isInstanceOf(Rook.class);
    }
}