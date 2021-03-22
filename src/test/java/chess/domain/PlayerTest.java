package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    @DisplayName("화이트 플레이어가 이동을 원하는 기물로 자신의 말을 골랐을 때 잘 이동하는지 확인")
    void validatePieceToMove() {
        final Player whitePlayer = new Player(Color.WHITE);
        final Position position = Position.from("a2");
        assertThatCode(() -> whitePlayer.pickStartPiece(position, new Pieces(new Pawn(Color.WHITE, position))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("화이트 플레이어가 이동을 원하는 기물로 검정 말을 골랐을 때 익셉션을 날리는지 확인")
    void validatePieceToMoveError() {
        final Player whitePlayer = new Player(Color.WHITE);
        final Position position = Position.from("a7");
        assertThatThrownBy(() -> whitePlayer.pickStartPiece(Position.from("a7"), new Pieces(new Pawn(Color.BLACK, position))))
                .isInstanceOf(IllegalArgumentException.class);
    }
}