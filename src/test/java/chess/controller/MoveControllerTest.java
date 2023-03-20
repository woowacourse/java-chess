package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.dto.PieceResponse;
import chess.view.OutputView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        List<List<PieceResponse>> piecePosition = executedBoard.getPiecePosition();
        PieceResponse pieceResponse = piecePosition.get(2).get(0);
        String pieceColor = pieceResponse.getPieceColor();
        String pieceType = pieceResponse.getPieceType();


        //then
        Assertions.assertAll(() -> {
                    assertThat(pieceColor).isEqualTo("WHITE");
                },
                () -> {
                    assertThat(pieceType).isEqualTo("p");
                }
        );
    }
}
