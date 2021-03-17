package chess.domain.board;

import chess.domain.board.ChessBoard;
import chess.domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThatCode;

class ChessBoardTest {
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    @BeforeEach
    void setUp() {
        whitePieces = Arrays.asList(King.from("k", B2), Bishop.from("b", A1));
        blackPieces = Arrays.asList(Queen.from("Q", C3), Rook.from("R", D4));
    }

    @DisplayName("ChessBoard 객체를 생성한다.")
    @Test
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard(whitePieces, blackPieces))
                .doesNotThrowAnyException();
    }
}
