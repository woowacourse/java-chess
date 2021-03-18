package chess.domain.piece;

import chess.board.Board;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PawnTest {
    private Pawn pawn;
    
    private List<List<Piece>> pieces;
    
    @BeforeEach
    void setUp() {
        Position position = Position.of(1, 1);
        
        pawn = new Pawn(Color.WHITE, position);
        
        Board board = new Board();
        board.init();
        
        pieces = board.getBoard();
    }
    
    @Test
    @DisplayName("1칸 전진 테스트")
    void move() {
        Position position = Position.of(2, 1);
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(position, pieces);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("이동 에러 검사")
    void validate() {
        Position position = Position.of(2, 4);
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(position, pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void score() {
        assertThat(pawn.getScore()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("2칸 전진 테스트")
    void a() {
        Position position = Position.of("b4");
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(position, pieces);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("1칸 대각선에 있는 상대방 기물 잡기 테스트")
    void b() {
        Position position = Position.of("b4");
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(position, pieces);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("전진 위치에 기물이 있을 경우 예외 발생")
    void c() {
        Position blackPawnPosition = Position.of("b3");
        
        pieces.get(2)
              .set(1, new Pawn(Color.BLACK, blackPawnPosition));
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(blackPawnPosition, pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("첫 이동이 아닌데 2칸을 전진할 경우 예외 발생")
    void d() {
        Position whitePawnPosition = Position.of("b3");
        pieces.get(2)
              .set(1, new Pawn(Color.WHITE, whitePawnPosition));
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(Position.of("b5"), pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("1칸 대각선 이동시 타겟 위치가 블랭크일 경우 이동 불가")
    void e() {
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(Position.of("c3"), pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("2칸 이동 시 경로 중간에 기물이 있을 경우 예외 발생")
    void f() {
        Position blackPawnPosition = Position.of("b3");
        
        pieces.get(2).set(1, new Pawn(Color.BLACK, blackPawnPosition));
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(Position.of("b4"), pieces);
        
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("이동하는 경로 사이에 기물이 있습니다.");
    }
}