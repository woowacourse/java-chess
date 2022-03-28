package chess.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;
import chess.domain.piece.property.Color;

public class ChessBoardTest {

    private ChessBoard board;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        board.putPiece(Position.of(File.a, Rank.Seven), new Rook(Color.Black));
        board.putPiece(Position.of(File.b, Rank.Seven), new Rook(Color.White));
    }

    @Test
    @DisplayName("체스말을 움직이면 원래자리는 빈 자리가 된다.")
    void chessBoardTest1() {
        Position source = Position.of(File.a, Rank.Eight);
        Position target = Position.of(File.b, Rank.Eight);

        Piece rook = new Rook(Color.Black);
        board.putPiece(source, rook);
        board.move(source, target);

        assertThat(board.getPiece(source)).isNull();
    }

    @Test
    @DisplayName("source에 기물이 없을 경우 에러를 출력한다.")
    void chessBoardTest2() {
        Position source = Position.of(File.h, Rank.Eight);
        Position target = Position.of(File.a, Rank.Six);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("source와 target이 같을 경우 에러를 출력한다.")
    void chessBoardTest3() {
        Position source = Position.of(File.a, Rank.Eight);
        Position target = Position.of(File.a, Rank.Eight);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("")
    void computeScoreTest() {
        ChessBoard chessBoard = initScoreTestBoard();

        Map<Color, Double> scoreBoard = chessBoard.computeScore();
        assertThat(scoreBoard)
            .contains(entry(Color.White, 19.5), entry(Color.Black, 20.0));
    }

    private ChessBoard initScoreTestBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.putPiece(Position.of(File.b, Rank.Eight), new King(Color.Black));
        chessBoard.putPiece(Position.of(File.c, Rank.Eight), new Rook(Color.Black));
        chessBoard.putPiece(Position.of(File.a, Rank.Seven), new Pawn(Color.Black));
        chessBoard.putPiece(Position.of(File.c, Rank.Seven), new Pawn(Color.Black));
        chessBoard.putPiece(Position.of(File.d, Rank.Seven), new Bishop(Color.Black));
        chessBoard.putPiece(Position.of(File.b, Rank.Six), new Pawn(Color.Black));
        chessBoard.putPiece(Position.of(File.e, Rank.Six), new Queen(Color.Black));
        chessBoard.putPiece(Position.of(File.f, Rank.Four), new Knight(Color.White));
        chessBoard.putPiece(Position.of(File.g, Rank.Four), new Queen(Color.White));
        chessBoard.putPiece(Position.of(File.f, Rank.Three), new Pawn(Color.White));
        chessBoard.putPiece(Position.of(File.h, Rank.Three), new Pawn(Color.White));
        chessBoard.putPiece(Position.of(File.f, Rank.Two), new Pawn(Color.White));
        chessBoard.putPiece(Position.of(File.g, Rank.Two), new Pawn(Color.White));
        chessBoard.putPiece(Position.of(File.e, Rank.One), new Rook(Color.White));
        chessBoard.putPiece(Position.of(File.f, Rank.One), new King(Color.White));
        return chessBoard;
    }
}
