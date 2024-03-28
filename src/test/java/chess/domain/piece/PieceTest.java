package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PieceTest {

    class ExamplePiece extends Piece {

        protected ExamplePiece(Team team) {
            super(team, List.of());
        }
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    @DisplayName("해당 팀이 검정 팀인지 확인한다.")
    void isBlackTeamTest(Team team, boolean expected) {
        ExamplePiece examplePiece = new ExamplePiece(team);

        assertThat(examplePiece.isBlackTeam()).isEqualTo(expected);
    }

    @Test
    @DisplayName("해당 기물이 킹인지 확인한다.")
    void isKingTest() {
        King king = new King(Team.BLACK);
        Queen queen = new Queen(Team.WHITE);
        Rook rook = new Rook(Team.BLACK);
        Bishop bishop = new Bishop(Team.WHITE);
        Knight knight = new Knight(Team.WHITE);
        Pawn pawn = new Pawn(Team.BLACK);
        ExamplePiece examplePiece = new ExamplePiece(Team.WHITE);

        assertAll(
                () -> assertThat(king.isKing()).isTrue(),
                () -> assertThat(queen.isKing()).isFalse(),
                () -> assertThat(rook.isKing()).isFalse(),
                () -> assertThat(bishop.isKing()).isFalse(),
                () -> assertThat(knight.isKing()).isFalse(),
                () -> assertThat(pawn.isKing()).isFalse(),
                () -> assertThatThrownBy(examplePiece::isKing)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 기물이 존재하지 않습니다.")
        );
    }

    @Test
    @DisplayName("해당 기물이 폰인지 확인한다.")
    void isPawnTest() {
        King king = new King(Team.BLACK);
        Queen queen = new Queen(Team.WHITE);
        Rook rook = new Rook(Team.BLACK);
        Bishop bishop = new Bishop(Team.WHITE);
        Knight knight = new Knight(Team.WHITE);
        Pawn pawn = new Pawn(Team.BLACK);
        ExamplePiece examplePiece = new ExamplePiece(Team.WHITE);

        assertAll(
                () -> assertThat(king.isPawn()).isFalse(),
                () -> assertThat(queen.isPawn()).isFalse(),
                () -> assertThat(rook.isPawn()).isFalse(),
                () -> assertThat(bishop.isPawn()).isFalse(),
                () -> assertThat(knight.isPawn()).isFalse(),
                () -> assertThat(pawn.isPawn()).isTrue(),
                () -> assertThatThrownBy(examplePiece::isPawn)
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("해당 기물이 존재하지 않습니다.")
        );
    }
}
