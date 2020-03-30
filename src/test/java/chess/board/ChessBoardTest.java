package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {

    @DisplayName("체스판 하나의 열에 대한 점수 합산 확인")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_MOVED_PAWN,1", "BLACK_KING,1", "BLACK_QUEEN,10", "BLACK_ROOK,6", "BLACK_BISHOP,4", "BLACK_KNIGHT,3.5"})
    void calculateScore(Pieces pieces, double expect) {
        //given
        ChessBoard chessBoard = ChessBoard.empty();

        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.EIGHT), Pieces.BLACK_NOT_MOVED_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.SEVEN), pieces.getPiece()));

        //when
        double actual = chessBoard.calculateScore(Team.BLACK);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("이동 경로에 다른 체스말이 존재할 경우 Exception 발생")
    @Test
    void replace() {
        ChessBoard chessBoard = ChessBoard.empty();

        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.EIGHT), Pieces.BLACK_NOT_MOVED_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.SEVEN), Pieces.BLACK_NOT_MOVED_PAWN.getPiece()));

        assertThatThrownBy(() -> chessBoard.replace("a8", "a6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 체스말이 존재합니다.");
    }

    @DisplayName("목적지에 아군 체스말이 존재할 경우 Exception 발생")
    @Test
    void replace2() {
        ChessBoard chessBoard = ChessBoard.empty();

        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.EIGHT), Pieces.BLACK_NOT_MOVED_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.SEVEN), Pieces.BLACK_NOT_MOVED_PAWN.getPiece()));

        String targetKey = "a7";

        assertThatThrownBy(() -> chessBoard.replace("a8", targetKey))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%s 에 도달할 수 없습니다.", targetKey);
    }

    @DisplayName("정상적으로 이동이 성공하면 밀어낸 체스말을 반환하다.")
    @Test
    void replace3() {
        //given
        ChessBoard chessBoard = ChessBoard.empty();

        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.EIGHT), Pieces.WHITE_ROOK.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.SEVEN), Pieces.BLACK_NOT_MOVED_PAWN.getPiece()));

        //when
        String sourceKey = "a8";
        String targetKey = "a7";

        Piece replacedPiece = chessBoard.replace(sourceKey, targetKey);

        //then
        assertThat(replacedPiece).isEqualTo(Pieces.BLACK_NOT_MOVED_PAWN.getPiece());
    }

}