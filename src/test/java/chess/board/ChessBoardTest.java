package chess.board;

import chess.board.piece.Pieces;
import chess.board.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardTest {

    @DisplayName("체스말이 움직였는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"b1,c3,true", "b1,c4,false", "a1,a3,false", "a7,a6,true"})
    void moveTest(String sourceKey, String targetKey, boolean expect) {
        //given
        ChessBoard chessBoard = new BoardGenerator().create();

        //when
        boolean actual = chessBoard.move(sourceKey, targetKey);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("체스판 하나의 열에 대한 점수 합산 확인")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_PAWN,1", "BLACK_KING,1", "BLACK_QUEEN,10", "BLACK_ROOK,6", "BLACK_BISHOP,4", "BLACK_KNIGHT,3.5"})
    void calculateScore(Pieces pieces, double expect) {
        //given
        ChessBoard chessBoard = ChessBoard.empty();

        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.EIGHT), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.SEVEN), pieces.getPiece()));

        //when
        double actual = chessBoard.calculateScore(Team.BLACK);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}