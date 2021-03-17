package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PositionTexture;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.IntStream;

import static chess.domain.piece.PositionTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ChessBoardTest {
    @DisplayName("ChessBoard 객체를 생성한다.")
    @Test
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard(ChessBoardFactory.initializeBoard()))
                .doesNotThrowAnyException();
    }

    @DisplayName("ChessBoard 생성 후, 기물 위치 확인 테스트 .")
    @Test
    void checkPiecePosition() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.initializeBoard());

        Map<Position, Piece> board = chessBoard.getBoard();

        assertThat(board.get(A1)).isInstanceOf(Rook.class);
        assertThat(board.get(A1)).isEqualTo(Rook.from("r", A1));
        assertThat(board.get(B2)).isInstanceOf(Pawn.class);
        assertThat(board.get(B2)).isEqualTo(Pawn.from("p", B2));
    }
}
