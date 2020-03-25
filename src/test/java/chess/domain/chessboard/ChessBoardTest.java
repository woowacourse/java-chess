package chess.domain.chessboard;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.chessPiece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenBlack")
    void findPieceByPositionTestQueenBlack() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.ONE))).isInstanceOf(Queen.class);
    }

    @Test
    @DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : null")
    void findPieceByPositionTestNull() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.THREE))).isEqualTo(null);
    }


    @Test
    @DisplayName("포지션에 맞는 피스를 찾는 기능 테스트 : QueenWhite")
    void findPieceByPositionTestQueenWhite() {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.findPieceByPosition(Position.of(File.D, Rank.EIGHT))).isInstanceOf(Queen.class);
    }
}
