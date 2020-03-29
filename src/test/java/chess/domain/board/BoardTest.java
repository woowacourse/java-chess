package chess.domain.board;

import chess.domain.Pieces;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class BoardTest {

    @ParameterizedTest
    @MethodSource("createFromPositionAndToPosition")
    void move(Position fromPosition, Position toPosition) {
        Board board = BoardFactory.createInitially();
        Piece pieceToMove = board.findPieceBy(fromPosition);
        board.move(fromPosition, toPosition);

        assertThat(board.findPieceBy(fromPosition)).isNull();
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createFromPositionAndToPosition() {
        return Stream.of(
                Arguments.of(Position.of("B2"), Position.of("B3"))
        );
    }

    @ParameterizedTest
    @MethodSource("createPositionAndPieceForAttackingPawn")
    void move_폰이_공격에_성공하는_경우(Piece pieceToMove, Position fromPosition, Piece pieceToAttack, Position toPosition) {
        //given
        BoardSource boardSource = new BoardSource();
        boardSource.addPiece(fromPosition, pieceToMove);
        boardSource.addPiece(toPosition, pieceToAttack);

        Board board = new Board(boardSource.getSource());

        //when
        board.move(fromPosition, toPosition);

        //then
        assertThat(board.findPieceBy(fromPosition)).isNull();
        assertThat(board.findPieceBy(toPosition)).isEqualTo(pieceToMove);
    }

    private static Stream<Arguments> createPositionAndPieceForAttackingPawn() {
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
    @MethodSource("createTeamAndPieces")
    void findPiecesOf(Team team, Pieces expected) {
        Board board = BoardFactory.createInitially();
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