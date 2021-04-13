package chess;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Point;
import chess.domain.Score;
import chess.domain.piece.kind.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTest {
    @DisplayName("점수 생성 및 초기 점수 확인")
    @Test
    void create() {
        ChessGame chessGame = new ChessGame();
        Score score = new Score(chessGame.getBoard());
        assertEquals(38, score.getWhiteScore());
        assertEquals(38, score.getBlackScore());
    }

    @DisplayName("폰이 겹쳤을 때 0.5로 계산하는 점수 확인")
    @Test
    void checkPawnScore() {
        ChessGame chessGame = new ChessGame();
        Board board = new Board(chessGame.getBoard());
        board.movePiece(Point.of("e2"), Point.of("e4"), WHITE);
        board.movePiece(Point.of("f7"), Point.of("f5"), BLACK);
        board.movePiece(Point.of("e4"), Point.of("f5"), WHITE);
        assertEquals(37, board.makeResult().getBlackScore());
    }
}
