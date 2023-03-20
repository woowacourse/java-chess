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
public class EndControllerTest {

    @Test
    void 엔드_컨트롤러는_보드를_그대로_반환한다() {
        //given
        EndController endController = new EndController(new OutputView());
        Board before = BoardGenerator.makeBoard();

        //when
        Board after = endController.execute(new RequestInfo("end"), before);

        //then
        Assertions.assertThat(before).isEqualTo(after);
    }
}
