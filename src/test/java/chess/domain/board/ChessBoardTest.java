package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.piece.PositionTexture.A1;
import static chess.domain.piece.PositionTexture.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());
    }

    @DisplayName("ChessBoard 객체를 생성한다.")
    @Test
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard(ChessBoardFactory.initializeBoard()))
                .doesNotThrowAnyException();
    }

    @DisplayName("ChessBoard 생성 후, 기물 위치 확인 테스트 .")
    @Test
    void checkPiecePosition() {
        Map<Position, Piece> board = chessBoard.getBoard();

        assertThat(board.get(A1)).isInstanceOf(Rook.class);
        assertThat(board.get(A1)).isEqualTo(Rook.from("r", A1));
        assertThat(board.get(A2)).isInstanceOf(Pawn.class);
        assertThat(board.get(A2)).isEqualTo(Pawn.from("p", A2));
    }

    @DisplayName("ChessBoard에서 기물을 찾는다.")
    @Test
    void findPiece() {
        Piece pawn = chessBoard.findPiece(A2);

        assertThat(pawn).isInstanceOf(Pawn.class);
    }
}
