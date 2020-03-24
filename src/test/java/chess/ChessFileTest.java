package chess;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ChessFileTest {

    @Test
    void of_ChessFilePosition_ReturnInstance() {
        assertThat(ChessFile.of('a')).isInstanceOf(ChessFile.class);
    }

    @Test
    void of_EqualsInstance_ReturnTrue() {
        ChessFile chessFile1 = ChessFile.of('a');
        ChessFile chessFile2 = ChessFile.of('a');

        assertThat(chessFile1.equals(chessFile2)).isTrue();
        assertThat(chessFile1 == chessFile2).isTrue();
    }

    @ParameterizedTest
    @ValueSource(chars = {'i', '9', '가'})
    void validate_InvalidChessFile_ExceptionThrown(char invalidChessFile) {
        assertThatThrownBy(() -> ChessFile.of(invalidChessFile)).isInstanceOf(IllegalArgumentException.class);
    }
}
