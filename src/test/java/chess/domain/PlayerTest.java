package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void validatePieceToMove() {
        Player whitePlayer = new Player(Color.WHITE);
        Position position = Position.from("a2");
        assertThatCode(() -> whitePlayer.pickStartPiece(position, new Pieces(new Pawn(Color.WHITE, position))))
                .doesNotThrowAnyException();
    }

    @Test
    void validatePieceToMoveError() {
        Player whitePlayer = new Player(Color.WHITE);
        Position position = Position.from("a7");
        assertThatThrownBy(() -> whitePlayer.pickStartPiece(Position.from("a7"), new Pieces(new Pawn(Color.BLACK, position))))
                .isInstanceOf(IllegalArgumentException.class);
    }

}