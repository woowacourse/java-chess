package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.movestrategy.BishopMoveStrategy;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessPieceTest {

    @Test
    void 진영이_검정색이면_True를_반환한다() {
        //given
        Camp camp = Camp.BLACK;
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(ChessPieceType.BISHOP, new BishopMoveStrategy());
        ChessPiece chessPiece = new ChessPiece(camp, chessPieceProperty);

        //when
        boolean isBlackCamp = chessPiece.getCamp().equals(Camp.BLACK);

        //then
        assertThat(isBlackCamp).isTrue();
    }

    @Test
    void 진영이_검정색이_아니면_False를_반환한다() {
        //given
        Camp camp = Camp.WHITE;
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(ChessPieceType.BISHOP, new BishopMoveStrategy());
        ChessPiece chessPiece = new ChessPiece(camp, chessPieceProperty);

        //when
        boolean isBlackCamp = chessPiece.getCamp().equals(Camp.BLACK);

        //then
        assertThat(isBlackCamp).isFalse();
    }

    @Test
    void 진영이_흰색이면_True를_반환한다() {
        //given
        Camp camp = Camp.WHITE;
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(ChessPieceType.BISHOP, new BishopMoveStrategy());
        ChessPiece chessPiece = new ChessPiece(camp, chessPieceProperty);

        //when
        boolean isWhiteCamp = chessPiece.getCamp().equals(Camp.WHITE);

        //then
        assertThat(isWhiteCamp).isTrue();
    }

    @Test
    void 진영이_흰색이_아니면_False를_반환한다() {
        //given
        Camp camp = Camp.BLACK;
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(ChessPieceType.BISHOP, new BishopMoveStrategy());
        ChessPiece chessPiece = new ChessPiece(camp, chessPieceProperty);

        //when
        boolean isWhiteCamp = chessPiece.getCamp().equals(Camp.WHITE);

        //then
        assertThat(isWhiteCamp).isFalse();
    }
}
