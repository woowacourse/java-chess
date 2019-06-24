package chess.domain.board;

import chess.domain.direction.core.Square;
import chess.domain.piece.*;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ChessGameTest {
    @Test
    void 남은_말_계산_테스트() {
        assertThat(new ChessGame(testGamingBoard()).calculateScore(Team.BLACK)).isEqualTo(20);
        assertThat(new ChessGame(testGamingBoard()).calculateScore(Team.WHITE)).isEqualTo(19.5);
    }

    @Test
    void 게임이_끝났는지_테스트() {
        assertThat(new ChessGame(testGameOverBoard()).isGameOver()).isEqualTo(true);
    }

    @Test
    void 우승자_테스트() {
        assertThat(new ChessGame(testGameOverBoard()).findWinner()).isEqualTo(Team.BLACK);
    }

    private Board testGamingBoard() {
        Map<Square, Piece> testBoard = Maps.newHashMap();

        testBoard.put(Square.of(1, 0), new King(Team.BLACK));
        testBoard.put(Square.of(2, 0), new Rook(Team.BLACK));
        testBoard.put(Square.of(0, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(2, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(3, 1), new Bishop(Team.BLACK));
        testBoard.put(Square.of(1, 2), new Pawn(Team.BLACK));
        testBoard.put(Square.of(4, 2), new Queen(Team.BLACK));

        testBoard.put(Square.of(5, 4), new Knight(Team.WHITE));
        testBoard.put(Square.of(6, 4), new Queen(Team.WHITE));
        testBoard.put(Square.of(5, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(7, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(5, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(6, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(4, 7), new Rook(Team.WHITE));
        testBoard.put(Square.of(5, 7), new King(Team.WHITE));

        return Board.drawBoard(testBoard);
    }

    private Board testGameOverBoard() {
        Map<Square, Piece> testBoard = Maps.newHashMap();

        testBoard.put(Square.of(1, 0), new King(Team.BLACK));
        testBoard.put(Square.of(2, 0), new Rook(Team.BLACK));
        testBoard.put(Square.of(0, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(2, 1), new Pawn(Team.BLACK));
        testBoard.put(Square.of(3, 1), new Bishop(Team.BLACK));
        testBoard.put(Square.of(1, 2), new Pawn(Team.BLACK));
        testBoard.put(Square.of(4, 2), new Queen(Team.BLACK));

        testBoard.put(Square.of(5, 4), new Knight(Team.WHITE));
        testBoard.put(Square.of(6, 4), new Queen(Team.WHITE));
        testBoard.put(Square.of(5, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(7, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(5, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(6, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(4, 7), new Rook(Team.WHITE));

        return Board.drawBoard(testBoard);
    }
}