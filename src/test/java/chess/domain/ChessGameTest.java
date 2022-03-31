package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.State.Finish;
import chess.domain.State.State;
import chess.domain.State.WhiteTurn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.fixedmovablepiece.King;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    Map<Position, Piece> testBoard = new HashMap<>();

    @BeforeEach
    void init() {
        testBoard.put(Position.of("d4"), new King(Color.WHITE));
        testBoard.put(Position.of("d5"), new WhitePawn());
        testBoard.put(Position.of("e3"), new BlackPawn());
    }

    @Test
    void playGameByCommandWithStart() {
        ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        State state = chessGame.playGameByCommand(new GameCommand("start"));

        assertThat(state).isInstanceOf(WhiteTurn.class);
    }

    @Test
    void playGameByCommandWithEnd() {
        ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        State state = chessGame.playGameByCommand(new GameCommand("end"));

        assertThat(state).isInstanceOf(Finish.class);
    }

    @Test
    void playGameByCommandWithMove() {
        ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        assertThatThrownBy(() ->
                chessGame.playGameByCommand(new GameCommand("move", "b2", "b4")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    void playGameByCommandWithStatus() {
        ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        assertThatThrownBy(() ->
                chessGame.playGameByCommand(new GameCommand("status")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("준비 상태에서는 해당 명령어를 사용할 수 없습니다.");
    }

    @Test
    void checkFinished() {
        ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));
        chessGame.playGameByCommand(new GameCommand("start"));
        boolean finished = chessGame.isFinished();

        assertThat(finished).isEqualTo(false);
    }


    @Test
    void calculateScore() {
        ChessGame chessGame = new ChessGame(new ChessBoard(() -> testBoard));

        double score = chessGame.calculateScore(Color.WHITE);
        assertThat(score).isEqualTo(1.0);
    }
}
