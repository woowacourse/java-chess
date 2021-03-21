package chess.domain.piece;

import chess.domain.game.Board;
import chess.domain.game.Chess;
import chess.domain.game.InitializedChess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    
    private final Chess chess = InitializedChess.create();
    private final Board board = chess.getBoard();
    
    public static Chess createEmptyBoard() {
        return new Chess(new Board(new HashMap<>()));
    }
    
    @Test
    @DisplayName("보드 초기화")
    void initializeBoardTest() {
        
        // then
        final Map<Position, Piece> chessBord = this.board.getBoard();
        assertThat(chessBord.get(Position.of("a1"))).isInstanceOf(Rook.class);
        assertThat(chessBord.get(Position.of("b1"))).isInstanceOf(Knight.class);
        assertThat(chessBord.get(Position.of("c1"))).isInstanceOf(Bishop.class);
        assertThat(chessBord.get(Position.of("d1"))).isInstanceOf(Queen.class);
        assertThat(chessBord.get(Position.of("e1"))).isInstanceOf(King.class);
        assertThat(chessBord.get(Position.of("f1"))).isInstanceOf(Bishop.class);
        assertThat(chessBord.get(Position.of("g1"))).isInstanceOf(Knight.class);
        assertThat(chessBord.get(Position.of("h1"))).isInstanceOf(Rook.class);
        
        for (int i = 0; i < 8; i++) {
            Position position = Position.of(i, 1);
            assertThat(chessBord.get(position)).isInstanceOf(Pawn.class);
        }
        
        assertThat(chessBord.get(Position.of("a8"))).isInstanceOf(Rook.class);
        assertThat(chessBord.get(Position.of("b8"))).isInstanceOf(Knight.class);
        assertThat(chessBord.get(Position.of("c8"))).isInstanceOf(Bishop.class);
        assertThat(chessBord.get(Position.of("d8"))).isInstanceOf(Queen.class);
        assertThat(chessBord.get(Position.of("e8"))).isInstanceOf(King.class);
        assertThat(chessBord.get(Position.of("f8"))).isInstanceOf(Bishop.class);
        assertThat(chessBord.get(Position.of("g8"))).isInstanceOf(Knight.class);
        assertThat(chessBord.get(Position.of("h8"))).isInstanceOf(Rook.class);
        
        for (int i = 0; i < 8; i++) {
            Position position = Position.of(i, 6);
            assertThat(chessBord.get(position)).isInstanceOf(Pawn.class);
        }
    }
    
    @Test
    @DisplayName("기물 이동")
    void movePiece() {
        
        // given
        final String source = "b2";
        final String target = "b3";
        
        // when
        chess.movePiece(source, target);
        
        // then
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        
        final Map<Position, Piece> chessBoard = this.board.getBoard();
        final Piece sourcePiece = chessBoard.get(sourcePosition);
        assertThat(sourcePiece).isInstanceOf(Blank.class);
        
        final Piece targetPiece = chessBoard.get(targetPosition);
        assertThat(targetPiece).isInstanceOf(Pawn.class);
    }
    
    @Test
    @DisplayName("킹이 죽으면 게임 상태를 멈춤으로 변경")
    void isRunning_IfKingIsDead_StatusISStop() {
        assertThat(chess.isRunning()).isTrue();
        
        final Chess newChess = killKingOfBlack();
        
        assertThat(newChess.isRunning()).isFalse();
    }
    
    private Chess killKingOfBlack() {
        Position whitePawnPosition = Position.of("d7");
        Board newBoard = BoardUtils.put(board, whitePawnPosition, Pawn.createWhitePawn());
        return new Chess(newBoard).movePiece("d7", "e8");
    }
    
    @Test
    @DisplayName("폰이 일직선에 없을때 점수 확인")
    void score_PawnsAreAnotherFile() {
        
        // given
        final Chess emptyChess = createEmptyBoard();
        Board newBoard = emptyChess.getBoard();
        newBoard = BoardUtils.put(newBoard, Position.of("a1"), Pawn.createWhitePawn());
        newBoard = BoardUtils.put(newBoard, Position.of("b1"), Pawn.createWhitePawn());
        final Chess chessAddedPawns = new Chess(newBoard);
        
        // when
        final double score = chessAddedPawns.score(Color.WHITE);
        
        // then
        assertThat(score).isEqualTo(2.0);
    }
    
    @Test
    @DisplayName("폰이 일직선에 있을때 점수 확인")
    void score_PawnsAreSameFile() {
        // given
        final Chess emptyChess = createEmptyBoard();
        Board newBoard = emptyChess.getBoard();
        newBoard = BoardUtils.put(newBoard, Position.of("a1"), Pawn.createWhitePawn());
        newBoard = BoardUtils.put(newBoard, Position.of("a2"), Pawn.createWhitePawn());
        final Chess chessAddedPawns = new Chess(newBoard);
        
        // when
        final double score = chessAddedPawns.score(Color.WHITE);
        
        // then
        assertThat(score).isEqualTo(1.0);
    }
    
    @Test
    @DisplayName("게임이 끝난 후 승자 확인")
    void winner() {
        
        
        // when
        final Chess newChess = killKingOfBlack();
        
        // then
        assertThat(newChess.winner()).isEqualTo(Color.WHITE);
    }
}
