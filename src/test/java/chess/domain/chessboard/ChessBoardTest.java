package chess.domain.chessboard;

import chess.domain.piece.SquareState;
import chess.domain.piece.Team;
import chess.domain.piece.state.*;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.SquareCoordinates.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessBoardTest {
    private static final Rook ROOK_BLACK = new Rook(Team.BLACK);
    private static final Knight KNIGHT_BLACK = new Knight(Team.BLACK);
    private static final Bishop BISHOP_BLACK = new Bishop(Team.BLACK);
    private static final Queen QUEEN_BLACK = new Queen(Team.BLACK);
    private static final King KING_BLACK = new King(Team.BLACK);
    private static final Pawn PAWN_BLACK = new Pawn(Team.BLACK);

    @Test
    void 체스판은_64개의_스퀘어를_가진다() {
        assertThat(new ChessBoard(ChessFactory.create()))
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(SquareState.class))
                .hasSize(64);
    }

    @Test
    void 체스판은_각_기물을_규칙에_맞게_배치한다() {
        assertThat(new ChessBoard(ChessFactory.create()))
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(SquareState.class))
                .first()
                .isInstanceOf(Rook.class);
    }

    @Test
    void 왕을_잡았을때_True를_반환한다() {
        ChessBoard chessBoard = new ChessBoard(ChessFactory.create());
        assertThat(chessBoard.isKingDead()).isFalse();

        //Shortest way for checkmate
        chessBoard.move(F2, F3);
        chessBoard.move(E7, E5);
        chessBoard.move(G2, G4);
        chessBoard.move(D8, H4);
        chessBoard.move(H2, H3);
        chessBoard.move(H4, E1);

        assertThat(chessBoard.isKingDead()).isTrue();
    }

    @Test
    void 같은_팀의_말을_모두_반환한다() {
        ChessBoard chessBoard = new ChessBoard(ChessFactory.create());

        assertThat(chessBoard.getPiecesOf(Team.BLACK)).containsExactly(
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                PAWN_BLACK, PAWN_BLACK, PAWN_BLACK, PAWN_BLACK,
                ROOK_BLACK, KNIGHT_BLACK, BISHOP_BLACK, QUEEN_BLACK,
                KING_BLACK, BISHOP_BLACK, KNIGHT_BLACK, ROOK_BLACK);
    }

    @Test
    void 같은_팀의_더블폰의_수를_반환한다_없는_경우() {
        ChessBoard chessBoard = new ChessBoard(ChessFactory.create());

        assertThat(chessBoard.countDoublePawnOf(Team.BLACK)).isEqualTo(0);
    }

    @Test
    void 같은_팀의_더블폰의_수를_반환한다_있는_경우() {
        ChessBoard chessBoard = new ChessBoard(ChessFactory.create());
        chessBoard.move(A2, A4);
        chessBoard.move(B7, B5);
        chessBoard.move(A4, B5);

        assertThat(chessBoard.countDoublePawnOf(Team.WHITE)).isEqualTo(2);
    }
}
