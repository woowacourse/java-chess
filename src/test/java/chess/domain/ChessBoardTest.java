package chess.domain;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessBoardTest {

    @Test
    @DisplayName("생성 시 null 예외발생")
    void constructorNullException() {
        assertThatThrownBy(() -> new ChessBoard(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("pieces는 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("체스판 생성")
    void createNewChessBoard() {
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        assertThat(chessBoard.getPieces()).hasSize(32);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,false", "a,2,true"})
    @DisplayName("해당 위치가 비어있는지 확인")
    void isPositionEmpty(char col, char row, boolean expected) {
        Position position = new Position('a', '1');
        Piece piece = new Knight(WHITE, position);
        ChessBoard chessBoard = new ChessBoard(Map.of(position, piece));

        assertThat(chessBoard.isPositionEmpty(new Position(col, row))).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력 위치에 기물이 없을 때 예외발생")
    void exceptionPieceByPosition() {
        ChessBoard chessBoard = new ChessBoard(new HashMap<>());
        Position position = new Position('a', '1');

        assertThatThrownBy(() -> chessBoard.pieceByPosition(position))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치에 존재하는 기물이 없습니다.");
    }

    @Test
    @DisplayName("입력 위치에 기물 반환")
    void pieceByPosition() {
        Position position = new Position('a', '1');
        Piece piece = new Knight(WHITE, position);
        ChessBoard chessBoard = new ChessBoard(Map.of(position, piece));

        assertThat(chessBoard.pieceByPosition(position)).isEqualTo(piece);
    }
}
