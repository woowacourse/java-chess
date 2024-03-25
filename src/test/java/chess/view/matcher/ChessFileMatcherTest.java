package chess.view.matcher;

import chess.domain.position.ChessFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessFileMatcherTest {

    @DisplayName("ChessFileMatcher는 모든 ChessFile에 대한 매칭 정보를 포함한다")
    @Test
    void matchFiles() {
        for (ChessFile value : ChessFile.values()) {
            assertThat(ChessFileMatcher.isPresentFile(value)).isTrue();
        }
    }
}
