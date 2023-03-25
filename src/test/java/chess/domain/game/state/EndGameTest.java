package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.ChessBoard;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndGameTest {

    @Test
    @DisplayName("왕이 존재하지 않으면 패배한다.")
    void calculateResultTest() {
        Map<Position, Piece> pieceByPosition = Map.of(
                Position.of(File.A, Rank.TWO), new Pawn(Camp.WHITE),
                Position.of(File.A, Rank.THREE), new Pawn(Camp.WHITE),
                Position.of(File.A, Rank.FOUR), new King(Camp.WHITE),

                Position.of(File.B, Rank.ONE), new Knight(Camp.BLACK),
                Position.of(File.B, Rank.THREE), new Rook(Camp.BLACK)
        );
        ChessBoard chessBoard = new ChessBoard(pieceByPosition);
        ChessGame endGame = new EndGame(chessBoard);

        GameResult gameResult = endGame.calculateResult();
        assertThat(gameResult.getMatchResult())
                .isEqualTo(MatchResult.WHITE_WIN);
    }
}
