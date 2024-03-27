package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("블랙이면서 첫 이동이면 한칸을 아래로 이동할 수 있다.")
    @Test
    void canMoveBlack_start1() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SEVEN);
        Position target = Position.of(File.C, Rank.SIX);
        Piece empty = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, empty);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("블랙이면서 첫 이동이면 두칸을 아래로 이동할 수 있다.")
    @Test
    void canMoveBlack_start2() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SEVEN);
        Position target = Position.of(File.C, Rank.FIVE);
        Piece empty = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, empty);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("블랙이면서 첫 이동이 아니면 두칸을 아래로 움직일 수 없다.")
    @Test
    void canNotMoveBlackTwoSquare() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.C, Rank.FOUR);
        Piece empty = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, empty);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("target 위치가 대각선이 아니고 말이 있다면 색깔에 관계 없이 움직일 수 없다.")
    @Test
    void canNotMoveBlack() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.C, Rank.FIVE);
        Piece white = new Pawn(Color.WHITE);
        Piece black = new Pawn(Color.BLACK);

        // when
        boolean canMove = pawn.canMove(source, target, white);
        boolean canMove2 = pawn.canMove(source, target, black);

        //then
        assertAll(
                () -> assertThat(canMove).isFalse(),
                () -> assertThat(canMove2).isFalse()
        );
    }

    @DisplayName("target 위치가 대각선이고 다른 색의 말이 있다면 움직일 수 있다.")
    @Test
    void canMoveBlackWithDiffColorDiagonal() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.B, Rank.FIVE);
        Piece piece = new Pawn(Color.WHITE);

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치가 대각선이고 다른 색의 말이 없다면 움직일 수 없다.")
    @Test
    void canNotMoveBlackDiagonal() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.B, Rank.FIVE);
        Piece piece = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("블랙 폰의 이동 경로를 반환한다.")
    @Test
    void makePathBlack_1() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.C, Rank.FIVE);

        // when
        List<Position> movingPath = pawn.searchPath(source, target);

        // then
        assertThat(movingPath).isEmpty();
    }

    @DisplayName("블랙 폰이 두칸 이동한 경우 이동 경로를 반환한다.")
    @Test
    void makePathBlack_2() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);

        Position source = Position.of(File.C, Rank.SEVEN);
        Position target = Position.of(File.C, Rank.FIVE);

        // when
        List<Position> movingPath = pawn.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.C, Rank.SIX));
    }

    @DisplayName("화이트이면서 첫 이동이면 한칸을 위로 이동할 수 있다.")
    @Test
    void canMoveWhite_start1() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.THREE);
        Piece piece = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("화이트이면서 첫 이동이면 두칸을 위로 이동할 수 있다.")
    @Test
    void canMoveWhite_start2() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.FOUR);
        Piece piece = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("화이트이면서 첫 이동이 아니면 두칸을 위로 움직일 수 없다.")
    @Test
    void canNotMoveWhiteTwoSquare() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.THREE);
        Position target = Position.of(File.C, Rank.FIVE);
        Piece piece = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("target 위치가 대각선이 아니고 말이 있다면 색깔에 관계 없이 움직일 수 없다.")
    @Test
    void canNotMoveWhite() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.C, Rank.SEVEN);
        Piece piece = new Pawn(Color.BLACK);

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("target 위치가 대각선이고 다른 색의 말이 있다면 움직일 수 있다.")
    @Test
    void canMoveWhiteWithDiffColorDiagonal() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.B, Rank.SEVEN);
        Piece piece = new Pawn(Color.BLACK);

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isTrue();
    }

    @DisplayName("target 위치가 대각선이고 다른 색의 말이 없다면 움직일 수 없다.")
    @Test
    void canNotMoveWhiteDiagonal() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.SIX);
        Position target = Position.of(File.B, Rank.SEVEN);
        Piece piece = new Empty();

        // when
        boolean canMove = pawn.canMove(source, target, piece);

        //then
        assertThat(canMove).isFalse();
    }

    @DisplayName("화이트 폰의 이동 경로를 반환한다.")
    @Test
    void makePathWhite_1() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.THREE);
        Position target = Position.of(File.B, Rank.FOUR);

        // when
        List<Position> movingPath = pawn.searchPath(source, target);

        // then
        assertThat(movingPath).isEmpty();
    }

    @DisplayName("화이트 폰이 두칸 이동한 경우 이동 경로를 반환한다.")
    @Test
    void makePathWhite_2() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);

        Position source = Position.of(File.C, Rank.TWO);
        Position target = Position.of(File.C, Rank.FOUR);

        // when
        List<Position> movingPath = pawn.searchPath(source, target);

        // then
        assertThat(movingPath).contains(Position.of(File.C, Rank.THREE));
    }
}
