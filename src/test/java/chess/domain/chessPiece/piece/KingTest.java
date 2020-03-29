package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.move.MoveType;
import chess.domain.move.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("d3");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece king = new King(source, new BlackTeam());

        assertThat(king.isMovable(moveType)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("d4");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece king = new King(source, new BlackTeam());

        assertThat(king.isMovable(moveType)).isFalse();
    }

    @Test
    @DisplayName("킹의 이름이 블랙팀이면 킹의 이름이 'b' 가 된다.")
    void blackTeamBishopNameTest() {
        Piece king = new King(Position.of("e1"), new BlackTeam());
        assertThat(king.pieceName()).isEqualTo("k");
    }

    @Test
    @DisplayName("킹의 이름이 화이트팀이면 킹의 이름이 'B' 가 된다.")
    void whiteTeamBishopNameTest() {
        Piece king = new King(Position.of("e1"), new WhiteTeam());
        assertThat(king.pieceName()).isEqualTo("K");
    }

    @Test
    @DisplayName("킹의 점수가 0점이다")
    void bishopScoreTest() {
        Piece king = new King(Position.of("e1"), new BlackTeam());
        assertThat(king.getScore()).isEqualTo(0);
    }
}