package chess.utils;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardToStringTest {

    @Test
    @DisplayName("chessBoard를 String으로 변환할 수 있다.")
    void boardToStringTest() {
        // given
        Map<Position, Piece> chessBoard = Map.of(
                Position.of(1, 1), new Rook(Color.BLACK),
                Position.of(8, 3), new Bishop(Color.WHITE),
                Position.of(8, 5), new King(Color.BLACK),
                Position.of(1, 7), new Knight(Color.WHITE),
                Position.of(4, 4), new Pawn(Color.BLACK),
                Position.of(5, 3), new Pawn(Color.WHITE)
        );

        List<String> expectStrings = List.of(
                "..b.K...",
                "........",
                "........",
                "..p.....",
                "...P....",
                "........",
                "........",
                "R.....n."
        );

        // when
        List<String> convertedStrings = BoardToString.convert(chessBoard);

        // then
        assertThat(convertedStrings).isEqualTo(expectStrings);

    }

}