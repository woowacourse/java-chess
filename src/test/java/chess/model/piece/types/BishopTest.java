package chess.model.piece.types;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.piece.Piece;
import chess.model.position.Column;
import chess.model.position.Position;
import chess.model.position.Row;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BishopTest {

    @Test
    @DisplayName("비숍의 방향 내에 존재하는 지 확인: 4대각선")
    void isNotWithInDirection() {

        // given
        Color anyColor = Color.WHITE;
        Bishop bishop = new Bishop(anyColor);

        Row srcRow = Row.findByNumber(2);
        Column srcCol = Column.findByNumber(3);
        Position src = new Position(srcRow, srcCol);

        Row dest1Row = Row.findByNumber(3);
        Column dest1Col = Column.findByNumber(4);
        Position dest1 = new Position(dest1Row, dest1Col);

        Row dest2Row = Row.findByNumber(1);
        Column dest2Col = Column.findByNumber(4);
        Position dest2 = new Position(dest2Row, dest2Col);

        Row dest3Row = Row.findByNumber(3);
        Column dest3Col = Column.findByNumber(2);
        Position dest3 = new Position(dest3Row, dest3Col);

        Row dest4Row = Row.findByNumber(1);
        Column dest4Col = Column.findByNumber(2);
        Position dest4 = new Position(dest4Row, dest4Col);

        Row dest5Row = Row.findByNumber(2);
        Column dest5Col = Column.findByNumber(5);
        Position dest5 = new Position(dest5Row, dest5Col);

        // when

        boolean able1Result = bishop.isNotWithInDirection(src, dest1);
        boolean able2Result = bishop.isNotWithInDirection(src, dest2);
        boolean able3Result = bishop.isNotWithInDirection(src, dest3);
        boolean able4Result = bishop.isNotWithInDirection(src, dest4);
        boolean unableResult = bishop.isNotWithInDirection(src, dest5);

        // then
        Assertions.assertAll(
                () -> org.assertj.core.api.Assertions.assertThat(able1Result).isTrue(),
                () -> org.assertj.core.api.Assertions.assertThat(able2Result).isTrue(),
                () -> org.assertj.core.api.Assertions.assertThat(able3Result).isTrue(),
                () -> org.assertj.core.api.Assertions.assertThat(able4Result).isTrue(),
                () -> org.assertj.core.api.Assertions.assertThat(unableResult).isFalse()
        );

    }

    @Test
    void isWithInRangeByMovement() {
    }

    @Test
    @DisplayName("비숍의 이동 방향 에 장애물이 있는 지: 없어야 함")
    void passFilter() {

        // given
        Row obstacleRow = Row.findByNumber(3);
        Column obstacleCol = Column.findByNumber(4);
        Position obstaclePosition = new Position(obstacleRow, obstacleCol);

        Map<Position, Piece> board = new HashMap<>();
        Color anyColor = Color.BLACK;
        Knight anyPiece = new Knight(anyColor);
        board.put(obstaclePosition, anyPiece);

        ChessBoard chessBoard = new ChessBoard(board);


//        Color anyColor = Color.WHITE;
//        Bishop bishop = new Bishop(anyColor);

        Row srcRow = Row.findByNumber(2);
        Column srcCol = Column.findByNumber(3);
        Position src = new Position(srcRow, srcCol);

        Row dest1Row = Row.findByNumber(4);
        Column dest1Col = Column.findByNumber(5);
        Position dest1 = new Position(dest1Row, dest1Col);

        // when


        // then
    }
}
