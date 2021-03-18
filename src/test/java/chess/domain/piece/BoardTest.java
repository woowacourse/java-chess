package chess.domain.piece;

import chess.board.Board;
import chess.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = new Board();
        board.init();
    }
    
    @Test
    @DisplayName("보드 초기화")
    void a() {
        
        // given
        final List<List<Piece>> board = this.board.getBoard();
        // when
        
        // then
        assertThat(board.get(0).get(0)).isInstanceOf(Rook.class);
        assertThat(board.get(0).get(1)).isInstanceOf(Knight.class);
        assertThat(board.get(0).get(2)).isInstanceOf(Bishop.class);
        // 필요시 테스트케이스 추가
    }
    
    @Test
    @DisplayName("기물 이동")
    void movePiece() {
        
        // given
        final String sourceValue = "b2";
        final String targetValue = "b3";
    
        // when
        board.movePiece(sourceValue, targetValue);
        
        // then
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
        
        final int sourceX = sourcePosition.getX().getPoint();
        final int sourceY = sourcePosition.getY().getPoint();
    
        final int targetX = targetPosition.getX().getPoint();
        final int targetY = targetPosition.getY().getPoint();
    
        final Piece sourcePiece = board.getBoard()
                                 .get(sourceX)
                                 .get(sourceY);
        
        assertThat(sourcePiece).isInstanceOf(Blank.class);
    
        final Piece targetPiece = board.getBoard()
                                       .get(targetX)
                                       .get(targetY);
    
        assertThat(targetPiece).isInstanceOf(Pawn.class);
    }
    
    @Test
    @DisplayName("끝났는지 확인")
    void d() {
        assertThat(board.isFinish()).isFalse();
    
        killKingOfBlack();
    
        assertThat(board.isFinish()).isTrue();
    }
    
    private void killKingOfBlack() {
        board.movePiece("b2", "b4");
        board.movePiece("c7", "c5");
        
        board.movePiece("b4", "c5");
        board.movePiece("d7", "d6");
        
        board.movePiece("c5", "d6");
        board.movePiece("b7", "b6");
        
        board.movePiece("d6", "d7");
        board.movePiece("a7", "a6");
        
        board.movePiece("d7", "e8");
    }
    
    @Test
    @DisplayName("폰이 일직선에 없을때 점수 확인")
    void b() {
        killKingOfBlack();
        assertThat(board.score(Color.BLACK)).isEqualTo(36.0);
    }
    
    @Test
    @DisplayName("폰이 일직선에 있을때 점수 확인")
    void e() {
        killKingOfBlack();
        assertThat(board.score(Color.WHITE)).isEqualTo(37.0);
    }
    
    @Test
    @DisplayName("게임이 끝난 후 승자 확인")
    void c() {
        
        // given
        
        
        // when
        killKingOfBlack();
    
        // then
        assertThat(board.winner()).isEqualTo(Color.WHITE);
    }
    
//    @Test
//    @DisplayName("게임이 끝나지 않은 상태에서 승자 확인 -> 에러")
//    void f() {
//
//        // given
//
//
//        // when
//
//
//        // then
//        assertThatThrownBy(() -> board.winner()).isInstanceOf(IllegalArgumentException.class);
//    }
}
