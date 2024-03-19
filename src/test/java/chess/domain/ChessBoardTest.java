package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @DisplayName("체스보드의 특정위치에 기물이 없는 지 확인할 수 있다")
    @Test
    void should_CheckPositionEmptiness_When_GivenPosition() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        Position position = Position.of(1, 1);
        positionPiece.put(position, new King());
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThat(chessBoard.positionIsEmpty(Position.of(2, 2))).isTrue();
    }

    @DisplayName("체스보드의 특정위치에 기물이 있는 지 확인할 수 있다")
    @Test
    void should_CheckPositionNotEmptiness_When_GivenPosition() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        Position position = Position.of(1, 1);
        positionPiece.put(position, new King());
        ChessBoard chessBoard = new ChessBoard(positionPiece);

        assertThat(chessBoard.positionIsEmpty(Position.of(1, 1))).isFalse();
    }
}
