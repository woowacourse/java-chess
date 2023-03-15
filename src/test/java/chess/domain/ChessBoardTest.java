package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessBoardTest {

    @DisplayName("체스판에 초기 말을 세팅한다.")
    @Test
    void 체스판_초기화() {
        ChessBoard chessBoard = new ChessBoard();

        Map<Position, Piece> positionPieces = chessBoard.piecesByPosition();

        assertThat(positionPieces).hasSize(32);
    }
}
