package domain.board;

import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.position.Position;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.Fixtures.*;
import static domain.piece.Color.BLACK;
import static domain.piece.Color.WHITE;
import static domain.piece.Type.BISHOP;
import static domain.piece.Type.KING;
import static domain.piece.Type.KNIGHT;
import static domain.piece.Type.PAWN;
import static domain.piece.Type.QUEEN;
import static domain.piece.Type.ROOK;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {
    @Test
    void 모든_피스가_존재하면_점수는_38점이다() {
        ChessBoard board = ChessBoardFactory.createInitialChessBoard();

        Score score = Score.calculate(board.getBoard());

        assertThat(score.getWhiteScore()).isEqualTo(38.0);
        assertThat(score.getBlackScore()).isEqualTo(38.0);
    }

    @Test
    void 같은_세로_줄에_폰이_3개_존재하면_일점오점_이다() {
        Map<Position, Piece> board = Map.of(A5, new WhitePawn(), A6, new WhitePawn(), A7, new WhitePawn(),
                A8, new King(WHITE));

        Score score = Score.calculate(board);

        double expectedWhiteScore = 0.5 * 3;
        assertThat(score.getWhiteScore()).isEqualTo(expectedWhiteScore);
    }

    @Test
    void 같은_세로_줄에_폰이_3개_그리고_2개_그리고_1개_존재하면_삼점오점_이다() {
        Map<Position, Piece> board = Map.of(
                A5, new WhitePawn(), A6, new WhitePawn(), A7, new WhitePawn(),
                B5, new WhitePawn(), B6, new WhitePawn(),
                C5, new WhitePawn(),
                A8, new King(WHITE)
        );

        Score score = Score.calculate(board);

        double expectedWhiteScore = 0.5 * 5 + 1.0;
        assertThat(score.getWhiteScore()).isEqualTo(expectedWhiteScore);
    }

    @Test
    void 일부_피스가_존재하는_경우에_점수를_계산할_수_있다() {
        var board = Map.ofEntries(
                Map.entry(A7, new WhitePawn()), Map.entry(B7, new WhitePawn()), Map.entry(C7, new WhitePawn()),
                Map.entry(D7, new WhitePawn()), Map.entry(E7, new WhitePawn()), Map.entry(F7, new WhitePawn()),
                Map.entry(A8, new Rook(WHITE)), Map.entry(B8, new Bishop(WHITE)), Map.entry(C8, new Knight(WHITE)),
                Map.entry(E8, new King(WHITE)), Map.entry(F8, new Queen(WHITE)),
                Map.entry(A2, new BlackPawn()), Map.entry(B2, new BlackPawn()), Map.entry(C2, new BlackPawn()),
                Map.entry(D2, new BlackPawn()), Map.entry(E2, new BlackPawn()),
                Map.entry(A1, new Rook(BLACK)), Map.entry(B1, new Bishop(BLACK)), Map.entry(C1, new Knight(BLACK)),
                Map.entry(H1, new Rook(BLACK)), Map.entry(G1, new Bishop(BLACK)), Map.entry(F1, new Knight(BLACK)),
                Map.entry(E1, new King(BLACK)), Map.entry(D1, new Queen(BLACK))
        );

        Score score = Score.calculate(board);

        double expectedWhiteScore = PAWN.score() * 6 + ROOK.score() * 1 + BISHOP.score() * 1 +
                KNIGHT.score() * 1 + KING.score() + QUEEN.score();
        double expectedBlackScore = PAWN.score() * 5 + ROOK.score() * 2 + BISHOP.score() * 2 +
                KNIGHT.score() * 2 + KING.score() + QUEEN.score();

        assertThat(score.getWhiteScore()).isEqualTo(expectedWhiteScore);
        assertThat(score.getBlackScore()).isEqualTo(expectedBlackScore);
    }
}
