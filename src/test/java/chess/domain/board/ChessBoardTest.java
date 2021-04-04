package chess.domain.board;

import chess.domain.feature.Color;
import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
        chessBoard.initBoard();
    }

    @DisplayName("체스 초기 기물 배치 확인")
    @ParameterizedTest
    @CsvSource(value = {"0, 4, K", "7, 4, k", "1, 0, P", "6, 0, p", "5, 0, ."}, delimiter = ',')
    void pieceLocationCheck(int i, int j, String value) {
        assertThat(chessBoard.getPiece(Position.of(i, j)).getName()).isEqualTo(value);
    }

    @DisplayName("무승부일 때 점수 계산 제대로 하는지")
    @Test
    void scoreTest_draw() {
        chessBoard.move(Position.of("b2"), Position.of("b4"));
        chessBoard.move(Position.of("c7"), Position.of("c5"));
        chessBoard.move(Position.of("b4"), Position.of("c5"));
        assertThat(chessBoard.getScore(Color.WHITE)).isEqualTo(37.0);
        assertThat(chessBoard.getScore(Color.BLACK)).isEqualTo(37.0);
    }

    @DisplayName("white가 패배한 상황에서 점수 계산 제대로 하는지")
    @Test
    void scoreTest_whiteLose() {
        ChessBoard emptyChessBoard = new ChessBoard();

        emptyChessBoard.replace(Position.of("b8"), new King(Color.BLACK, Position.of("b8")));
        emptyChessBoard.replace(Position.of("c8"), new Rook(Color.BLACK, Position.of("c8")));
        emptyChessBoard.replace(Position.of("a7"), new Pawn(Color.BLACK, Position.of("a7")));
        emptyChessBoard.replace(Position.of("c7"), new Pawn(Color.BLACK, Position.of("c7")));
        emptyChessBoard.replace(Position.of("d7"), new Bishop(Color.BLACK, Position.of("d7")));
        emptyChessBoard.replace(Position.of("b6"), new Pawn(Color.BLACK, Position.of("b6")));
        emptyChessBoard.replace(Position.of("e6"), new Queen(Color.BLACK, Position.of("e6")));

        emptyChessBoard.replace(Position.of("f4"), new Knight(Color.WHITE, Position.of("f4")));
        emptyChessBoard.replace(Position.of("g4"), new Queen(Color.WHITE, Position.of("g4")));
        emptyChessBoard.replace(Position.of("f3"), new Pawn(Color.WHITE, Position.of("f3")));
        emptyChessBoard.replace(Position.of("h3"), new Pawn(Color.WHITE, Position.of("h3")));
        emptyChessBoard.replace(Position.of("f2"), new Pawn(Color.WHITE, Position.of("f2")));
        emptyChessBoard.replace(Position.of("g2"), new Pawn(Color.WHITE, Position.of("g2")));
        emptyChessBoard.replace(Position.of("e1"), new Rook(Color.WHITE, Position.of("e1")));
        emptyChessBoard.replace(Position.of("f1"), new King(Color.WHITE, Position.of("f1")));

        assertThat(emptyChessBoard.getScore(Color.BLACK)).isEqualTo(20.0);
        assertThat(emptyChessBoard.getScore(Color.WHITE)).isEqualTo(19.5);
    }
}
