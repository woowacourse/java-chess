package chess.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EndControllerTest {

    @Test
    void 엔드_컨트롤러는_종료_응답을_반환한다() {
        //given
        EndController endController = EndController.getInstance();

        //when
        Response response = endController.execute(new Request("end"));

        //then
        Assertions.assertThat(response.getType()).isEqualTo(ResponseType.END);
    }
}
