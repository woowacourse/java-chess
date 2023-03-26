package chess.utils;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class StringToBoardTest {

    @Test
    @DisplayName("List<String>을 chessBoard로 만들 수 있다.")
    void stringToBoardTest() {
        // given
        List<String> chessBoardText = new ArrayList<>(List.of("P..p", "Rkq"));
        Map<Position, Piece> expectChessBoard = Map.of(
                Position.of(2, 1), new Pawn(Color.BLACK),
                Position.of(2, 4), new Pawn(Color.WHITE),
                Position.of(1, 1), new Rook(Color.BLACK),
                Position.of(1, 2), new King(Color.WHITE),
                Position.of(1, 3), new Queen((Color.WHITE))
        );

        // when
        Map<Position, Piece> convertedChessBoard = StringToBoard.convert(chessBoardText);

        // then
        assertThat(convertedChessBoard).isEqualTo(expectChessBoard);
    }

}