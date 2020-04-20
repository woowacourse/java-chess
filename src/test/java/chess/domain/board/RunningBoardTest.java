package chess.domain.board;

import chess.config.BoardConfig;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.factory.PieceType;
import chess.domain.piece.position.MovingFlow;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RunningBoardTest {

    @Test
    @DisplayName("#initialize() : should return initialized Board")
    void initiaize() {
        RunningBoard runningBoard = RunningBoard.initiaize();
        for (int i = BoardConfig.LINE_START; i <= BoardConfig.LINE_END; i++) {
            for (Integer row : Arrays.asList(BoardConfig.LINE_START,BoardConfig.LINE_END)) {
                Piece piece = runningBoard.getPiece(Position.of(i, row));
                PieceType pieceType = PieceType.valueOf(piece.getClass());
                assertThat(pieceType).isEqualTo(PieceType.findByInitialColumn(i));
            }
        }

        for (int i = BoardConfig.LINE_START; i <= BoardConfig.LINE_END; i++) {
            for (int j = RunningBoard.BLANK_START_INDEX; j <= RunningBoard.BLANK_END_INDEX; j++) {
                Piece piece = runningBoard.getPiece(Position.of(i, j));
                assertTrue(piece.isBlank());
            }
        }
    }

    @ParameterizedTest
    @DisplayName("#movePiece() : should return Board with moved piece")
    @MethodSource({"getCasesForMovePiece"})
    void movePiece(MovingFlow movingFlow, Piece expected) {
        RunningBoard runningBoard = RunningBoard.initiaize();
        Board movedBoard = runningBoard.movePiece(movingFlow);
        Piece piece = movedBoard.getPiece(movingFlow.getTo());
        assertThat(piece).isEqualTo(expected);

    }

    @Test
    @DisplayName("#getPiece() : should return Piece as to Position")
    void getPiece() {
        RunningBoard runningBoard = RunningBoard.initiaize();
        Piece piece = runningBoard.getPiece(Position.of(1, 1));
        assertThat(piece).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("#concludeResult() : should throw IllegalStateException")
    void concludeResult() {
        RunningBoard runningBoard = RunningBoard.initiaize();
        assertThatThrownBy(runningBoard::concludeResult)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(RunningBoard.NOT_FINISHED_ERROR);
    }

    private static Stream<Arguments> getCasesForMovePiece() {
        return Stream.of(
                Arguments.of(MovingFlow.of("a2", "a3"), PieceFactory.createMovedPiece(PieceType.MOVED_PAWN, Position.of(1,3), Team.WHITE, MoveType.MOVED)),
                Arguments.of(MovingFlow.of("b1", "c3"), PieceFactory.createMovedPiece(PieceType.KNIGHT, Position.of(3,3), Team.WHITE, MoveType.MOVED))
        );
    }
}