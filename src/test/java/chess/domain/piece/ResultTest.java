package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ResultTest {

    @DisplayName("플레이어 색깔에 따른 점수를 계산한다.")
    @Test
    void score(){
        Board board = BoardFactory.initializeBoard();
        Result result = new Result(board);
        Score totalScore = result.calculateTotalScore(PieceColor.WHITE);
        assertThat(totalScore).isEqualTo(Score.MAX);
    }

    @DisplayName("같은 행에 있는 폰들의 점수를 계산한다.")
    @Test
    void pawnScoreInSameColumn(){
        Board board = new Board();
        board.putPiece(new Pawn(PieceColor.WHITE),Position.ofName("a1"));
        board.putPiece(new Pawn(PieceColor.WHITE),Position.ofName("a2"));
        board.putPiece(new Pawn(PieceColor.WHITE),Position.ofName("a3"));
        Result result = new Result(board);
        Score totalScore = result.calculateTotalScore(PieceColor.WHITE);
        assertThat(totalScore).isEqualTo(new Score(1.5));
    }

    @DisplayName("같은 행에 있거나 있지 않은 폰들의 점수를 계산한다.")
    @Test
    void pawnScoreInDifferentColumn(){
        Board board = new Board();
        board.putPiece(new Pawn(PieceColor.BLACK),Position.ofName("a1"));
        board.putPiece(new Pawn(PieceColor.BLACK),Position.ofName("a2"));
        board.putPiece(new Pawn(PieceColor.BLACK),Position.ofName("b2"));
        board.putPiece(new Pawn(PieceColor.BLACK),Position.ofName("c3"));
        Result result = new Result(board);
        Score totalScore = result.calculateTotalScore(PieceColor.BLACK);
        assertThat(totalScore).isEqualTo(new Score(3));
    }

    @DisplayName("킹이 없으면 승패가 결정된다.")
    @ParameterizedTest
    @CsvSource(value = {"e1:흑","e8:백"},delimiter = ':')
    void findWinner(String positionName, String colorName){
        Board board = BoardFactory.initializeBoard();
        Result result = new Result(board);
        assertThat(result.findWinner()).isEqualTo("아직 승자가 정해지지 않았습니다.");
        Piece king = board.findPieceBy(Position.ofName(positionName));
        board.getCoordinates().remove(king);
        assertThat(result.findWinner()).isEqualTo(colorName+"이 이겼습니다.");
    }

}