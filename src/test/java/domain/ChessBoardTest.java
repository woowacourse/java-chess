package domain;

import domain.piece.Empty;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fixture.PositionFixture.A2;
import static fixture.PositionFixture.A3;
import static fixture.PositionFixture.A4;
import static fixture.PositionFixture.A5;
import static fixture.PositionFixture.A6;
import static fixture.PositionFixture.A7;
import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    /*
    RNBQKBNR  8
    PPPPPPPP  7
    ........  6
    ........  5
    ........  4
    ........  3
    pppppppp  2
    rnbqkbnr  1

    abcdefgh
     */
    @DisplayName("경로에 있는 기물을 찾는다.")
    @Test
    void findPieces() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardCreator());
        Path path = new Path(List.of(A7, A6, A5, A4, A3, A2));

        Pieces pieces = chessBoard.findPieces(path);

        assertThat(pieces).isEqualTo(new Pieces(List.of(
                new Pawn(Side.BLACK),
                new Empty(),
                new Empty(),
                new Empty(),
                new Empty(),
                new Pawn(Side.WHITE)
        )));
    }
}
