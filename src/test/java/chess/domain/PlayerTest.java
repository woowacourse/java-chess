package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Player.OPPONENT_MOVE_ERROR);
    }

    @Test
    @DisplayName("내 말이 있는 곳으로 이동하려 할 때 익셉션을 잘 날리는지 확인")
    void validateSameColorKill() {
        final Player whitePlayer = new Player(Color.WHITE);
        final Position position = Position.from("a2");
        final Pieces pieces = new Pieces(new Pawn(Color.WHITE, Position.from("a2")),
                new Pawn(Color.WHITE, Position.from("a3")));

        assertThatThrownBy(() -> whitePlayer.move(position, Position.from("a3"), pieces))
                .isInstanceOf(IllegalArgumentException.class).hasMessageContaining(Player.CANT_KILL_MINE_ERROR);
    }

    @Test
    @DisplayName("내 말의 위치와 상대말의 위치를 입력했을 때 kill을 잘 수행하는지 확인")
    void kill() {
        final Player whitePlayer = new Player(Color.WHITE);
        final Position position = Position.from("a1");
        final Pieces pieces = new Pieces(new Rook(Color.WHITE, Position.from("a1")),
                new Pawn(Color.BLACK, Position.from("a7")));

        whitePlayer.move(position, Position.from("a7"), pieces);
        assertThat(pieces.getPieces().size()).isEqualTo(1);
    }
}