package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.move.MoveType;
import chess.domain.move.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("f2");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece queen = new Queen(source, new BlackTeam());

        assertThat(queen.isMovable(moveType)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("f3");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece queen = new Queen(source, new BlackTeam());

        assertThat(queen.isMovable(moveType)).isFalse();
    }

    @Test
    @DisplayName("퀸의 이름이 블랙팀이면 퀸의 이름이 'q' 가 된다.")
    void blackTeamBishopNameTest() {
        Piece queen = new Queen(Position.of("e1"), new BlackTeam());
        assertThat(queen.pieceName()).isEqualTo("q");
    }

    @Test
    @DisplayName("퀸의 이름이 화이트팀이면 퀸의 이름이 'Q' 가 된다.")
    void whiteTeamBishopNameTest() {
        Piece queen = new Queen(Position.of("e1"), new WhiteTeam());
        assertThat(queen.pieceName()).isEqualTo("Q");
    }

    @Test
    @DisplayName("퀸의 점수가 9점이다")
    void bishopScoreTest() {
        Piece queen = new Queen(Position.of("e1"), new BlackTeam());
        assertThat(queen.getScore()).isEqualTo(9);
    }
}