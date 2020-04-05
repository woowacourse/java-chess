package chess.domain.piece.piece;

import chess.domain.move.Move;
import chess.domain.move.MoveFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.BlackTeam;
import chess.domain.piece.team.WhiteTeam;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    @Test
    @DisplayName("폰 2칸이동 테스트 성공")
    void isMovable() {
        Position sourcePosition = Position.of("b2");
        Position targetPosition = Position.of("b4");

        Pawn blackPawn = new Pawn(sourcePosition, new BlackTeam());
        Move move = MoveFactory.findMovePattern(sourcePosition, targetPosition);

        blackPawn.validateMovePattern(move, Optional.empty());
    }

    @Test
    @DisplayName("폰 2칸이동 테스트 실패")
    void isMovable2() {
        Position sourcePosition = Position.of("b3");
        Position targetPosition = Position.of("b5");

        Pawn blackPawn = new Pawn(sourcePosition, new BlackTeam());
        Move move = MoveFactory.findMovePattern(sourcePosition, targetPosition);

        assertThatThrownBy(() -> blackPawn.validateMovePattern(move, Optional.empty()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다");
    }

    @Test
    @DisplayName("폰 공격 테스트")
    void isMovableAttack() {
        Position blackPosition = Position.of("b2");
        Position whitePosition = Position.of("c3");

        Pawn blackPawn = new Pawn(blackPosition, new BlackTeam());
        Optional<Piece> whitePawn = Optional.of(new Pawn(whitePosition, new WhiteTeam()));
        Move move = MoveFactory.findMovePattern(blackPawn.getPosition(), whitePosition);

        blackPawn.validateMovePattern(move, whitePawn);
    }

    @Test
    @DisplayName("폰 뒤로 이동 테스트")
    void isMovableFalse() {
        Pawn blackPawn = new Pawn(Position.of("b5"), new WhiteTeam());
        Move move = MoveFactory.findMovePattern(blackPawn.getPosition(), Position.of("b6"));

        assertThatThrownBy(() -> blackPawn.validateMovePattern(move, Optional.empty()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 말이 갈 수 없는 칸입니다");
    }
}
