package chess.domain.piece;

import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import chess.domain.route.Route;
import chess.fixture.MovePathFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceTest {

    private Piece piece;

    @BeforeEach
    void setUp() {
        piece = new Piece(Side.WHITE) {
            @Override
            public boolean hasFollowedRule(Position current, Position target, Route route) {
                return true;
            }

            @Override
            public PieceType pieceType() {
                return null;
            }
        };
    }

    @DisplayName("source 위치와 target 위치가 같으면 이동할 수 없다.")
    @Test
    void checkDifferentPosition() {
        Position source = A1;
        Position target = A1;

        assertThatThrownBy(() -> piece.checkValidMove(source, target, MovePathFixture.noPieces()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치와 target 위치가 같으면 이동할 수 없습니다.");
    }

    @DisplayName("target 위치에 같은 색의 기물이 존재하면 이동할 수 없다.")
    @Test
    void checkNoSameColorPieceAtTarget() {
        Position source = A1;
        Position target = A2;

        assertThatThrownBy(() -> piece.checkValidMove(source, target, MovePathFixture.hasTargetPiece(new Pawn(Side.WHITE))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("target 위치에 같은 색의 기물이 존재하면 이동할 수 없습니다.");
    }

    @DisplayName("source 위치에서 target 위치까지의 경로에 기물이 존재하면 이동할 수 없다.")
    @Test
    void checkNoPathPieces() {
        Position source = A1;
        Position target = A3;

        assertThatThrownBy(() -> piece.checkValidMove(source, target, MovePathFixture.hasPathPieces(new Rook(Side.BLACK))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("source 위치에서 target 위치까지의 경로에 기물이 존재하면 이동할 수 없습니다.");
    }

    @DisplayName("이동 규칙을 어기면 이동할 수 없다.")
    @Test
    void checkHasViolatedRule() {
        Piece piece = new Piece(Side.WHITE) {
            @Override
            public boolean hasFollowedRule(Position current, Position target, Route movePath) {
                return false;
            }

            @Override
            public PieceType pieceType() {
                return null;
            }
        };

        assertThatThrownBy(() -> piece.checkValidMove(A1, A2, MovePathFixture.noPieces()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 규칙을 어기면 이동할 수 없습니다.");
    }
}
