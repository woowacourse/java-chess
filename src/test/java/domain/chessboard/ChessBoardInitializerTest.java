package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.BlackPawn;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardInitializerTest {

    List<Row> board;
    Row row;

    @BeforeEach
    void setUp() {
        board = ChessBoardInitializer.createInitialBoard();
    }

    @DisplayName("맨 위의 행은 모두 흑색 말로 구성되어 있다.")
    @Test
    void firstRowPiecesIsAllBlack() {
        row = board.get(0);
        List<ChessPiece> pieces = row.getPieces();

        assertThat(pieces).allMatch(piece -> piece.hasSameColor(Color.BLACK));
    }

    @DisplayName("위에서 두 번째 행은 모두 흑색 폰으로 구성되어 있다.")
    @Test
    void secondRowPiecesIsAllBlackPawn() {
        row = board.get(1);
        List<ChessPiece> pieces = row.getPieces();

        assertThat(pieces).allMatch(piece -> piece instanceof BlackPawn);
    }

    @DisplayName("위에서 세 번째 행부터 여섯 번째 행은 모두 비어있다.")
    @Test
    void thirdToSixthRowPieceIsAllEmpty() {
        for (int i = 2; i <= 5; i++) {
            row = board.get(i);
            List<ChessPiece> pieces = row.getPieces();

            assertThat(pieces).allMatch(piece -> piece instanceof Blank);
        }
    }

    @DisplayName("아래에서 두 번째 행은 모두 흰색 폰으로 구성되어 있다.")
    @Test
    void seventhRowPieceIsAllWhitePawn() {
        row = board.get(6);
        List<ChessPiece> pieces = row.getPieces();

        assertThat(pieces).allMatch(piece -> piece instanceof WhitePawn);
    }

    @DisplayName("맨 아래의 행은 모두 흰색 말로 구성되어 있다.")
    @Test
    void eighthRowPieceIsAllWhite() {
        row = board.get(7);
        List<ChessPiece> pieces = row.getPieces();

        assertThat(pieces).allMatch(piece -> piece.hasSameColor(Color.WHITE));
    }
}
