package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnMoveStrategyTest {

    class PawnMoveStrategyForTest extends PawnMoveStrategy {

        @Override
        public boolean isMovable(Board board, Position source, Position target) {
            return false;
        }

        @Override
        protected boolean isMovePattern(MovementPattern movementPattern,
                                        Board board,
                                        Position source,
                                        Piece targetPiece) {
            return false;
        }

        @Override
        protected boolean isStartMovable(Board board, Position source, Piece targetPiece) {
            return super.isStartMovable(board, source, targetPiece);
        }

        @Override
        protected boolean isTargetPieceOppositeTeam(Piece targetPiece, Piece sourcePiece) {
            return super.isTargetPieceOppositeTeam(targetPiece, sourcePiece);
        }
    }

    private Board board;
    private PawnMoveStrategyForTest pawnMoveStrategyForTest;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        pawnMoveStrategyForTest = new PawnMoveStrategyForTest();
    }

    @Test
    @DisplayName("Pawn 이 시작지점에서 2칸 전진 할 수 있다.")
    void isStartMovable() {
        Position source = Position.of('a', 2);

        assertThat(pawnMoveStrategyForTest.isStartMovable(board, source, new Blank())).isTrue();
    }

    @Test
    @DisplayName("Pawn 이 시작지점에 위치 하지 않아서 2칸 전진 할 수 없다.")
    void isStartMovable_NotStartPosition() {
        board.movePiece(Position.of('a', 2), Position.of('a', 3));

        Position source = Position.of('a', 3);

        assertThat(pawnMoveStrategyForTest.isStartMovable(board, source, new Blank())).isFalse();
    }

    @Test
    @DisplayName("Pawn 의 한칸 앞에 다른 기물이 존재해서 2칸 전진 할 수 없다.")
    void isStartMovable_ExistOtherPiece() {
        board.movePiece(Position.of('a', 7), Position.of('a', 3));

        Position source = Position.of('a', 2);

        assertThat(pawnMoveStrategyForTest.isStartMovable(board, source, new Blank())).isFalse();
    }

    @Test
    @DisplayName("Pawn 의 2칸 앞에 다른 기물이 존재하여 2칸 전진 할 수 없다.")
    void isStartMovable_ExistOtherPieceAtTarget() {
        Position source = Position.of('a', 2);

        assertThat(pawnMoveStrategyForTest.isStartMovable(board, source, new Pawn(Team.BLACK))).isFalse();
    }

    @Test
    @DisplayName("target Piece 가 상대편 Piece 인지 판별")
    void isCatchable() {
        assertThat(pawnMoveStrategyForTest.isTargetPieceOppositeTeam(new Pawn(Team.BLACK), new Pawn(Team.WHITE)))
                .isTrue();
    }
}

