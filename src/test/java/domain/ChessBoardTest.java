package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.chessboard.BoardGenerator;
import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import domain.piece.BlackPawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    @DisplayName("각 기물들은 이동할 수 있는 위치에 같은 색 말이 있다면 이동할 수 없다.")
    void runExceptionSameTargetSameColor() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = new Position(Row.ONE, Column.A);
        Position target = new Position(Row.TWO, Column.A);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 기물들은 이동할 수 있는 위치에 다른 색 말이 있다면 이동할 수 있다.")
    void MoveDifferentColor() {
        ChessBoard chessBoard = new ChessBoard(new BoardGenerator() {
            @Override
            public Map<Position, Piece> generate() {
                Map<Position, Piece> board = new HashMap<>();
                board.put(new Position(Row.ONE, Column.A), new Rook(Player.WHITE));
                board.put(new Position(Row.SEVEN, Column.A), new BlackPawn(Player.BLACK));
                return board;
            }
        });

        Position source = new Position(Row.ONE, Column.A);
        Position target = new Position(Row.SEVEN, Column.A);

        chessBoard.move(source, target);

        assertThat(chessBoard.getSymbol(source)).isEqualTo(".");
        assertThat(chessBoard.getSymbol(target)).isEqualTo("r");
    }

    @Test
    @DisplayName("각 기물들은 Target 위치까지 가는 경로에 말이 있다면 그 이상 이동할 수 없다.")
    void canNotMoveMore() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = new Position(Row.ONE, Column.A);
        Position target = new Position(Row.SEVEN, Column.A);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight는 다른 기물을 뛰어 넘어 이동할 수 있다.")
    void jumpPiece_Knight() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = new Position(Row.ONE, Column.B);
        Position target = new Position(Row.THREE, Column.C);

        assertDoesNotThrow(()->chessBoard.move(source, target));
    }
}
