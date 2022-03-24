package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.chessboard.ChessBoard;
import domain.chessboard.ChessBoardGenerator;
import domain.position.Column;
import domain.position.Position;
import domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @Test
    @DisplayName("각 기물들은 고유 특성에 따라 이동할 수 있다.")
    void movePiece() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = new Position(Row.TWO, Column.A);
        Position target = new Position(Row.THREE, Column.A);

        chessBoard.move(source, target);
        assertThat(chessBoard.getSymbol(source)).isEqualTo(".");
        assertThat(chessBoard.getSymbol(target)).isEqualTo("p");
    }

    @Test
    @DisplayName("각 기물들은 이동할 수 있는 위치에 같은 색 말이 있다면 이동할 수 없다.")
    void doNotMoveSameColor() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = new Position(Row.ONE, Column.A);
        Position target = new Position(Row.TWO, Column.A);

        assertThatThrownBy(() -> chessBoard.move(source, target))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("각 기물들은 이동할 수 있는 위치에 다른 색 말이 있다면 이동할 수 있다.")
    void MoveDifferentColor() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());

        Position source = new Position(Row.ONE, Column.A);
        Position target = new Position(Row.SEVEN, Column.A);
        chessBoard.move(source, target);

        assertThat(chessBoard.getSymbol(source)).isEqualTo(".");
        assertThat(chessBoard.getSymbol(target)).isEqualTo("r");
    }
}
