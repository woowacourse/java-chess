package chess.domain.board.position;

import static org.assertj.core.api.Assertions.*;

import chess.domain.board.position.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    @DisplayName("두 File 사이에 존재하는 모든 File을 List로 담아 전달한다.")
    void traceGroup() {
        //given
        List<File> files = File.traceGroup(File.A, File.D);

        //then
        assertThat(files).isEqualTo(List.of(File.B, File.C));
    }
}
