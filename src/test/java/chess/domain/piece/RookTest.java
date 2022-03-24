package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.ChessBoard;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;

public class RookTest {

    private ChessBoard board;

    @BeforeEach
    void initBoard() {
        board = new ChessBoard();
        board.putPiece(Position.of(File.a, Rank.Seven), new Rook(Color.Black));
        board.putPiece(Position.of(File.b, Rank.Seven), new Rook(Color.White));
    }

    @Test
    @DisplayName("룩이 가려는 포지션에 같은 색의 기물이 있다면 에러를 출력한다.")
    void pieceTest() {
        Position source = Position.of(File.a, Rank.Eight);
        Position target = Position.of(File.a, Rank.Seven);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("룩이 가려는 포지션에 다른 색의 기물이 있다면 기물을 잡고 그 위치로 이동한다.")
    void pieceTest2() {
        Position source = Position.of(File.b, Rank.Eight);
        Position target = Position.of(File.b, Rank.Seven);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);
        board.move(source, target);

        assertThat(board.getPiece(target)).isSameAs(piece);
    }

    @Test
    @DisplayName("룩이 대각선으로 이동할 경우 예외를 출력한다.")
    void pieceTest3() {
        Position source = Position.of(File.a, Rank.Eight);
        Position target = Position.of(File.b, Rank.Seven);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가려는 방향에서 기물이 있기 전까지의 위치는 이동이 가능하다.")
    void pieceTest4() {
        Position source = Position.of(File.a, Rank.Eight);
        Position target = Position.of(File.h, Rank.Eight);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);
        board.move(source, target);

        assertThat(board.getPiece(target)).isSameAs(piece);
    }

    @Test
    @DisplayName("가려는 방향에서 기물이 있는 곳 너머로 이동하는 경우 에러를 출력한다.")
    void pieceTest5() {
        Position source = Position.of(File.a, Rank.Eight);
        Position target = Position.of(File.a, Rank.Six);

        Piece piece = new Rook(Color.Black);
        board.putPiece(source, piece);

        assertThatThrownBy(() -> board.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
