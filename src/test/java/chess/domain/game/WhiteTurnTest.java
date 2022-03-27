package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.dto.MoveCommandDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class WhiteTurnTest {

    private static final String BLACK_KING_POSITION = "e8";
    private static final String WHITE_KING_POSITION = "e1";

    private Game game;

    @BeforeEach
    void setUp() {
        List<Piece> pieces = new ArrayList<>(List.of(new King(WHITE), new King(BLACK)));
        game = new WhiteTurn(new ActivePieces(pieces));
    }

    @Test
    void 백색_체스말_이동_후_흑색_턴_반환() {
        Game actual = game.moveChessmen(new MoveCommandDto(WHITE_KING_POSITION, "d1"));

        assertThat(actual).isInstanceOf(BlackTurn.class);
    }

    @Test
    void 백색_턴에서_흑색_체스말_이동시_예외발생() {
        assertThatThrownBy(() -> game.moveChessmen(new MoveCommandDto(BLACK_KING_POSITION, "d8")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("WHITE 진영이 움직일 차례입니다!");
    }
}
