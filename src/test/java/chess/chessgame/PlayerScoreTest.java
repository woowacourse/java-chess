package chess.chessgame;

import chess.chessboard.*;
import chess.piece.Pawn;
import chess.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerScoreTest {
    @Nested
    @DisplayName("from static 메서드는")
    class from {
        ChessBoard chessBoard;

        @BeforeEach
        void 보드초기화() {
            chessBoard = new ChessBoardFactory().generate();
        }

        @Test
        @DisplayName("각 기물별 점수를 더해서 PlayerScore를 반환한다")
        void it_returns_PlayerScore1() {
            final PlayerScore playerScore = PlayerScore.from(chessBoard.getPieces(Side.BLACK));
            assertThat(playerScore).isEqualTo(new PlayerScore(38));
        }

        @Test
        @DisplayName("Pawn이 한 세로줄에 여러개 있으면 하나당 0.5점으로 점수를 계산해서 PlayerScore를 반환한다")
        void it_returns_PlayerScore2() {
            final List<Pawn> pawns = Pawn.getPawnsOf(Side.BLACK);
            final Pawn pawn1 = pawns.get(0);
            final Pawn pawn2 = pawns.get(1);
            final Square square1 = Square.of(Rank.TWO, File.B);
            final Square square2 = Square.of(Rank.THREE, File.B);
            final Map<Square, Piece> pieces = Map.of(square1, pawn1, square2, pawn2);
            chessBoard = new ChessBoard(pieces);

            final PlayerScore actual = PlayerScore.from(chessBoard.getPieces(Side.BLACK));

            assertThat(actual).isEqualTo(new PlayerScore(1));
        }
    }
}
