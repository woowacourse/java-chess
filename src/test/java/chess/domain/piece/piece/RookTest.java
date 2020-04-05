package chess.domain.piece.piece;

import chess.domain.move.Move;
import chess.domain.move.MoveFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RookTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("d4");

        Move move = MoveFactory.findMovePattern(source, target);
        Piece rook = new Rook(source, new BlackTeam());

        rook.validateMovePattern(move, null);
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("e3");

        Move move = MoveFactory.findMovePattern(source, target);
        Piece rook = new Rook(source, new BlackTeam());

        assertThatThrownBy(() -> rook.validateMovePattern(move, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다");
    }
}
