package domain.chessGame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("ScoreCalculator는 ")
class ScoreCalculatorTest {

    @Test
    @DisplayName("각 색깔 진영의 기본 점수를 계산할 수 있다.")
    void calculateBasicScoreTest() {
        // given
        ChessBoardGenerator generator = new ChessBoardGenerator();
        ChessBoard chessBoard = new ChessBoard(generator.generate());

        // when
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard.getBlackPieces(), chessBoard.getWhitePieces());

        // then
        assertThat(scoreCalculator.getBlackScore()).isEqualTo(38);
        assertThat(scoreCalculator.getWhiteScore()).isEqualTo(38);
    }

    @Nested
    @DisplayName("진행중인 게임 현황으로 score를 계산할 수 있다.")
    class CalculateGameScoreInProgress {
        Map<Position, Piece> setupBoard = Map.ofEntries(
                entry(Position.of(8,2), new King(Color.BLACK)),
                entry(Position.of(8,3), new Rook(Color.BLACK)),
                entry(Position.of(7,1), new BlackPawn()),
                entry(Position.of(7,3), new BlackPawn()),
                entry(Position.of(7,4), new Bishop(Color.BLACK)),
                entry(Position.of(6,2), new BlackPawn()),
                entry(Position.of(6,5), new Queen(Color.BLACK)),
                entry(Position.of(4,6), new Knight(Color.WHITE)),
                entry(Position.of(4,7), new Queen(Color.WHITE)),
                entry(Position.of(3,6), new WhitePawn()),
                entry(Position.of(3,8), new WhitePawn()),
                entry(Position.of(2,6), new WhitePawn()),
                entry(Position.of(2,7), new WhitePawn()),
                entry(Position.of(1,5), new Rook(Color.WHITE)),
                entry(Position.of(1,6), new King(Color.WHITE))
        );

        @Test
        @DisplayName("검은색 진영의 점수는 20점이다.")
        void CalculateBlackGameScore() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Map<Position, Piece> blackPieces = chessBoard.getBlackPieces();
            Map<Position, Piece> whitePieces = chessBoard.getWhitePieces();
            ScoreCalculator scoreCalculator = new ScoreCalculator(blackPieces, whitePieces);

            // when
            double score = scoreCalculator.getBlackScore();

            // then
            assertThat(score).isEqualTo(20);
        }

        @Test
        @DisplayName("흰색 진영의 점수는 19.5점이다.")
        void CalculateWhiteGameScore() {
            // given
            ChessBoard chessBoard = new ChessBoard(setupBoard);
            Map<Position, Piece> blackPieces = chessBoard.getBlackPieces();
            Map<Position, Piece> whitePieces = chessBoard.getWhitePieces();
            ScoreCalculator scoreCalculator = new ScoreCalculator(blackPieces, whitePieces);

            // when
            double score = scoreCalculator.getWhiteScore();

            // then
            assertThat(score).isEqualTo(19.5);
        }
    }
}
