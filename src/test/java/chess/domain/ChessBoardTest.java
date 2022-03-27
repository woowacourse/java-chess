package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.property.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;

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
}
