package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.PieceDoesNotExistException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlankTest {

    private static final Piece BLANK = Blank.getBlank();
    private static final Side SIDE = Side.BLACK;

    @Test
    @DisplayName("기물이 없는 빈칸 선택시 예외발생")
    void getRoute() {
        Piece blank = Blank.getBlank();
        assertThatThrownBy(() -> blank.route(Position.from("a1"), Position.from("a2"), BLANK, SIDE))
                .isInstanceOf(PieceDoesNotExistException.class);
    }
}