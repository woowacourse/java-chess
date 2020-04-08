package chess.domain.board;

import chess.dao.InMemoryPieceDao;
import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Placeable;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class BoardTest {
    private static Team teamInTurn;

    @BeforeEach
    void setUp() {
        teamInTurn = Team.WHITE;
    }

    @ParameterizedTest
    @MethodSource("createFromPositionAndToPosition")
    void move(Position fromPosition, Position toPosition) {
        Board board = BoardFactory.createInitially(InMemoryPieceDao.getInstance());
        Placeable pieceToMove = board.findPieceBy(fromPosition);
        board.move(fromPosition, toPosition, teamInTurn);

        assertThat(board.findPieceBy(fromPosition)).isEqualTo(new Empty());
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createFromPositionAndToPosition() {
        return Stream.of(
                Arguments.of(Position.of("B2"), Position.of("B3"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForAttackingPawnToSucceed")
    void move_폰이_공격에_성공하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        //when
        board.move(fromPosition, toPosition, teamInTurn);

        //then
        assertThat(board.findPieceBy(fromPosition)).isEqualTo(new Empty());
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createPositionAndPieceForAttackingPawnToSucceed() {

        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("C3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("E3"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForAttackingPawnToFail")
    void move_폰이_공격에_실패하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        assertThatThrownBy(() -> {
            board.move(fromPosition, toPosition, teamInTurn);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPositionAndPieceForAttackingPawnToFail() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("C3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Empty(), Position.of("C3"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForProceedingPawnToSucceed")
    void move_폰이_전진에_성공하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        //when
        board.move(fromPosition, toPosition, teamInTurn);

        //then
        assertThat(board.findPieceBy(fromPosition)).isEqualTo(new Empty());
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createPositionAndPieceForProceedingPawnToSucceed() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Empty(), Position.of("D3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Empty(), Position.of("D4")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D3"),
                        new Empty(), Position.of("D4"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForMovingPawnToFail")
    void move_폰이_전진에_실패하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        assertThatThrownBy(() -> {
            board.move(fromPosition, toPosition, teamInTurn);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPositionAndPieceForMovingPawnToFail() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("D3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("D3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("D4")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D2"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("D4")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("D3"),
                        new Empty(), Position.of("D5"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForKnightToSucceed")
    void move_나이트가_이동에_성공하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToDisturb, Position middlePosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(middlePosition, pieceToDisturb);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        //when
        board.move(fromPosition, toPosition, teamInTurn);

        //then
        assertThat(board.findPieceBy(fromPosition)).isEqualTo(new Empty());
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createPositionAndPieceForKnightToSucceed() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("E6"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("F7")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("E7"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("F7")),

                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("F6"),
                        new Empty(), Position.of("G6")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("E6"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("G6")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.PAWN), Position.of("E7"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("G6"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForKnightToFail")
    void move_나이트가_이동에_실패하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        assertThatThrownBy(() -> {
            board.move(fromPosition, toPosition, teamInTurn);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPositionAndPieceForKnightToFail() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("F7")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("G6")),

                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("E6")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("F6")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("F5")),

                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("E6")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("F6")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E5"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("F5"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForQueenToSucceed")
    void move_퀸이_이동에_성공하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        //when
        board.move(fromPosition, toPosition, teamInTurn);

        //then
        assertThat(board.findPieceBy(fromPosition)).isEqualTo(new Empty());
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createPositionAndPieceForQueenToSucceed() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("D8")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("D4")),

                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("F3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("H5")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("B3"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForQueenToFailByDirsturbing")
    void move_퀸이_장애물에_막혀서_이동에_실패하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToDisturb, Position middlePosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(middlePosition, pieceToDisturb);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        assertThatThrownBy(() -> {
            board.move(fromPosition, toPosition, teamInTurn);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPositionAndPieceForQueenToFailByDirsturbing() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("D4"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("D8")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("D2"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("D4")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("E2"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("F3")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("F3"),
                        new Piece(Team.BLACK, PieceType.ROOK), Position.of("H5"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForQueenToFailByNotAvailablePosition")
    void move_퀸이_목적지에_같은팀_기물이_있어서_이동에_실패하는_경우(Placeable pieceToMove, Position fromPosition, Placeable pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        assertThatThrownBy(() -> {
            board.move(fromPosition, toPosition, teamInTurn);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> createPositionAndPieceForQueenToFailByNotAvailablePosition() {
        return Stream.of(
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.WHITE, PieceType.ROOK), Position.of("D8")),
                Arguments.of(
                        new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                        new Piece(Team.WHITE, PieceType.KNIGHT), Position.of("D2"),
                        Arguments.of(
                                new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                                new Piece(Team.WHITE, PieceType.ROOK), Position.of("F3")),
                        Arguments.of(
                                new Piece(Team.WHITE, PieceType.QUEEN), Position.of("D1"),
                                new Piece(Team.WHITE, PieceType.ROOK), Position.of("H5")))
        );
    }

    @ParameterizedTest
    @MethodSource("createTeamAndPieces")
    void findPiecesOf(Team team, Pieces expected) {
        Board board = BoardFactory.createInitially(InMemoryPieceDao.getInstance());
        Pieces pieces = board.findPiecesOf(team);

        assertThat(pieces).isEqualTo(expected);
    }

    private static Stream<Arguments> createTeamAndPieces() {
        return Stream.of(
                Arguments.of(Team.BLACK, new Pieces(new HashSet<>(Arrays.asList(
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),
                        new Piece(Team.BLACK, PieceType.PAWN),

                        new Piece(Team.BLACK, PieceType.KING),
                        new Piece(Team.BLACK, PieceType.QUEEN),
                        new Piece(Team.BLACK, PieceType.BISHOP),
                        new Piece(Team.BLACK, PieceType.BISHOP),
                        new Piece(Team.BLACK, PieceType.KNIGHT),
                        new Piece(Team.BLACK, PieceType.KNIGHT),
                        new Piece(Team.BLACK, PieceType.ROOK),
                        new Piece(Team.BLACK, PieceType.ROOK)
                )))),
                Arguments.of(Team.WHITE, new Pieces(new HashSet<>(Arrays.asList(
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),
                        new Piece(Team.WHITE, PieceType.PAWN),

                        new Piece(Team.WHITE, PieceType.KING),
                        new Piece(Team.WHITE, PieceType.QUEEN),
                        new Piece(Team.WHITE, PieceType.BISHOP),
                        new Piece(Team.WHITE, PieceType.BISHOP),
                        new Piece(Team.WHITE, PieceType.KNIGHT),
                        new Piece(Team.WHITE, PieceType.KNIGHT),
                        new Piece(Team.WHITE, PieceType.ROOK),
                        new Piece(Team.WHITE, PieceType.ROOK)
                ))))
        );
    }
}