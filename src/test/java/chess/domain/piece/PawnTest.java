package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    
    @Test
    @DisplayName("폰 움직일 수 있는 위치 테스트")
    void canMove() {
        Pawn whitePawn = Pawn.create(Color.WHITE);
        assertDoesNotThrow(() ->
                whitePawn.canMove(Position.from("a2"), Position.from("a3"))
        );
        assertDoesNotThrow(() ->
                whitePawn.canMove(Position.from("a2"), Position.from("a4"))
        );
        Pawn blackPawn = Pawn.create(Color.BLACK);
        assertDoesNotThrow(() ->
                blackPawn.canMove(Position.from("a7"), Position.from("a6"))
        );
        assertDoesNotThrow(() ->
                blackPawn.canMove(Position.from("a7"), Position.from("a5"))
        );
    }
    
    @Test
    @DisplayName("폰 움직일 수 없는 위치 테스트")
    void canNotMove() {
        Pawn whitePawn = Pawn.create(Color.WHITE);
        Assertions.assertThatThrownBy(() ->
                whitePawn.canMove(Position.from("a2"), Position.from("a1"))
        ).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() ->
                whitePawn.canMove(Position.from("a2"), Position.from("a1"))
        ).isInstanceOf(IllegalArgumentException.class);
        
        Pawn blackPawn = Pawn.create(Color.BLACK);
        Assertions.assertThatThrownBy(() ->
                blackPawn.canMove(Position.from("a7"), Position.from("a8"))
        ).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() ->
                blackPawn.canMove(Position.from("a7"), Position.from("a8"))
        ).isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    @DisplayName("폰 움직일 수 없는 거리 테스트")
    void canNotMoveDistance() {
        Pawn whitePawn = Pawn.create(Color.WHITE);
        Assertions.assertThatThrownBy(() ->
                whitePawn.canMove(Position.from("a2"), Position.from("a5"))
        ).isInstanceOf(IllegalArgumentException.class);
        
        Pawn blackPawn = Pawn.create(Color.BLACK);
        Assertions.assertThatThrownBy(() ->
                blackPawn.canMove(Position.from("a7"), Position.from("a4"))
        ).isInstanceOf(IllegalArgumentException.class);
        
    }
    
}