package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.move.MoveType;
import chess.domain.move.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("f3");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece knight = new Knight(source, new BlackTeam());

        assertThat(knight.isMovable(moveType)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("e3");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece knight = new Knight(source, new BlackTeam());

        assertThat(knight.isMovable(moveType)).isFalse();
    }

    @Test
    @DisplayName("나이트의 이름이 블랙팀이면 나이트의 이름이 'n' 가 된다.")
    void blackTeamBishopNameTest() {
        Piece knight = new Knight(Position.of("e1"), new BlackTeam());
        assertThat(knight.pieceName()).isEqualTo("n");
    }

    @Test
    @DisplayName("나이트의 이름이 화이트팀이면 나이트의 이름이 'N' 가 된다.")
    void whiteTeamBishopNameTest() {
        Piece knight = new Knight(Position.of("e1"), new WhiteTeam());
        assertThat(knight.pieceName()).isEqualTo("N");
    }

    @Test
    @DisplayName("나이트의 점수가 2.5점이다")
    void bishopScoreTest() {
        Piece knight = new Knight(Position.of("e1"), new BlackTeam());
        assertThat(knight.getScore()).isEqualTo(2.5);
    }
}