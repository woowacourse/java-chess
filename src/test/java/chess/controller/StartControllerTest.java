package chess.controller;

import chess.domain.board.BoardSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StartControllerTest {
    @Test
    void 스타트_컨트롤러는_보드_세션에_보드를_등록해준다() {
        //given
        StartController startController = StartController.getInstance();

        //when
        boolean before = BoardSession.existBoard();
        startController.execute(new Request("start"));
        boolean after = BoardSession.existBoard();

        //then
        Assertions.assertFalse(before);
        Assertions.assertTrue(after);
    }
}
