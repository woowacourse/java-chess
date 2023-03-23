package chess.controller;

import chess.domain.Position;
import chess.domain.board.BoardGenerator;
import chess.domain.board.BoardSession;
import chess.domain.piece.Color;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoveControllerTest {

    private static final MoveController controller = MoveController.getInstance();

    @BeforeEach
    void setting() {
        BoardSession.clear();
    }

    @Test
    void Move_명령은_Baord_Session에_보드가_생성된_후_실행가능_하다() {
        //given
        Response response = controller.execute(new Request("move a1 a2"));

        //when
        ResponseType type = response.getType();
        String cause = response.getCause();

        assertThat(type).isEqualTo(ResponseType.FAIL);
        assertThat(cause).isEqualTo("게임이 시작하지 않았습니다");
    }

    @Test
    void Move_Contoller_는_전진_명령을_수행한다() {
        //given
        Request request = new Request("move a2 a3");
        BoardSession.makeSession(BoardGenerator.makeBoard());

        //when
        Response response = controller.execute(request);
        Map<Position, Piece> board = BoardSession.getBoard().getBoard();
        Piece result = board.get(A3);


        //then
        Assertions.assertAll(() -> {
                    assertThat(result.getColor()).isEqualTo(Color.WHITE);
                },
                () -> {
                    assertThat(result).isInstanceOf(PawnPiece.class);
                }
        );
    }
}
