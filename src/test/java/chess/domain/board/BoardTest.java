package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.EmptyPiece;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    private static final int BOARD_LENGTH = 8;
    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardInitializer.initiateBoard();
    }

    @Test
    @DisplayName("올바른 보드 생성된다.")
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
        Horizontal[] horizontals = Horizontal.values();
        Vertical[] verticals = Vertical.values();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertThat(board.pickPiece(horizontals[j], verticals[i])).isEqualTo(piecesList.get(i).get(j));
            }
        }
    }

    @Test
    @DisplayName("입력한 위치의 기물을 가져온다.")
    void pickPieceTest() {
        final Piece piece = board.pickPiece(Horizontal.B, Vertical.TWO);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece).isEqualTo(Pawn.getInstanceOf(Owner.WHITE));
    }

    @Test
    @DisplayName("입력한 위치로 이동된다.")
    void moveTest() {
        final Position source = Position.of(Horizontal.B, Vertical.TWO);
        final Position target = Position.of(Horizontal.B, Vertical.THREE);

        board.move(source, target);

        assertThat(board.pickPiece(source)).isInstanceOf(EmptyPiece.class);
        assertThat(board.pickPiece(target)).isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("지정한 위치에 있는 말의 Path를 가져온다.")
    void movablePathTest() {
        assertThat(board.movablePath(Position.of("a2"))).isInstanceOf(Path.class);
    }

    @Test
    @DisplayName("해당 색깔의 king이 살아있는지 판단한다.")
    void isKingAliveTest() {
        boolean isTrue = board.isKingAlive(Owner.WHITE);

        Map<Position, Piece> board1 = board.getBoard();
        board1.put(Position.of("e1"), EmptyPiece.getInstance());
        Board otherBoard = new Board(board1);
        boolean isFalse = otherBoard.isKingAlive(Owner.WHITE);

        assertThat(isTrue).isTrue();
        assertThat(isFalse).isFalse();
    }

    @Test
    @DisplayName("보드 리셋 시킬 수 있다.")
    void resetBoardTest() {
        board.move(Position.of("a2"), Position.of("a4"));
        List<Piece> pieces = new ArrayList<>();

        pieces.addAll(Arrays.asList(getPiecesOfFirstLine(Owner.BLACK)));
        pieces.addAll(Arrays.asList(getPiecesOfSecondLine(Owner.BLACK)));
        for (int i = 0; i < 4; i++) {
            pieces.addAll(Arrays.asList(getEmptyLine()));
        }
        pieces.addAll(Arrays.asList(getPiecesOfSecondLine(Owner.WHITE)));
        pieces.addAll(Arrays.asList(getPiecesOfFirstLine(Owner.WHITE)));

        board.resetBoard();

        List<Piece> boardPieces = board.pieces();

        for (int i = 0; i < 64; i++) {
            assertThat(boardPieces.get(i)).isEqualTo(pieces.get(i));
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
        final Piece[] pieces = new EmptyPiece[BOARD_LENGTH];
        Arrays.fill(pieces, EmptyPiece.getInstance());
        return pieces;
    }
}
