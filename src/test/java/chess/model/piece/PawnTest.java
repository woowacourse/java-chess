package chess.model.piece;

import chess.model.position.Movement;
import chess.model.position.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    private final Piece blackPawn = Pawn.from(Color.BLACK);
    private final Piece whitePawn = Pawn.from(Color.WHITE);
    private final Piece empty = Empty.getInstance();

    @Nested
    class WhitePawnTest {
        @Test
        void 목적지에_같은_색_기물이_있으면_예외가_발생한다() {
            Position source = Position.of(1, 1);
            Position destination = Position.of(1, 2);
            Movement movement = new Movement(source, destination);
            assertThatThrownBy(() -> whitePawn.canMove(movement, whitePawn))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @CsvSource({"1,1,1,2", "1,2,1,3", "2,3,2,4", "6,7,6,8", "1,2,1,4", "2,2,2,4", "5,2,5,4", "8,2,8,4"})
        void 목적지에_상대_기물이_있으면_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, blackPawn)).isFalse();
        }

        @ParameterizedTest
        @CsvSource({"1,1,1,2", "1,2,1,3", "2,3,2,4", "6,7,6,8"})
        void 목적지에_기물이_없으면_한_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, empty)).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,2,1,4", "2,2,2,4", "5,2,5,4", "8,2,8,4"})
        void 목적지에_기물이_없으면_시작_위치에서_두_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, empty)).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"3,3,3,5", "3,4,3,6", "4,3,4,5", "7,3,7,5"})
        void 시작_위치가_아닐_땐_두_칸_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, empty)).isFalse();
        }

        @ParameterizedTest
        @CsvSource({"2,7,1,8", "2,2,3,3", "4,7,5,8", "3,4,4,5"})
        void 목적지에_상대_기물이_있으면_대각선_앞으로_한_칸_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, blackPawn)).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"2,7,1,8", "2,2,3,3", "4,7,5,8", "3,4,4,5"})
        void 목적지에_기물이_없으면_대각선_앞으로_한_칸_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, empty)).isFalse();
        }


        @ParameterizedTest
        @CsvSource({"1,1,2,1", "2,2,1,1", "2,3,2,2", "6,7,6,6"})
        void 옆이나_뒤로는_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(whitePawn.canMove(movement, empty)).isFalse();
        }
    }

    @Nested
    class BlackPawnTest {
        @Test
        void 목적지에_같은_색_기물이_있으면_예외가_발생한다() {
            Position source = Position.of(2, 4);
            Position destination = Position.of(2, 3);
            Movement movement = new Movement(source, destination);
            assertThatThrownBy(() -> blackPawn.canMove(movement, blackPawn))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest
        @CsvSource({"2,4,2,3", "2,5,2,4", "7,8,7,7", "7,7,7,6", "1,7,1,5", "2,7,2,5", "5,7,5,5", "8,7,8,5"})
        void 목적지에_상대_기물이_있으면_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, whitePawn)).isFalse();
        }

        @ParameterizedTest
        @CsvSource({"2,4,2,3", "2,5,2,4", "7,8,7,7", "7,7,7,6"})
        void 목적지에_기물이_없으면_한_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, empty)).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,7,1,5", "2,7,2,5", "5,7,5,5", "8,7,8,5"})
        void 목적지에_기물이_없으면_시작_위치에서_두_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, empty)).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,5,1,3", "2,6,2,4", "5,5,5,3", "8,3,8,1"})
        void 시작_위치가_아닐_땐_두_칸_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, empty)).isFalse();
        }

        @ParameterizedTest
        @CsvSource({"1,8,2,7", "3,3,2,2", "5,8,4,7", "4,5,3,4"})
        void 목적지에_상대_기물이_있으면_대각선으로_한_칸_앞으로_움직일_수_있다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, whitePawn)).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"1,8,2,7", "3,3,2,2", "5,8,4,7", "4,5,3,4"})
        void 목적지에_기물이_없으면_대각선으로_한_칸_앞으로_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, empty)).isFalse();
        }

        @ParameterizedTest
        @CsvSource({"1,1,1,2", "1,1,1,2", "2,2,2,3", "6,5,6,6"})
        void 옆이나_뒤로는_움직일_수_없다(int sourceFile, int sourceRank, int destinationFile, int destinationRank) {
            Position source = Position.of(sourceFile, sourceRank);
            Position destination = Position.of(destinationFile, destinationRank);
            Movement movement = new Movement(source, destination);
            assertThat(blackPawn.canMove(movement, empty)).isFalse();
        }
    }
}
