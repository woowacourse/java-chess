package chess.domain.board;

import chess.domain.coordinate.Coordinate;
import chess.domain.coordinate.File;
import chess.domain.coordinate.Rank;
import chess.domain.manager.ChessManager;
import chess.domain.piece.Pieces;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {

    @DisplayName("체스말이 움직였는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"b1,c4", "b1,c9", "a1,a3", "a7,a2"})
    void moveTest(String sourceKey, String targetKey) {
        //given
        ForwardChessBoard chessBoard = BoardGenerator.create();
        ChessManager chessManager = new ChessManager(chessBoard);

        assertThatThrownBy(() -> chessBoard.move(sourceKey, targetKey))
                .isInstanceOf(IllegalArgumentException.class);
    }

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
}