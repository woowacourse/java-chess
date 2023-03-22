package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PawnTest {

    @DisplayName("백은 처음에 1칸 혹은 2칸 위로 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "c4"})
    void whiteMoveUp_onceOrTwice_onFirstMove(String position) {
        Piece pawn = new Pawn(WHITE);
        Move move = new Move(new Position("c2"), new Position(position));

        assertThat(pawn.isValidMove(move, null)).isTrue();
    }

    @DisplayName("흑은 처음에 1칸 혹은 2칸 아래로 움직일 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"c6", "c5"})
    void blackMoveDown_onceOrTwice_onFirstMove(String position) {
        Piece pawn = new Pawn(BLACK);
        Move move = new Move(new Position("c7"), new Position(position));

        assertThat(pawn.isValidMove(move, null)).isTrue();
    }

    @DisplayName("처음에 3칸 이상 움직일 수 없다")
    @Test
    void canNotMove_moreThan3_onFirstMove() {
        Piece pawn = new Pawn(WHITE);
        Move move = new Move(new Position("c2"), new Position("c5"));

        assertThat(pawn.isValidMove(move, null)).isFalse();
    }

    @DisplayName("백은 아래로 움직일 수 없다")
    @Test
    void whiteCannotMoveDown() {
        Piece pawn = new Pawn(WHITE);
        Move move = new Move(new Position("c2"), new Position("c1"));

        assertThat(pawn.isValidMove(move, null)).isFalse();
    }

    @DisplayName("흑은 위로 움직일 수 없다")
    @Test
    void blackCannotMoveUp() {
        Piece pawn = new Pawn(BLACK);
        Move move = new Move(new Position("c7"), new Position("c8"));

        assertThat(pawn.isValidMove(move, null)).isFalse();
    }

    @DisplayName("첫 수 이후 1칸 앞으로 움직일 수 있다")
    @Test
    void moveOnceAfterTouch() {
        Piece touchedPawn = new Pawn(WHITE);
        Move move = new Move(new Position("c3"), new Position("c4"));

        assertThat(touchedPawn.isValidMove(move, null)).isTrue();
    }

    @DisplayName("첫 수 이후 앞으로 2칸 움직일 수 없다")
    @Test
    void canNotMove_moreThan2_AfterTouch() {
        Piece touchedPawn = new Pawn(WHITE);
        Move move = new Move(new Position("c3"), new Position("c5"));

        assertThat(touchedPawn.isValidMove(move, null)).isFalse();
    }

    @DisplayName("백은 윗 대각선으로 공격할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "d3"})
    void white_hasAttackMove_UpDiagonal(String position) {
        Piece pawn = new Pawn(WHITE);
        Piece targetPiece = new Pawn(BLACK);
        Move move = new Move(new Position("c2"), new Position(position));

        assertThat(pawn.isValidMove(move, targetPiece)).isTrue();
    }

    @DisplayName("흑은 아래 대각선으로 공격할 수 있다")
    @ParameterizedTest
    @ValueSource(strings = {"b1", "d1"})
    void black_hasAttackMove_DownDiagonal(String position) {
        Piece pawn = new Pawn(BLACK);
        Piece targetPiece = new Pawn(WHITE);
        Move move = new Move(new Position("c2"), new Position(position));

        assertThat(pawn.isValidMove(move, targetPiece)).isTrue();
    }

    @DisplayName("백은 아래 대각선으로 공격할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"b1", "d1"})
    void white_hasAttackMove_DownDiagonal_throws(String position) {
        Piece pawn = new Pawn(WHITE);
        Piece targetPiece = new Pawn(BLACK);
        Move move = new Move(new Position("c2"), new Position(position));

        assertThat(pawn.isValidMove(move, targetPiece)).isFalse();
    }

    @DisplayName("흑은 윗 대각선으로 공격할 수 없다")
    @ParameterizedTest
    @ValueSource(strings = {"b3", "d3"})
    void black_hasAttackMove_UpDiagonal_throws(String position) {
        Piece pawn = new Pawn(BLACK);
        Piece targetPiece = new Pawn(WHITE);
        Move move = new Move(new Position("c2"), new Position(position));

        assertThat(pawn.isValidMove(move, targetPiece)).isFalse();
    }

    @DisplayName("공격시 수직으로 이동할 수 없다")
    @Test
    void canNotMoveStraight_onAttack() {
        Piece pawn = new Pawn(WHITE);
        Piece targetPiece = new Pawn(BLACK);
        Move move = new Move(new Position("c2"), new Position("c3"));

        assertThat(pawn.isValidMove(move, targetPiece)).isFalse();
    }

    @DisplayName("이동시 대각선으로 이동할 수 없다")
    @Test
    void canNotMoveDiagonal_onMove() {
        Piece pawn = new Pawn(WHITE);
        Move move = new Move(new Position("c2"), new Position("b1"));

        assertThat(pawn.isValidMove(move, null)).isFalse();
    }
}
