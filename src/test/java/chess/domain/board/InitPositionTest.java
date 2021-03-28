package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.position.InitPosition;
import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InitPositionTest {

    private Map<Position, Piece> squares;

    @BeforeEach
    void setUp() {
        this.squares = InitPosition.initSquares();
    }

    @Test
    @DisplayName("InitPieces에서 반환 받은 초기 체스보드 크기 확인")
    void testInitBoardSize() {
        assertThat(this.squares).hasSize(64);
    }

    @Test
    @DisplayName("InitPieces에서 반환 받은 초기 체스보드 체스말 확인")
    void testInitBoard() {
        Board board = new Board(this.squares);

        assertThat(board.piecesByYpoint(Ypoint.EIGHT)).containsExactly(
            Rook.createBlack(),
            Knight.createBlack(),
            Bishop.createBlack(),
            Queen.createBlack(),
            King.createBlack(),
            Bishop.createBlack(),
            Knight.createBlack(),
            Rook.createBlack()
        );

        assertThat(board.piecesByYpoint(Ypoint.SEVEN)).containsExactly(
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack(),
            Pawn.createBlack()
        );

        assertThat(board.piecesByYpoint(Ypoint.SIX)).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        assertThat(board.piecesByYpoint(Ypoint.FIVE)).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        assertThat(board.piecesByYpoint(Ypoint.FOUR)).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        assertThat(board.piecesByYpoint(Ypoint.THREE)).containsExactly(
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create(),
            Empty.create()
        );

        assertThat(board.piecesByYpoint(Ypoint.TWO)).containsExactly(
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite(),
            Pawn.createWhite()
        );

        assertThat(board.piecesByYpoint(Ypoint.ONE)).containsExactly(
            Rook.createWhite(),
            Knight.createWhite(),
            Bishop.createWhite(),
            Queen.createWhite(),
            King.createWhite(),
            Bishop.createWhite(),
            Knight.createWhite(),
            Rook.createWhite()
        );
    }
}
