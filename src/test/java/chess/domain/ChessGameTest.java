package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Team;
import chess.domain.utils.BoardInitializer;
import chess.domain.piece.Pawn;
import java.util.EnumMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameTest {
    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    void isRunning() {
        chessGame.initBoard(BoardInitializer.init());
        assertThat(chessGame.isRunning()).isTrue();
    }

    @Test
    void isReady() {
        assertThat(chessGame.isReady()).isTrue();
    }

    @Test
    void move() {
        chessGame.initBoard(BoardInitializer.init());
        chessGame.move("move a2 a3");
        Board board = chessGame.board();
        assertThat(board.pieceAt(Position.of("a3"))).isInstanceOf(Pawn.class);
    }

    @Test
    void calculatePoint() {
        EnumMap<Team, Double> result = new EnumMap<>(Team.class);
        result.put(Team.BLACK, 38.0);
        result.put(Team.WHITE, 38.0);

        chessGame.initBoard(BoardInitializer.init());
        assertThat(chessGame.calculatePoint()).isEqualTo(result);
    }
}