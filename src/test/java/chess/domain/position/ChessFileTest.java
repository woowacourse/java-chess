package chess.domain.position;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessFileTest {

    @ParameterizedTest
    @ValueSource(chars = {'i', '9', '가'})
    void validate_InvalidChessFile_ExceptionThrown(char invalidChessFile) {
        assertThatThrownBy(() -> ChessFile.findValueOf(invalidChessFile)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void move_FileMovingUnit_ReturnMovedChessFile() {
        ChessFile sourceFile = ChessFile.A;

        ChessFile expected = ChessFile.B;
        assertThat(sourceFile.move(1)).isEqualTo(expected);
    }

    @Test
    void intervalTo_TargetChessFile_CalculateInterval() {
        ChessFile sourceFile = ChessFile.A;
        ChessFile targetFile = ChessFile.B;

        int expected = 'b' - 'a';
        assertThat(sourceFile.intervalTo(targetFile)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    void intervalTo_NullChessFile_ExceptionThrown(ChessFile chessFile) {
        assertThatThrownBy(() -> ChessFile.A.intervalTo(chessFile))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("비교할 타겟 파일이 존재하지 않습니다.");
    }
}
