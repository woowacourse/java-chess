package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @ParameterizedTest
    @CsvSource({"1,1,1,2", "1,2,1,3", "2,3,2,4", "6,7,6,8"})
    void 화이트_폰은_한_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.WHITE).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"2,7,1,8", "2,2,3,3", "4,7,5,8", "3,4,4,5"})
    void 화이트_폰은_상대편이_존재한다면_대각선으로_한_칸_앞으로_움직일_수_있다(
            int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.WHITE).isValid(movement, Pawn.from(Color.BLACK))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1,2,1", "2,2,1,1", "2,3,2,2", "6,7,6,6"})
    void 화이트_폰은_옆이나_뒤로는_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.WHITE).isValid(movement, Empty.getInstance())).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1,2,1,4", "2,2,2,4", "5,2,5,4", "8,2,8,4"})
    void 화이트_폰은_시작_위치에서_두_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.WHITE).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"3,3,3,5", "3,4,3,6", "4,3,4,5", "7,3,7,5"})
    void 화이트_폰은_시작_위치가_아닐_땐_두_칸_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.WHITE).isValid(movement, Empty.getInstance())).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"2,4,2,3", "2,5,2,4", "7,8,7,7", "7,7,7,6"})
    void 블랙_폰은_한_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.BLACK).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,8,2,7", "3,3,2,2", "5,8,4,7", "4,5,3,4"})
    void 블랙_폰은_상대편이_존재한다면_대각선으로_한_칸_앞으로_움직일_수_있다(
            int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.BLACK).isValid(movement, Pawn.from(Color.WHITE))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,1,1,2", "1,1,1,2", "2,2,2,3", "6,5,6,6"})
    void 블랙_폰은_옆이나_뒤로는_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.BLACK).isValid(movement, Empty.getInstance())).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1,7,1,5", "2,7,2,5", "5,7,5,5", "8,7,8,5"})
    void 블랙_폰은_시작_위치에서_두_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.BLACK).isValid(movement, Empty.getInstance())).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"1,5,1,3", "2,6,2,4", "5,5,5,3", "8,3,8,1"})
    void 블랙_폰은_시작_위치가_아닐_땐_두_칸_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
        Position source = Position.of(sourceFile, sourceRank);
        Position destination = Position.of(destinationFile, destinationRank);
        Movement movement = new Movement(source, destination);
        assertThat(Pawn.from(Color.BLACK).isValid(movement, Empty.getInstance())).isFalse();
    }
}
