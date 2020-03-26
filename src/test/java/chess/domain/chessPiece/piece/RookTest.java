package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movefactory.MoveFactory;
import chess.domain.movefactory.MoveType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("d4");

        MoveType moveType = MoveFactory.of(source, target);
        Piece rook = new Rook(source, new BlackTeam());

        assertThat(rook.isMovable(moveType)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("e3");

        MoveType moveType = MoveFactory.of(source, target);
        Piece rook = new Rook(source, new BlackTeam());

        assertThat(rook.isMovable(moveType)).isFalse();
    }
}