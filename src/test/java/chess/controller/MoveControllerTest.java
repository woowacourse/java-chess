package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.view.OutputView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MoveControllerTest {

    @Test
    void Move_Contoller_는_전진_명령을_수행한다() {
        //given
        MoveController moveController = new MoveController(new OutputView());
        RequestInfo request = new RequestInfo("move a2 a3");

        //when
        Board executedBoard = moveController.execute(request, BoardGenerator.makeBoard());
        Map<Position, Piece> board = executedBoard.getBoard();
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
