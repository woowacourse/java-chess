package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.exception.IllegalMoveException;
import chess.domain.game.Team;
import chess.domain.move.Direction;
import chess.domain.move.Move;

class EmptyPieceTest {

    @DisplayName("비어있는 기물이다")
    @Test
    void isEmpty() {
        Piece emptyPiece = EmptyPiece.create();

        assertThat(emptyPiece.isEmpty()).isTrue();
    }

    @DisplayName("움직일 수 없다")
    @Test
    void move_throws() {
        Piece emptyPiece = EmptyPiece.create();

        assertThatThrownBy(() -> emptyPiece.hasMove(new Move(Direction.UP)))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("움직일 기물이 없습니다");
        assertThatThrownBy(() -> emptyPiece.hasAttackMove(new Move(Direction.UP)))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("움직일 기물이 없습니다");
    }

    @DisplayName("누구의 팀도 아니다")
    @Test
    void neitherWhiteNorBlack() {
        Piece emptyPiece = EmptyPiece.create();

        assertThat(emptyPiece.hasTeam(Team.WHITE)).isFalse();
        assertThat(emptyPiece.hasTeam(Team.BLACK)).isFalse();
    }

    @DisplayName("누구의 팀도 아니다")
    @Test
    void neitherWhiteNorBlack_2() {
        Piece emptyPiece = EmptyPiece.create();

        assertThat(emptyPiece.isSameTeamWith(new Pawn(Team.WHITE))).isFalse();
        assertThat(emptyPiece.isSameTeamWith(new Pawn(Team.BLACK))).isFalse();
    }
}