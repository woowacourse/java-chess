package chess.controller;

import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.view.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StartControllerTest {
    @Test
    void 스타트_컨트롤러는_보더를_초기화_해준다() {
        //given
        StartController startController = new StartController(new OutputView());
        Board board = BoardGenerator.emtpyBoard();

        //when
        Board expect = startController.execute(new RequestInfo("start"), board);

        //then
        Assertions.assertThat(expect).isNotEqualTo(BoardGenerator.emtpyBoard());
    }
}
