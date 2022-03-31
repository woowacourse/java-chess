package chess.domain;

import static org.assertj.core.api.Assertions.*;

import chess.domain.piece.StartedPawn;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;

public class ChessBoardTest {

    private ChessBoard board;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        board.putPiece(Position.of(File.A, Rank.SEVEN), new Rook(Color.BLACK));
        board.putPiece(Position.of(File.B, Rank.SEVEN), new Bishop(Color.WHITE));
    }

    @Test
    @DisplayName("체스말을 움직이면 말이 이동한다.")
    void chessBoardTest1() {
        Position source = Position.of(File.A, Rank.EIGHT);
        Position target = Position.of(File.B, Rank.EIGHT);

        Piece rook = new Rook(Color.BLACK);
        board.putPiece(source, rook);
        board.move(source, target);

        assertThat(board.getPiece(target)).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("체스말을 움직이면 원래자리는 빈 자리가 된다.")
    void chessBoardTest2() {
        Position source = Position.of(File.A, Rank.EIGHT);
        Position target = Position.of(File.B, Rank.EIGHT);

        Piece rook = new Rook(Color.BLACK);
        board.putPiece(source, rook);
        board.move(source, target);

        assertThat(board.getPiece(source)).isNull();
    }

    @Test
    @DisplayName("source에 기물이 없을 경우 에러를 출력한다.")
    void chessBoardTest3() {
        Position source = Position.of(File.H, Rank.EIGHT);
        Position target = Position.of(File.A, Rank.SIX);

        Piece piece = new Rook(Color.BLACK);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("source와 target이 같을 경우 에러를 출력한다.")
    void chessBoardTest4() {
        Position source = Position.of(File.A, Rank.EIGHT);
        Position target = Position.of(File.A, Rank.EIGHT);

        Piece piece = new Rook(Color.BLACK);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("computeScore 메서드는 색깔별로 점수를 계산한다.")
    void computeScoreTest() {
        ChessBoard chessBoard = initScoreTestBoard();

        Map<Color, Double> scoreBoard = chessBoard.computeScore();
        assertThat(scoreBoard)
            .contains(entry(Color.WHITE, 19.5), entry(Color.BLACK, 20.0));
    }

    private ChessBoard initScoreTestBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.putPiece(Position.of(File.B, Rank.EIGHT), new King(Color.BLACK));
        chessBoard.putPiece(Position.of(File.C, Rank.EIGHT), new Rook(Color.BLACK));
        chessBoard.putPiece(Position.of(File.A, Rank.SEVEN), new StartedPawn(Color.BLACK));
        chessBoard.putPiece(Position.of(File.C, Rank.SEVEN), new StartedPawn(Color.BLACK));
        chessBoard.putPiece(Position.of(File.D, Rank.SEVEN), new Bishop(Color.BLACK));
        chessBoard.putPiece(Position.of(File.B, Rank.SIX), new StartedPawn(Color.BLACK));
        chessBoard.putPiece(Position.of(File.E, Rank.SIX), new Queen(Color.BLACK));
        chessBoard.putPiece(Position.of(File.F, Rank.FOUR), new Knight(Color.WHITE));
        chessBoard.putPiece(Position.of(File.G, Rank.FOUR), new Queen(Color.WHITE));
        chessBoard.putPiece(Position.of(File.F, Rank.THREE), new StartedPawn(Color.WHITE));
        chessBoard.putPiece(Position.of(File.H, Rank.THREE), new StartedPawn(Color.WHITE));
        chessBoard.putPiece(Position.of(File.F, Rank.TWO), new StartedPawn(Color.WHITE));
        chessBoard.putPiece(Position.of(File.G, Rank.TWO), new StartedPawn(Color.WHITE));
        chessBoard.putPiece(Position.of(File.E, Rank.ONE), new Rook(Color.WHITE));
        chessBoard.putPiece(Position.of(File.F, Rank.ONE), new King(Color.WHITE));
        return chessBoard;
    }
}
