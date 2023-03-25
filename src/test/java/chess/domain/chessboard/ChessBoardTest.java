package chess.domain.chessboard;

import chess.domain.piece.SquareState;
import chess.domain.piece.state.Rook;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.SquareCoordinates.*;
import static chess.domain.SquareCoordinates.E1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessBoardTest {

    @Test
    void 체스판은_64개의_스퀘어를_가진다() {
        assertThat(new ChessBoard())
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(SquareState.class))
                .hasSize(64);
    }

    @Test
    void 체스판은_각_기물을_규칙에_맞게_배치한다() {
        assertThat(new ChessBoard())
                .extracting("squares")
                .asInstanceOf(InstanceOfAssertFactories.list(SquareState.class))
                .first()
                .isInstanceOf(Rook.class);
    }

    @Test
    void 왕을_잡았을때_True를_반환하는지_확인(){
        ChessBoard chessBoard = new ChessBoard();
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
}
