package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movefactory.MoveType;
import chess.domain.movefactory.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰 2칸이동 테스트 성공")
    void isMovable() {
        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b4");

        Pawn blackPawn = new Pawn(sourcePosition, new BlackTeam());
        MoveType moveType = MoveTypeFactory.of(sourcePosition, targetPosition);

        assertThat(blackPawn.isMovable(moveType, null)).isTrue();
    }

    @Test
    @DisplayName("폰 2칸이동 테스트 실패")
    void isMovable2() {
        Position sourcePosition = Position.of("b3");
        Position targetPosition = Position.of("b5");

        Pawn blackPawn = new Pawn(sourcePosition, new BlackTeam());
        MoveType moveType = MoveTypeFactory.of(sourcePosition, targetPosition);

        assertThat(blackPawn.isMovable(moveType, null)).isFalse();
    }

    @Test
    @DisplayName("폰 공격 테스트")
    void isMovableAttack() {
        Pawn blackPawn = new Pawn(Position.of("b2"), new BlackTeam());
        Piece whitePawn = new Pawn(Position.of("c3"), new WhiteTeam());
        MoveType moveType = MoveTypeFactory.of(blackPawn.position, whitePawn.position);
        assertThat(blackPawn.isMovable(moveType, whitePawn)).isTrue();
    }

    @Test
    @DisplayName("폰 뒤로 이동 테스트")
    void isMovableFalse() {
        Pawn blackPawn = new Pawn(Position.of("b2"), new BlackTeam());
        MoveType moveType = MoveTypeFactory.of(blackPawn.position, Position.of("b1"));
        assertThat(blackPawn.isMovable(moveType, null)).isFalse();
    }
}