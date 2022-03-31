package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Color;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionsTest {
    @ParameterizedTest(name = "출발지 : {0}, 도착지 : {1}, 기대값 : {2}")
    @CsvSource(value = {"E5,E7,90", "E5,G7,45", "E5,G5,0", "E5,G3,-45",
            "E5,E3,-90", "E5,C3,-135", "E5,C5,180",
            "E5,F7,63", "E5,G6,26", "E5,G4,-26", "E5,F3,-63",
            "E5,D3,-116", "E5,C4,-153", "E5,C6,153", "E5,D7,116"
    })
    @DisplayName("두 지점 간 각도 계산 테스트")
    void calculateAngleOfTwoPositions(String from, String to, int expected) {
        final int actual = Directions.calculateAngleOfTwoPositions(Position.from(from), Position.from(to));
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "폰 : {0} -> {1} : {2}")
    @CsvSource(value = {"A2,A3,true", "A2,A4,true", "B2,A3,true", "B2,C3,true",
            "A2,B2,false", "A2,A1,false", "A2,A1,false", "B2,A1,false", "B2,C1,false"})
    @DisplayName("폰은 앞으로 한 칸 전진하며, 첫 수 에만 두 칸 전진 가능하다. "
            + "적 기물이 있을 시 대각 한 칸 전진하며 잡을 수 있다."
            + "좌우 또는 뒤로는 이동 불가하다.")
    void isMovableDirectionForPawn(String rawFrom, String rawTo, boolean expected) {
        // given
        final Position from = Position.from(rawFrom);
        final Position to = Position.from(rawTo);

        // when
        final boolean isMovable = Directions.isMovableDirection(new Pawn(Color.WHITE), from, to);

        // then
        assertThat(isMovable).isEqualTo(expected);
    }

    @ParameterizedTest(name = "킹,퀸 : {0} -> {1} : {2}")
    @CsvSource(value = {"E5,E7,true", "E5,F6,true", "E5,F5,true", "E5,F4,true",
            "E5,E3,true", "E5,D4,true", "E5,C5,true", "E5,C7,true",
            "E5,F7,false", "E5,G6,false", "E5,G4,false", "E5,F3,false",
            "E5,D3,false", "E5,C4,false", "E5,C6,false", "E5,D7,false"})
    @DisplayName("킹은 한 칸, 퀸은 여러 칸으로 이동 거리는 다르지만, 방향은 동일하게 8방향 모두 가능하다.")
    void isMovableDirectionForQueenOrKing(String rawFrom, String rawTo, boolean expected) {
        // given
        final Position from = Position.from(rawFrom);
        final Position to = Position.from(rawTo);

        // when
        final boolean isMovableForKing = Directions.isMovableDirection(new King(Color.WHITE), from, to);
        final boolean isMovableQueen = Directions.isMovableDirection(new Queen(Color.WHITE), from, to);

        // then
        assertAll(
                () -> assertThat(isMovableForKing).isEqualTo(expected),
                () -> assertThat(isMovableQueen).isEqualTo(expected)
        );
    }

    @ParameterizedTest(name = "룩 : {0} -> {1} : {2}")
    @CsvSource(value = {"E5,E7,true", "E5,F6,false", "E5,F5,true", "E5,F4,false",
            "E5,E3,true", "E5,D4,false", "E5,C5,true", "E5,C7,false",
            "E5,F7,false", "E5,G6,false", "E5,G4,false", "E5,F3,false",
            "E5,D3,false", "E5,C4,false", "E5,C6,false", "E5,D7,false"})
    @DisplayName("룩은 전후좌우 4방향으로 이동 가능하다.")
    void isMovableDirectionForRook(String rawFrom, String rawTo, boolean expected) {
        // given
        final Position from = Position.from(rawFrom);
        final Position to = Position.from(rawTo);

        // when
        final boolean isMovableForRook = Directions.isMovableDirection(new Rook(Color.WHITE), from, to);

        // then
        assertThat(isMovableForRook).isEqualTo(expected);
    }

    @ParameterizedTest(name = "비숍 : {0} -> {1} : {2}")
    @CsvSource(value = {"E5,E7,false", "E5,F6,true", "E5,F5,false", "E5,F4,true",
            "E5,E3,false", "E5,D4,true", "E5,C5,false", "E5,C7,true",
            "E5,F7,false", "E5,G6,false", "E5,G4,false", "E5,F3,false",
            "E5,D3,false", "E5,C4,false", "E5,C6,false", "E5,D7,false"})
    @DisplayName("비숍은 가로축 대비 45도에 해당하는 대각선 4방향으로 이동 가능하다.")
    void isMovableDirectionForBishop(String rawFrom, String rawTo, boolean expected) {
        // given
        final Position from = Position.from(rawFrom);
        final Position to = Position.from(rawTo);

        // when
        final boolean isMovableForBishop = Directions.isMovableDirection(new Bishop(Color.WHITE), from, to);

        // then
        assertThat(isMovableForBishop).isEqualTo(expected);
    }

    @ParameterizedTest(name = "나이트 : {0} -> {1} : {2}")
    @CsvSource(value = {"E5,E7,false", "E5,F6,false", "E5,F5,false", "E5,F4,false",
            "E5,E3,false", "E5,D4,false", "E5,C5,false", "E5,C7,false",
            "E5,F7,true", "E5,G6,true", "E5,G4,true", "E5,F3,true",
            "E5,D3,true", "E5,C4,true", "E5,C6,true", "E5,D7,true"})
    @DisplayName("나이트는 가로축 2칸, 세로축 1칸 또는 가로축 1칸, 세로축 2칸에 해당하는 4방향으로 이동 가능하다.")
    void isMovableDirectionForKnight(String rawFrom, String rawTo, boolean expected) {
        // given
        final Position from = Position.from(rawFrom);
        final Position to = Position.from(rawTo);

        // when
        final boolean isMovableForKnight = Directions.isMovableDirection(new Knight(Color.WHITE), from, to);

        // then
        assertThat(isMovableForKnight).isEqualTo(expected);
    }
}
