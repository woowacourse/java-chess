package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    private Map<Position, Piece> BOARD_MAP = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        BOARD_MAP = EmptyBoardGenerator.create();
    }

    @DisplayName("비숍 이동 가능")
    @ParameterizedTest(name = "(4,4)->({0},{1}) 가능")
    @CsvSource({"6,6", "2,2", "2,6", "6,2"})
    void canMove(int file, int rank) {
        Piece bishop = new Bishop(WHITE);
        Piece captured = new Knight(BLACK);

        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, bishop);
        BOARD_MAP.put(Position.of(6, 6), captured);

        assertThat(bishop.canMove(source, target, BOARD_MAP)).isTrue();
    }

    @DisplayName("비숍 이동 불가")
    @ParameterizedTest(name = "{0}: (4,4)->({1},{2}) 불가")
    @CsvSource({"이동 규칙 위반,4,6", "같은 위치,4,4", "자신의 말이 위치,6,6", "이동 경로에 말이 존재,2,2"})
    void cannotMove(String description, int file, int rank) {
        Piece bishop = new Bishop(WHITE);
        Piece ownPiece = new Pawn(WHITE);
        Piece pieceOnRoute = new Pawn(BLACK);

        Position source = Position.of(4, 4);
        Position target = Position.of(file, rank);

        BOARD_MAP.put(source, bishop);
        BOARD_MAP.put(Position.of(6, 6), ownPiece);
        BOARD_MAP.put(Position.of(3, 3), pieceOnRoute);

        assertThat(bishop.canMove(source, target, BOARD_MAP)).isFalse();
    }
}
