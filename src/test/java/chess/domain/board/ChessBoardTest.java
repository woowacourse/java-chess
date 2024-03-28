package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.PathFinder;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;
import chess.domain.square.piece.Color;
import chess.domain.square.piece.unified.Queen;
import chess.domain.square.piece.unified.Rook;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("target이 비어있는 경우 체스말을 움직인다.")
    @Test
    void movePieceTest() {
        final Map<Position, Square> squares = EmptySquaresMaker.make();
        squares.put(new Position(Rank.FIRST, File.A), Rook.from(Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(squares);
        Map<Position, Square> expected = EmptySquaresMaker.make();
        expected.put(new Position(Rank.FIRST, File.B), Rook.from(Color.WHITE));

        chessBoard.move(new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.B)));

        assertThat(chessBoard.getSquares()).isEqualTo(expected);
    }

    @DisplayName("target이 체스말인 경우 공격한다.")
    @Test
    void moveAttackTest() {
        final Map<Position, Square> squares = EmptySquaresMaker.make();
        squares.put(new Position(Rank.FIRST, File.A), Rook.from(Color.WHITE));
        squares.put(new Position(Rank.FIRST, File.B), Queen.from(Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(squares);
        Map<Position, Square> expected = EmptySquaresMaker.make();
        expected.put(new Position(Rank.FIRST, File.B), Rook.from(Color.WHITE));

        chessBoard.move(new PathFinder(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.B)));

        assertThat(chessBoard.getSquares()).isEqualTo(expected);
    }
}
