package chess.domain.piece.state;

import chess.config.BoardConfig;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.score.Score;
import chess.domain.position.Position;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PiecesTest {
    @Test
    @DisplayName("#initialize() : should return initialized Board")
    void initiaize() {
        Pieces pieces = Pieces.initialize();
        Map<String, String> serialized = pieces.serialize();
        assertThat(serialized.size()).isEqualTo(32);
        assertPawn(serialized, BoardConfig.LINE_START + 1, "p");
        assertPawn(serialized, BoardConfig.LINE_END - 1, "P");
        assertEdge(serialized, BoardConfig.LINE_START);
        assertEdge(serialized, BoardConfig.LINE_END);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForMovePieceSucceed"})
    void movePieceSucceed(Position from, Position to) {
        Pieces pieces = Pieces.initialize();
        pieces = pieces.movePiece(from, to);

        Pieces testPieces = TestPieces.initialize();
        Pieces movedPieces = testPieces.movePiece(from, to);

        assertThat(pieces).isEqualTo(movedPieces);

    }

    @ParameterizedTest
    @MethodSource({"getCasesForMovePieceFail"})
    void movePieceFail(Position from, Position to) {
        Pieces pieces = Pieces.initialize();
        assertThatThrownBy(() ->pieces.movePiece(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(Pieces.CAN_NOT_MOVE_ERROR, from, to));
    }

    @ParameterizedTest
    @MethodSource({"getCasesForIsNotFilled"})
    void isNotFilled(Position position, boolean expected) {
        Pieces pieces = Pieces.initialize();
        assertThat(pieces.isBlank(position)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForHasHindranceInStraightBetween"})
    void hasHindranceInStraightBetweenWithStraightDirection(Position from, Position to, boolean expected) {
        Pieces pieces = Pieces.initialize();
        boolean hasHindranceInBetween = pieces.hasHindranceInStraightBetween(from, to);
        assertThat(hasHindranceInBetween).isEqualTo(expected);
    }

    @Test
    void hasHindranceInStraightBetweenWithUnknownDirection() {
        Position from = Position.of(1, 2);
        Position to = Position.of(2,4);
        Pieces pieces = Pieces.initialize();
        assertThatThrownBy(() -> pieces.hasHindranceInStraightBetween(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(Pieces.NOT_STRAIGHT_ERROR, from, to));
    }

    @ParameterizedTest
    @MethodSource({"getCasesForIsSameTeam"})
    void isSameTeam(Position from, Position to, boolean expected) {
        Pieces pieces = Pieces.initialize();
        assertThat(pieces.isSameTeam(from, to)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForIsOppositeTeam"})
    void isOppositeTeam(Position from, Position to, boolean expected) {
        Pieces pieces = Pieces.initialize();
        assertThat(pieces.isOppositeTeam(from, to)).isEqualTo(expected);
    }

    @Test
    void isNotFinishedTrue() {
        Pieces pieces = Pieces.initialize();
        assertThat(pieces.isNotFinished()).isEqualTo(true);
    }

    @Test
    void isNotFinishedFalse() {
        Pieces pieces = Pieces.initialize();
        killBlackKingOnPurpose(pieces);
        assertThat(pieces.isNotFinished()).isEqualTo(false);
    }

    @Test
    void calculateScoreOf() {
        Pieces pieces = Pieces.initialize();
        Score score = pieces.calculateScoreOf(Team.WHITE);
        assertThat(score).isEqualTo(Score.of(38));
    }


    @Test
    void concludeResultWithInitializedPieces() {
        String initialScore = "38.0";
        Pieces pieces = Pieces.initialize();
        Result result = pieces.concludeResult();
        assertThat(result.getWinner()).isEqualTo(Team.NOT_ASSIGNED.toString());
        assertThat(result.getWhiteScore()).isEqualTo(initialScore);
        assertThat(result.getBlackScore()).isEqualTo(initialScore);
    }

    @Test
    void concludeResultWithWhiteWinPieces() {
        String whiteScore = "38.0";
        String blackScore = "37.0";
        Pieces pieces = Pieces.initialize();
        killBlackKingOnPurpose(pieces);
        Result result = pieces.concludeResult();
        assertThat(result.getWinner()).isEqualTo(Team.WHITE.toString());
        assertThat(result.getWhiteScore()).isEqualTo(whiteScore);
        assertThat(result.getBlackScore()).isEqualTo(blackScore);
    }

    @Test
    void serialize() {
        Map<Position, Piece> pieces = new HashMap<Position, Piece>() {{
            put(Position.of(1,1), new Rook(Team.WHITE));
        }};
        Pieces piecesState = new Pieces(pieces);
        Map<String, String> serialized = piecesState.serialize();
        assertThat(serialized.toString()).isEqualTo("{11=r}");
    }

    private void killBlackKingOnPurpose(Pieces pieces) {
        pieces.movePiece(Position.of(2,1), Position.of(3,3));
        pieces.movePiece(Position.of(3,3), Position.of(2,5));
        pieces.movePiece(Position.of(2,5), Position.of(3,7));
        pieces.movePiece(Position.of(3,7), Position.of(5,8));
    }

    private static Stream<Arguments> getCasesForIsSameTeam() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,2), true),
                Arguments.of(Position.of(1,1), Position.of(1,8), false)
        );
    }

    private static Stream<Arguments> getCasesForIsOppositeTeam() {
        return Stream.of(
                Arguments.of(Position.of(1,1), Position.of(1,2), false),
                Arguments.of(Position.of(1,1), Position.of(1,8), true)
        );
    }

    private static Stream<Arguments> getCasesForHasHindranceInStraightBetween() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,7), false),
                Arguments.of(Position.of(1,2), Position.of(6,7), false),
                Arguments.of(Position.of(1,2), Position.of(1,8), true),
                Arguments.of(Position.of(1,2), Position.of(7,8), true)
        );
    }

    private static Stream<Arguments> getCasesForIsNotFilled() {
        return Stream.of(
                Arguments.of(Position.of(1,1), false),
                Arguments.of(Position.of(1,3), true)
        );
    }

    private static Stream<Arguments> getCasesForMovePieceFail() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,5))
        );
    }

    private static Stream<Arguments> getCasesForMovePieceSucceed() {
        return Stream.of(
                Arguments.of(Position.of(1,2), Position.of(1,3)),
                Arguments.of(Position.of(1,2), Position.of(1,4)),
                Arguments.of(Position.of(2,1), Position.of(3,3))
        );
    }

    private void assertPawn(Map<String, String> serialized, int row, String p) {
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            String position = String.valueOf(column) + String.valueOf(row);
            assertTrue(serialized.containsKey(position));
            String name = serialized.get(position);
            assertThat(name).isEqualTo(p);
        }
    }

    private void assertEdge(Map<String, String> serialized, int row) {
        for (int column = BoardConfig.LINE_START; column <= BoardConfig.LINE_END; column++) {
            String position = String.valueOf(column) + String.valueOf(row);
            assertTrue(serialized.containsKey(position));
        }
    }
}