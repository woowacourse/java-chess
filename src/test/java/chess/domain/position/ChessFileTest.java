package chess.domain.position;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void move_FileMovingUnit_ReturnMovedChessFile() {
        ChessFile sourceFile = ChessFile.from('a');

        ChessFile expected = ChessFile.from('b');
        assertThat(sourceFile.move(1)).isEqualTo(expected);
    }

    @Test
    void intervalTo_TargetChessFile_CalculateInterval() {
        ChessFile sourceFile = ChessFile.from('a');
        ChessFile targetFile = ChessFile.from('b');

        int expected = 'b' - 'a';
        assertThat(sourceFile.intervalTo(targetFile)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    void intervalTo_NullChessFile_ExceptionThrown(ChessFile chessFile) {
        assertThatThrownBy(() -> ChessFile.from('a').intervalTo(chessFile))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("비교할 타겟 파일이 존재하지 않습니다.");
    }
}
