package chess.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.WhitePawn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {

    @DisplayName("플레이어 색깔에 따른 점수를 계산한다.")
    @Test
    void score() {
        Board board = BoardFactory.initializeBoard();
        Result result = new Result(board, PieceColor.WHITE);
        assertThat(result.getWhiteScore()).isEqualTo(Score.MAX);
        assertThat(result.getBlackScore()).isEqualTo(Score.MAX);
    }

    @DisplayName("같은 행에 있는 폰들의 점수를 계산한다.")
    @Test
    void pawnScoreInSameColumn() {
        Board board = new Board();
        board.putPiece(new WhitePawn(), Position.of("a1"));
        board.putPiece(new WhitePawn(), Position.of("a2"));
        board.putPiece(new WhitePawn(), Position.of("a3"));
        Result result = new Result(board, PieceColor.WHITE);
        assertThat(result.getWhiteScore()).isEqualTo(new Score(1.5));
    }

    @DisplayName("같은 행에 있거나 있지 않은 폰들의 점수를 계산한다.")
    @Test
    void pawnScoreInDifferentColumn() {
        Board board = new Board();
        board.putPiece(new BlackPawn(), Position.of("a1"));
        board.putPiece(new BlackPawn(), Position.of("a2"));
        board.putPiece(new BlackPawn(), Position.of("b2"));
        board.putPiece(new BlackPawn(), Position.of("c3"));
        Result result = new Result(board, PieceColor.BLACK);
        Score totalScore = result.calculateTotalScore(PieceColor.BLACK);
        assertThat(totalScore).isEqualTo(new Score(3));
    }

    @DisplayName("킹이 없으면 승패가 결정된다.")
    @ParameterizedTest
    @CsvSource(value = {"e1:흑", "e8:백"}, delimiter = ':')
    void findWinner(String positionName, String colorName) {
        Board board = BoardFactory.initializeBoard();
        Result result = new Result(board, PieceColor.WHITE);
        assertThatIllegalArgumentException()
            .isThrownBy(result::findWinner)
            .withMessage("아직 승자가 정해지지 않았습니다.");
        Piece king = board.findPieceBy(Position.of(positionName));
        board.getCoordinates().remove(king);
        assertThat(result.findWinner()).isEqualTo(colorName + "이 이겼습니다.");
    }

}