package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Empty;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private static final int BOARD_LENGTH = 8;
    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.initiateBoard();
    }

    @DisplayName("올바른 보드 생성된다.")
    @Test
    void createTest() {
        List<List<Piece>> piecesList = Arrays.asList(
                Arrays.asList(getPiecesOfFirstLine(Owner.BLACK)),
                Arrays.asList(getPiecesOfSecondLine(Owner.BLACK)),
                Arrays.asList(getEmptyLine()),
                Arrays.asList(getEmptyLine()),
                Arrays.asList(getEmptyLine()),
                Arrays.asList(getEmptyLine()),
                Arrays.asList(getPiecesOfSecondLine(Owner.WHITE)),
                Arrays.asList(getPiecesOfFirstLine(Owner.WHITE))
                );
        Vertical[] verticals = Vertical.values();
        Horizontal[] horizontals = Horizontal.values();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertThat(board.pickPiece(verticals[j], horizontals[i])).isEqualTo(piecesList.get(i).get(j));
            }
        }
    }

    private static Piece[] getPiecesOfFirstLine(final Owner owner) {
        return new Piece[]{
                Rook.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Queen.getInstanceOf(owner),
                King.getInstanceOf(owner),
                Bishop.getInstanceOf(owner),
                Knight.getInstanceOf(owner),
                Rook.getInstanceOf(owner)
        };
    }

    private static Piece[] getPiecesOfSecondLine(final Owner owner) {
        final Piece[] pieces = new Pawn[BOARD_LENGTH];
        Arrays.fill(pieces, Pawn.getInstanceOf(owner));
        return pieces;
    }

    private static Piece[] getEmptyLine() {
        final Piece[] pieces = new Empty[BOARD_LENGTH];
        Arrays.fill(pieces, Empty.getInstance());
        return pieces;
    }

    @DisplayName("입력한 위치의 기물을 가져온다.")
    @Test
    void of() {
        final Piece piece = board.pickPiece(Vertical.B, Horizontal.TWO);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @DisplayName("입력한 위치로 이동된다.")
    @Test
    void moveTest() {
        final Position source = Position.of(Vertical.B, Horizontal.TWO);
        final Position target = Position.of(Vertical.B, Horizontal.THREE);

        board.move(source, target);

        assertThat(board.pickPiece(source)).isInstanceOf(Empty.class);
        assertThat(board.pickPiece(target)).isInstanceOf(Pawn.class);
    }
}
