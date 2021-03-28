package chess.domain.board;

import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.piece.Blank;
import chess.domain.piece.BoardUtils;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    
    private Chess chess;
    private Board board;
    
    
    @BeforeEach
    void setUp() {
        chess = Chess.createWithEmptyBoard()
                     .start();
        board = chess.getBoard();
    }
    
    @Test
    @DisplayName("기물 이동")
    void movePiece() {
        
        // given
        Position sourcePosition = Position.from("b2");
        Position targetPosition = Position.from("b3");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        chess.move(movePosition);
        
        // then
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
        
        killKingOfBlack();
        
        assertThat(chess.isRunning()).isFalse();
    }
    
    private void killKingOfBlack() {
        Position whitePawnPosition = Position.from("d7");
        Board newBoard = BoardUtils.put(board, whitePawnPosition, Pawn.WHITE_INSTANCE);
        chess = new Chess(newBoard);
        
        Position blackKingPosition = Position.from("e8");
        MovePosition movePosition = new MovePosition(whitePawnPosition, blackKingPosition);
        chess = chess.move(movePosition);
    }
    
    @Test
    @DisplayName("폰이 일직선에 없을때 점수 확인")
    void score_PawnsAreAnotherFile() {
        
        // given
        final Chess emptyChess = Chess.createWithEmptyBoard();
        Board newBoard = emptyChess.getBoard();
        newBoard = BoardUtils.put(newBoard, Position.from("a1"), Pawn.WHITE_INSTANCE);
        newBoard = BoardUtils.put(newBoard, Position.from("b1"), Pawn.WHITE_INSTANCE);
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
        final Chess emptyChess = Chess.createWithEmptyBoard();
        Board newBoard = emptyChess.getBoard();
        newBoard = BoardUtils.put(newBoard, Position.from("a1"), Pawn.WHITE_INSTANCE);
        newBoard = BoardUtils.put(newBoard, Position.from("a2"), Pawn.WHITE_INSTANCE);
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
        killKingOfBlack();
        
        // then
        assertThat(chess.winner()).isEqualTo(Color.WHITE);
    }
}
