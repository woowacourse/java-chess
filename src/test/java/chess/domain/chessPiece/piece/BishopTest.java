package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.move.MoveType;
import chess.domain.move.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    @DisplayName("이동 성공 테스트")
    void movable() {
        Position source = Position.of("d2");
        Position target = Position.of("g5");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece bishop = new Bishop(source, new BlackTeam());

        assertThat(bishop.isMovable(moveType)).isTrue();
    }

    @Test
    @DisplayName("이동 실패 테스트")
    void isNotMovable() {
        Position source = Position.of("d2");
        Position target = Position.of("d4");

        MoveType moveType = MoveTypeFactory.of(source, target);
        Piece bishop = new Bishop(source, new BlackTeam());

        assertThat(bishop.isMovable(moveType)).isFalse();
    }

    @Test
    @DisplayName("비숍의 이름이 블랙팀이면 비숍의 이름이 'b' 가 된다.")
    void blackTeamBishopNameTest() {
        Piece bishop = new Bishop(Position.of("b1"), new BlackTeam());
        assertThat(bishop.pieceName()).isEqualTo("b");
    }

    @Test
    @DisplayName("비숍의 이름이 화이트팀이면 비숍의 이름이 'B' 가 된다.")
    void whiteTeamBishopNameTest() {
        Piece bishop = new Bishop(Position.of("b1"), new WhiteTeam());
        assertThat(bishop.pieceName()).isEqualTo("B");
    }

    @Test
    @DisplayName("비숍의 점수가 3점이다")
    void bishopScoreTest() {
        Piece bishop = new Bishop(Position.of("b1"), new BlackTeam());
        assertThat(bishop.getScore()).isEqualTo(3);
    }
}