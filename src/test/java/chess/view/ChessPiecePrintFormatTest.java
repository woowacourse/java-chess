package chess.view;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.Camp;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceProperty;
import chess.domain.chesspiece.ChessPieceType;
import chess.domain.chesspiece.movestrategy.KingMoveStrategy;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessPiecePrintFormatTest {

    @Test
    void 체스_말의_진영이_흰색인_경우_알맞은_표기법을_소문자로_반환한다() {
        Camp camp = Camp.WHITE;
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(ChessPieceType.KING, new KingMoveStrategy());
        ChessPiece chessPiece = new ChessPiece(camp, chessPieceProperty);
        assertThat(ChessPiecePrintFormat.findChessPieceNotation(chessPiece.createDto())).isEqualTo("k");
    }

    @Test
    void 체스_말의_진영이_검정색인_경우_알맞은_표기법을_대문자로_반환한다() {
        Camp camp = Camp.BLACK;
        ChessPieceProperty chessPieceProperty = new ChessPieceProperty(ChessPieceType.KING, new KingMoveStrategy());
        ChessPiece chessPiece = new ChessPiece(camp, chessPieceProperty);
        assertThat(ChessPiecePrintFormat.findChessPieceNotation(chessPiece.createDto())).isEqualTo("K");
    }
}
