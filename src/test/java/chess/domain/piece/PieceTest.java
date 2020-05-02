package chess.domain.piece;

import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.score.Score;
import chess.domain.piece.state.PiecesState;
import chess.domain.piece.state.TestPiecesState;
import chess.domain.piece.team.Team;
import chess.domain.position.InitialColumn;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTest {

    //pawn
    private static final Position MOVED_PAWN_POSITION = Position.of(3, 3);
    private static final Position ENEMY_POSITION = Position.of(1, 3);

    //bishop
    private static final Position INITIAL_BISHOP_POSITION = Position.of(3, 1);
    private static final Position CURRENT_BISHOP_POSITION = Position.of(1, 3);

    //king
    private static final Position CURRENT_KING_POSITION = Position.of(5, 3);

    //queen
    private static final Position INITIAL_QUEEN_POSITION = Position.of(4, 1);
    private static final Position CURRENT_QUEEN_POSITION = Position.of(4, 3);

    //rook
    private static final Position INITIAL_ROOK_POSITION = Position.of(1, 1);
    private static final Position CURRENT_ROOK_POSITION = Position.of(1, 3);

    @ParameterizedTest
    @DisplayName("#bishopCanNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForPawnCanNotMove"})
    void pawnCanNotMove(Position from, Position to, Team team, boolean expected) {
        //given
        PiecesState testPiecesState = TestPiecesState.initialize();
        testPiecesState = testPiecesState.movePiece(Position.of(1, 7), ENEMY_POSITION);
        PiecesState piecesState = testPiecesState.movePiece(Position.of(3,2), MOVED_PAWN_POSITION);
        Piece initializedPawn = PieceFactory.createInitializedPawn(team);
        //when
        boolean canNotMove = initializedPawn.canNotMove(from, to, piecesState);
        //then
        assertThat(canNotMove).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#bishopCanNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForBishopCanNotMove"})
    void bishopCanNotMove(Position from, Position to, Team team, boolean expected) {
        Piece bishop = PieceFactory.createPieceWithInitialColumn(InitialColumn.BISHOP, team);

        PiecesState testPiecesState = TestPiecesState.initialize();
        PiecesState piecesState = testPiecesState.movePiece(INITIAL_BISHOP_POSITION, CURRENT_BISHOP_POSITION);

        boolean canNotMove = bishop.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }





    @ParameterizedTest
    @DisplayName("#kingCanNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForKingCanNotMove"})
    void kingCanNotMove(Position from, Position to, Team team, boolean expected) {
        final Position initialKingPosition = Position.of(5, 1);
        Piece king = PieceFactory.createPieceWithInitialColumn(InitialColumn.KING, team);

        PiecesState testPiecesState = TestPiecesState.initialize();
        PiecesState piecesState = testPiecesState.movePiece(initialKingPosition, CURRENT_KING_POSITION);

        boolean canNotMove = king.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#knightCanNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForKnightCanNotMove"})
    void knightCanNotMove(Position from, Position to, Team team, boolean expected) {
        Piece knight = PieceFactory.createPieceWithInitialColumn(InitialColumn.KNIGHT, team);

        PiecesState piecesState = TestPiecesState.initialize();

        boolean canNotMove = knight.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("#queenCanNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForQueenCanNotMove"})
    void queenCanNotMove(Position from, Position to, Team team, boolean expected) {
        Piece queen = PieceFactory.createPieceWithInitialColumn(InitialColumn.QUEEN, team);

        PiecesState testPiecesState = TestPiecesState.initialize();
        PiecesState piecesState = testPiecesState.movePiece(INITIAL_QUEEN_POSITION, CURRENT_QUEEN_POSITION);

        boolean canNotMove = queen.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }



    @ParameterizedTest
    @DisplayName("#rookCanNotMove() : should return boolean as to Position 'from', 'to' and team")
    @MethodSource({"getCasesForRookCanNotMove"})
    void rookCanNotMove(Position from, Position to, Team team, boolean expected) {
        Piece rook = PieceFactory.createPieceWithInitialColumn(InitialColumn.ROOK, team);

        PiecesState testPiecesState = TestPiecesState.initialize();
        PiecesState piecesState = testPiecesState.movePiece(INITIAL_ROOK_POSITION, CURRENT_ROOK_POSITION);

        boolean canNotMove = rook.canNotMove(from, to, piecesState);
        assertThat(canNotMove).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForPawnCalculateScore"})
    void pawnCalculateScore(boolean hasMultiplePeerAtFile, Score expected) {
        Piece piece = PieceFactory.createInitializedPawn(Team.WHITE);
        Score score = piece.calculateScore(() -> hasMultiplePeerAtFile);
        assertThat(score).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource({"getCasesForPieceWhichIsNotPawnCalculateScore"})
    void pieceWhichIsNotPawnCalculateScore(InitialColumn initialColumn, Score expected) {
        Piece pieceWhichIsNotPawn = PieceFactory.createPieceWithInitialColumn(initialColumn, Team.WHITE);
        Score score = pieceWhichIsNotPawn.calculateScore(null);
        assertThat(score).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForPawnCanNotMove() {
        final Position initialPawnPositionFirst = Position.of(1, 2);
        final Position initialPawnPositionSecond = Position.of(2, 2);
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(initialPawnPositionFirst,
                        initialPawnPositionFirst,
                        team,
                        true),
                Arguments.of(initialPawnPositionSecond,
                        Position.of(2, 5),
                        team,
                        true),
                Arguments.of(initialPawnPositionFirst,
                        Position.of(1, 4),
                        team,
                        true),
                Arguments.of(initialPawnPositionSecond,
                        Position.of(3, 3),
                        team,
                        true),
                Arguments.of(initialPawnPositionFirst,
                        initialPawnPositionSecond,
                        team,
                        true),
                Arguments.of(initialPawnPositionFirst,
                        ENEMY_POSITION,
                        team,
                        true),
                Arguments.of(MOVED_PAWN_POSITION,
                        Position.of(3, 5),
                        team,
                        true),

                Arguments.of(initialPawnPositionSecond,
                        ENEMY_POSITION,
                        team,
                        false),
                Arguments.of(initialPawnPositionSecond,
                        Position.of(2, 3),
                        team,
                        false),
                Arguments.of(initialPawnPositionSecond,
                        Position.of(2, 4),
                        team,
                        false),
                Arguments.of(MOVED_PAWN_POSITION,
                        Position.of(3, 4),
                        team,
                        false)

        );
    }

    private static Stream<Arguments> getCasesForBishopCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_BISHOP_POSITION,
                        CURRENT_BISHOP_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_BISHOP_POSITION,
                        INITIAL_BISHOP_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_BISHOP_POSITION,
                        Position.of(2, 2),
                        team,
                        true),
                Arguments.of(CURRENT_BISHOP_POSITION,
                        Position.of(2, 5),
                        team,
                        true),
                Arguments.of(CURRENT_BISHOP_POSITION,
                        Position.of(1, 5),
                        team,
                        true),
                Arguments.of(CURRENT_BISHOP_POSITION,
                        Position.of(2, 4),
                        team,
                        false)
        );
    }

    private static Stream<Arguments> getCasesForKingCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_KING_POSITION,
                        CURRENT_KING_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_KING_POSITION,
                        Position.of(5, 5),
                        team,
                        true),
                Arguments.of(CURRENT_KING_POSITION,
                        Position.of(5, 2),
                        team,
                        true),
                Arguments.of(CURRENT_KING_POSITION,
                        Position.of(5, 4),
                        team,
                        false)
        );
    }

    private static Stream<Arguments> getCasesForKnightCanNotMove() {
        final Position initialKinghtPosition = Position.of(2, 1);
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(initialKinghtPosition,
                        initialKinghtPosition,
                        team,
                        true),
                Arguments.of(initialKinghtPosition,
                        Position.of(3, 5),
                        team,
                        true),
                Arguments.of(initialKinghtPosition,
                        Position.of(4, 3),
                        team,
                        true),
                Arguments.of(initialKinghtPosition,
                        Position.of(4, 2),
                        team,
                        true),
                Arguments.of(initialKinghtPosition,
                        Position.of(3, 3),
                        team,
                        false)
        );
    }

    private static Stream<Arguments> getCasesForQueenCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_QUEEN_POSITION,
                        CURRENT_QUEEN_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        INITIAL_QUEEN_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        Position.of(4, 2),
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        Position.of(4, 2),
                        team,
                        true),
                Arguments.of(CURRENT_QUEEN_POSITION,
                        Position.of(6, 4),
                        team,
                        true)
        );
    }

    private static Stream<Arguments> getCasesForRookCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(CURRENT_ROOK_POSITION,
                        CURRENT_ROOK_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        INITIAL_ROOK_POSITION,
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(1, 2),
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(2, 4),
                        team,
                        true),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(1, 4),
                        team,
                        false),
                Arguments.of(CURRENT_ROOK_POSITION,
                        Position.of(1, 7),
                        team,
                        false)
        );
    }

    private static Stream<Arguments> getCasesForPawnCalculateScore() {
        return Stream.of(
                Arguments.of(true, Score.of(0.5)),
                Arguments.of(false, Score.of(1))
        );
    }

    private static Stream<Arguments> getCasesForPieceWhichIsNotPawnCalculateScore() {
        return Stream.of(
                Arguments.of(InitialColumn.ROOK, Score.of(5)),
                Arguments.of(InitialColumn.KNIGHT, Score.of(2.5)),
                Arguments.of(InitialColumn.BISHOP, Score.of(3)),
                Arguments.of(InitialColumn.QUEEN, Score.of(9)),
                Arguments.of(InitialColumn.KING, Score.zero())
        );
    }
}