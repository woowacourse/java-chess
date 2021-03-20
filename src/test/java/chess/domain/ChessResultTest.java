package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class ChessResultTest {
    private ChessResult result;
    private Map<Position, Piece> chessBoard;

    @BeforeEach
    void setUp() {
        chessBoard = new TreeMap<>(new Board().unwrap());
        result = new ChessResult(new Board(chessBoard));
    }

    @Test
    @DisplayName("현재 점수 확인하는 기능")
    void checkScore() {
        final Team team = Team.WHITE;
        assertThat(result.calculateScore(team)).isEqualTo(38);

        chessBoard.put(new Position("a", "3"), new Pawn(Team.WHITE));
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.calculateScore(team)).isEqualTo(38);
    }

    @Test
    @DisplayName("점수 높은 팀 확인 기능")
    void checkScoreWinner() {
        assertThat(result.getWinner()).isEqualTo(Team.NOTHING);

        chessBoard.put(new Position("a", "3"), new Rook(Team.WHITE));
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.getWinner()).isEqualTo(Team.WHITE);

        chessBoard.put(new Position("a", "4"), new Queen(Team.BLACK));
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.getWinner()).isEqualTo(Team.BLACK);
    }

    @Test
    @DisplayName("왕 잡은 팀 확인 기능")
    void checkKingSlayer() {
        chessBoard.put(new Position("e", "8"), Blank.getInstance());
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.getWinner()).isEqualTo(Team.WHITE);
    }
}