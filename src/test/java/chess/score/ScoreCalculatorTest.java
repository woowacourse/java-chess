package chess.score;

import chess.board.ChessBoard;
import chess.board.ChessBoardCreater;
import chess.game.ChessSet;
import chess.location.Location;
import chess.piece.type.Piece;
import chess.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {
    @Test
    @DisplayName("점수 계산")
    void calculateScore() {
        ChessBoard chessBoard = ChessBoardCreater.create();
        Map<Location, Piece> chessBoardPieces = chessBoard.giveMyPiece(Team.WHITE);
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        ChessSet chessSet = new ChessSet(chessBoardPieces);
        assertThat(scoreCalculator.calculate(chessSet))
                .isEqualTo(new Score(38));
    }
}