package chess.utils;

import org.junit.jupiter.api.Test;

import chess.domain.Coordinate;
import chess.domain.Count;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Pawn;
import chess.utils.exceptions.InvalidPieceNameException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {
    @Test
    void String_이름으로_Piece_생성하기() {
        assertThat(DataProcessor.piece("p")).isEqualTo(new Pawn(Team.WHITE));
    }

    @Test
    void 이름으로_Piece_생성_예외처리() {
        assertThrows(InvalidPieceNameException.class, () -> {
            DataProcessor.piece("d");
        });
    }

    @Test
    void 스트링으로_Position_생성하기() {
        assertThat(DataProcessor.position("a", "1")).isEqualTo(new Position(new Coordinate('a'), new Coordinate(1)));
    }

    @Test
    void Int로_Count객체_생성하기() {
        assertThat(DataProcessor.count(3)).isEqualTo(new Count(3));
    }
}