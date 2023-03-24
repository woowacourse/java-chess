package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.result.GameResult;
import chess.domain.game.result.MatchResult;
import chess.domain.piece.Camp;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.ChessBoard;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndScoreGameTest {

    @Test
    @DisplayName("현재 체스 게임내의 점수를 구할 수 있다.")
    void calculateChessScoreTest() {
        Map<Position, Piece> pieceByPosition = new LinkedHashMap<>();
        pieceByPosition.put(Position.of(File.A, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.B, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.C, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.D, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.A, Rank.ONE), new Rook(Camp.WHITE));
        pieceByPosition.put(Position.of(File.B, Rank.ONE), new Knight(Camp.WHITE));

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        EndScoreGame endScoreGame = new EndScoreGame(chessBoard);

        assertThat(endScoreGame).extracting("whiteCampScore")
                .isEqualTo(11.5d);
    }

    @Test
    @DisplayName("폰이 동일한 파일에 존재하는 경우 모든 폰은 절반 점수로 반영된다.")
    void calculateChessScoreWhenPawnInSameFileTest() {
        Map<Position, Piece> pieceByPosition = new LinkedHashMap<>();
        pieceByPosition.put(Position.of(File.A, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.A, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.C, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.D, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.A, Rank.ONE), new Rook(Camp.WHITE));
        pieceByPosition.put(Position.of(File.B, Rank.ONE), new Knight(Camp.WHITE));

        ChessBoard chessBoard = new ChessBoard(pieceByPosition);

        EndScoreGame endScoreGame = new EndScoreGame(chessBoard);

        assertThat(endScoreGame).extracting("whiteCampScore")
                .isEqualTo(10.5d);
    }

    @Test
    @DisplayName("점수를 기준으로 게임의 승패를 판단한다.")
    void calculateResultWithScoreTest() {
        Map<Position, Piece> pieceByPosition = new LinkedHashMap<>();
        pieceByPosition.put(Position.of(File.A, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.A, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.C, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.D, Rank.TWO), new Pawn(Camp.WHITE));
        pieceByPosition.put(Position.of(File.A, Rank.ONE), new Rook(Camp.WHITE));
        pieceByPosition.put(Position.of(File.B, Rank.ONE), new Knight(Camp.WHITE));
        ChessBoard chessBoard = new ChessBoard(pieceByPosition);
        EndScoreGame endScoreGame = new EndScoreGame(chessBoard);

        GameResult gameResult = endScoreGame.calculateResult();

        assertThat(gameResult.peekScoreOfCamp(Camp.WHITE)).isEqualTo(10.5d);
        assertThat(gameResult.peekScoreOfCamp(Camp.BLACK)).isEqualTo(0d);

        assertThat(gameResult.getMatchResult()).isEqualTo(MatchResult.WHITE_WIN);
    }
}
