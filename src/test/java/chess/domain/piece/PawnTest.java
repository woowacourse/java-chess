package chess.domain.piece;

import chess.domain.game.Board;
import chess.domain.game.InitializedChess;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class PawnTest {
    private final Pawn pawn = Pawn.createWhitePawn();
    private final Position sourcePosition = Position.of("a2");
    private final Board board = InitializedChess.create()
                                                .getBoard();
    
    
    @Test
    @DisplayName("1칸 전진 테스트")
    void moveLinearOneStep() {
        Position targetPosition = Position.of("a3");
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition, board);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("2칸 전진 테스트")
    void moveLinearTwoStep() {
        Position targetPosition = Position.of("a4");
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition, board);
        
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("3칸 이상 전진 시 예외 발생")
    void moveLinearMultiStep_OverTwoStep_ExceptionThrown() {
        Position targetPosition = Position.of("a5");
        
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition, board);
        
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("1칸 대각선에 있는 상대방 기물 잡기 테스트")
    void moveDiagonalOneStep() {
        
        // given
        final Map<Position, Piece> testBoard = this.board.getBoard();
        Position targetPosition = Position.of("b3");
        testBoard.put(targetPosition, Pawn.createBlackPawn());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition,
                new Board(testBoard));
        
        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("타겟 위치에 이미 기물이 있을 경우 예외 발생")
    void move_PieceAlreadyExistsAtTarget_ExceptionThrown() {
        
        // given
        Position targetPosition = Position.of("a3");
        Board newBoard = BoardUtils.put(board, targetPosition, Pawn.createBlackPawn());
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("첫 이동이 아닌데 2칸을 전진할 경우 예외 발생")
    void moveTwoStep_IfPawnIsNotFirstMovement_ExceptionThrown() {
        
        // given
        Position newSourcePosition = Position.of("c3");
        Board newBoard = BoardUtils.put(board, newSourcePosition, Pawn.createWhitePawn());
        
        Position targetPosition = Position.of("c5");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(newSourcePosition, targetPosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("1칸 대각선 이동시 타겟 위치가 블랭크일 경우 이동 불가")
    void moveDiagonal_IfTargetIsBlank_ExceptionThrown() {
        
        // given
        final Position targetPosition = Position.of("b3");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition, board);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }
    
    @Test
    @DisplayName("2칸 이동 시 경로 중간에 기물이 있을 경우 예외 발생")
    void move_ObstacleIsInPath_ExceptionThrown() {
        
        // given
        Position blackPawnPosition = Position.of("c3");
        Board newBoard = BoardUtils.put(board, blackPawnPosition, Pawn.createBlackPawn());
        
        Position targetPosition = Position.of("c4");
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> pawn.move(sourcePosition, targetPosition, newBoard);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        assertThat(pawn.getScore()).isEqualTo(1);
    }
}