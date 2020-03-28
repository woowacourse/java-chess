package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movefactory.MoveType;
import chess.domain.movefactory.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("d4");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece rook = new Rook(source, new BlackTeam());

        assertThat(rook.isMovable(moveType)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("e3");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece rook = new Rook(source, new BlackTeam());

        assertThat(rook.isMovable(moveType)).isFalse();
    }

    @Test
    @DisplayName("룩의 이름이 블랙팀이면 룩의 이름이 'r' 가 된다.")
    void blackTeamBishopNameTest() {
        Piece rook = new Rook(Position.of("e1"), new BlackTeam());
        assertThat(rook.pieceName()).isEqualTo("r");
    }

    @Test
    @DisplayName("룩의 이름이 화이트팀이면 룩의 이름이 'R' 가 된다.")
    void whiteTeamBishopNameTest() {
        Piece rook = new Rook(Position.of("e1"), new WhiteTeam());
        assertThat(rook.pieceName()).isEqualTo("R");
    }

    @Test
    @DisplayName("룩의 점수가 5점이다")
    void bishopScoreTest() {
        Piece rook = new Rook(Position.of("e1"), new BlackTeam());
        assertThat(rook.getScore()).isEqualTo(5);
    }
}