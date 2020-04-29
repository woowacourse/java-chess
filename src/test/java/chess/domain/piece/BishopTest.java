package chess.domain.piece;

//todo: refac
class BishopTest {

//    @ParameterizedTest
//    @DisplayName("#move() : should return Bishop as to Position 'from', 'to' and team")
//    @MethodSource({"getCasesForMove"})
//    void move(Position from, Position to, Team team, Piece expected) {
//        Piece bishop = null;
//
//        PiecesState boardState = TestSquaresState.initialize();
//        Piece exPiece = boardState.getPiece(to);
//        Piece moved = bishop.move(to, exPiece);
//        assertThat(moved).isEqualTo(expected);
//    }

//    @ParameterizedTest
//    @DisplayName("#hasHindrance() : return boolean as to Position from, to and team")
//    @MethodSource({"getCasesForHasHindrance"})
//    void hasHindrance(Position from, Position to, Team team, boolean expected) {
//        Bishop bishop = (Bishop) PieceFactory.createPiece(PieceType.BISHOP, from, team);
//        PiecesState piecesState = TestSquaresState.initialize();
//        boolean hasHindrance = bishop.hasHindrance(to, piecesState);
//        assertThat(hasHindrance).isEqualTo(expected);
//    }

//    @Test
//    @DisplayName("#calculateScore() : should return score of Bishop")
//    void calculateScore() {
//        //given
//        Piece bishop = null;
//        PiecesState boardState = TestSquaresState.initialize();
//        //when
//        Score score = bishop.calculateScore(boardState);
//        //then
//        assertThat(score).isEqualTo(null);
//    }
//
//    private static Stream<Arguments> getCasesForMove() {
//        Team team = Team.WHITE;
//        return Stream.of(
//                Arguments.of(Position.of(2, 2),
//                        Position.of(6, 6),
//                        team,
//                        null),
//                Arguments.of(Position.of(2, 2),
//                        Position.of(7, 7),
//                        team,
//                        null),
//                Arguments.of(Position.of(2, 2),
//                        Position.of(1, 3),
//                        team,
//                        null)
//        );
//    }
//
//    private static Stream<Arguments> getCasesForHasHindrance() {
//        return Stream.of(
//                Arguments.of(Position.of(3, 2), Position.of(4, 4), Team.WHITE, true),
//                Arguments.of(Position.of(3, 1), Position.of(5, 3), Team.WHITE, true),
//                Arguments.of(Position.of(1, 3), Position.of(3, 1), Team.WHITE, true),
//                Arguments.of(Position.of(5, 3), Position.of(3, 1), Team.WHITE, true),
//                Arguments.of(Position.of(3, 1), Position.of(1, 3), Team.WHITE, true),
//                Arguments.of(Position.of(2, 2), Position.of(6, 6), Team.WHITE, false)
//        );
//    }
}