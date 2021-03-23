package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessResultTest {

    private Board board;
    private ChessResult result;

    @BeforeEach
    void setUp() {
        board = new Board();
        result = new ChessResult(board);
    }

    @Test
    @DisplayName("현재 점수 확인하는 기능")
    void checkScore() {
        final Team team = Team.WHITE;
        assertThat(result.calculateScore(team)).isEqualTo(38);

        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        chessBoard.put(new Position("a", "3"), new Pawn(Team.WHITE));
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.calculateScore(team)).isEqualTo(38);
    }

    @Test
    @DisplayName("점수 높은 팀 확인 기능")
    void checkScoreWinner() {
        assertThat(result.winner()).isEqualTo(Team.NOTHING);
        Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        chessBoard.put(new Position("a", "3"), new Rook(Team.WHITE));
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.winner()).isEqualTo(Team.WHITE);

        chessBoard = new TreeMap<>(board.unwrap());
        chessBoard.put(new Position("a", "4"), new Queen(Team.BLACK));
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.winner()).isEqualTo(Team.BLACK);
    }

    @Test
    @DisplayName("왕 잡은 팀 확인 기능")
    void checkKingSlayer() {
        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        chessBoard.put(new Position("e", "8"), Blank.getInstance());
        result = new ChessResult(new Board(chessBoard));
        assertThat(result.winner()).isEqualTo(Team.WHITE);
    }
}