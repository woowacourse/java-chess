package chess.domain;

import chess.domain.piece.BlackPawn;
import chess.exception.SamePositionException;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    private ChessBoard chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @Test
    void 같은_위치에_말_추가() {
        chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(2, 5)));
        assertThrows(SamePositionException.class, () ->
                chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(2, 5))));
    }

    @Test
    void 다른_위치에_말_추가() {
        chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(2, 5)));
        assertDoesNotThrow(() ->
                chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(3, 5))));
    }

    @Test
    void 해당_경로로_이동_불가능() {
        chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(6, 5)));
        Path path = new Path();
        path.add(Position.getPosition(5, 5));
        path.add(Position.getPosition(6, 5));
        path.add(Position.getPosition(7, 5));
        assertFalse(chessBoard.isMovable(path));
    }

    @Test
    void 해당_경로로_이동_가능() {
        Path path = new Path();
        path.add(Position.getPosition(5, 5));
        path.add(Position.getPosition(6, 5));
        path.add(Position.getPosition(7, 5));
        assertTrue(chessBoard.isMovable(path));
    }

    @Test
    void 초기_흑색_점수() {
        chessBoard = ChessPiece.generateChessBoard(new ChessInitialPosition());

        assertThat(chessBoard.getPlayerScore(Player.BLACK)).isEqualTo(new Score(38));
    }

    @Test
    void 초기_백색_점수() {
        chessBoard = ChessPiece.generateChessBoard(new ChessInitialPosition());

        assertThat(chessBoard.getPlayerScore(Player.WHITE)).isEqualTo(new Score(38));
    }

    @Test
    void ROOK_점수() {
        chessBoard.addPiece(Rook.valueOf(Player.BLACK, Position.getPosition(1, 1)));

        assertThat(chessBoard.getPlayerScore(Player.BLACK)).isEqualTo(new Score(5));
    }

    @Test
    void PAWN_점수() {
        chessBoard.addPiece(BlackPawn.valueOf(Player.BLACK, Position.getPosition(1, 1)));
        chessBoard.addPiece(BlackPawn.valueOf(Player.BLACK, Position.getPosition(1, 3)));
        chessBoard.addPiece(BlackPawn.valueOf(Player.BLACK, Position.getPosition(1, 7)));
        chessBoard.addPiece(BlackPawn.valueOf(Player.BLACK, Position.getPosition(2, 7)));

        assertThat(chessBoard.getPlayerScore(Player.BLACK)).isEqualTo(new Score(2.5));
    }
}
