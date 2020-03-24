package chess.domain.position;

import chess.domain.position.ChessFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ChessFileTest {

    @Test
    void from_ChessFilePosition_ReturnInstance() {
        assertThat(ChessFile.from('a')).isInstanceOf(ChessFile.class);
    }

    @Test
    void from_EqualsInstance_ReturnTrue() {
        ChessFile chessFile1 = ChessFile.from('a');
        ChessFile chessFile2 = ChessFile.from('a');

        assertThat(chessFile1.equals(chessFile2)).isTrue();
        assertThat(chessFile1 == chessFile2).isTrue();
    }

    @ParameterizedTest
    @ValueSource(chars = {'i', '9', '가'})
    void validate_InvalidChessFile_ExceptionThrown(char invalidChessFile) {
        assertThatThrownBy(() -> ChessFile.from(invalidChessFile)).isInstanceOf(IllegalArgumentException.class);
    }
}
