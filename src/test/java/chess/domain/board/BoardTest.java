package chess.domain.board;

import chess.domain.route.Path;
import chess.domain.route.Pieces;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.fixture.PieceFixture.BLACK_PAWN;
import static chess.fixture.PieceFixture.EMPTY;
import static chess.fixture.PieceFixture.WHITE_PAWN;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.A5;
import static chess.fixture.PositionFixture.A6;
import static chess.fixture.PositionFixture.A7;
import static chess.fixture.PositionFixture.B2;
import static chess.fixture.PositionFixture.B3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BoardTest {

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
        Board board = new Board(new BoardCreator());
        Path path = new Path(List.of(A7, A6, A5, A4, A3, A2));

        Pieces pieces = board.findPieces(path);

        assertThat(pieces).isEqualTo(new Pieces(List.of(
                BLACK_PAWN, EMPTY, EMPTY, EMPTY, EMPTY, WHITE_PAWN
        )));
    }

    @DisplayName("기물을 source 위치에서 target 위치로 옮긴다.")
    @Test
    void move() {
        Board board = new Board(new BoardCreator());

        board.move(B2, B3);

        assertAll(
                () -> assertThat(board.findPiece(B2)).isEqualTo(EMPTY),
                () -> assertThat(board.findPiece(B3)).isEqualTo(WHITE_PAWN)
        );
    }
}
