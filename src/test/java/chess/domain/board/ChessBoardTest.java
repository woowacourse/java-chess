package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CurrentTurn;
import chess.domain.EmptySquaresMaker;
import chess.domain.position.File;
import chess.domain.position.Path;
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
    @DisplayName("체스말을 움직일 때, 시작 위치에 아군 말이 존재하지 않는 경우 예외를 발생시킨다.")
    @Test
    void startEmptyExceptionTest() {
        // given
        ChessBoard chessBoard = new ChessBoard(EmptySquaresMaker.make(), new CurrentTurn(Color.WHITE));
        Path path = new Path(new Position(Rank.FIRST, File.A), new Position(Rank.SECOND, File.B));

        // when & then
        assertThatThrownBy(() -> chessBoard.move(path))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 위치에 아군 체스말이 존재해야 합니다.");
    }

    @DisplayName("target이 비어있는 경우 체스말을 움직인다.")
    @Test
    void movePieceTest() {
        // given
        final Map<Position, Square> squares = EmptySquaresMaker.make();
        squares.put(new Position(Rank.FIRST, File.A), Rook.from(Color.WHITE));
        ChessBoard chessBoard = new ChessBoard(squares, new CurrentTurn(Color.WHITE));
        Map<Position, Square> expected = EmptySquaresMaker.make();
        expected.put(new Position(Rank.FIRST, File.B), Rook.from(Color.WHITE));

        // when
        chessBoard.move(new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.B)));

        // then
        assertThat(chessBoard.getSquares()).isEqualTo(expected);
    }

    @DisplayName("target이 체스말인 경우 공격한다.")
    @Test
    void moveAttackTest() {
        // given
        final Map<Position, Square> squares = EmptySquaresMaker.make();
        squares.put(new Position(Rank.FIRST, File.A), Rook.from(Color.WHITE));
        squares.put(new Position(Rank.FIRST, File.B), Queen.from(Color.BLACK));
        ChessBoard chessBoard = new ChessBoard(squares, new CurrentTurn(Color.WHITE));
        Map<Position, Square> expected = EmptySquaresMaker.make();
        expected.put(new Position(Rank.FIRST, File.B), Rook.from(Color.WHITE));

        // when
        chessBoard.move(new Path(new Position(Rank.FIRST, File.A), new Position(Rank.FIRST, File.B)));

        // then
        assertThat(chessBoard.getSquares()).isEqualTo(expected);
    }
}
