package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.fixture.EmptyBoardFixture;
import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

class GameScoreTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new EmptyBoardFixture().getBoard();
    }

    @ParameterizedTest
    @CsvSource(value = {"BLACK:18", "WHITE:17"}, delimiter = ':')
    void calculateScore메서드는_각_팀에_해당하는_점수를_반환한다(Team team, double score) {
        // given
        board.put(new Position(File.A, Rank.FOUR), new Queen(Team.BLACK));
        board.put(new Position(File.B, Rank.FOUR), new Queen(Team.WHITE));
        board.put(new Position(File.A, Rank.FIVE), new Bishop(Team.BLACK));
        board.put(new Position(File.B, Rank.FIVE), new Bishop(Team.WHITE));
        board.put(new Position(File.A, Rank.SIX), new Rook(Team.BLACK));
        board.put(new Position(File.B, Rank.SIX), new Rook(Team.WHITE));
        board.put(new Position(File.A, Rank.SEVEN), new BlackPawn());

        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        GameScore gameScore = chessBoard.getGameScore();

        // when & then
        assertThat(gameScore.calculateScore(team)).isEqualTo(score);
    }

    @Test
    void calculateScore메서드는_세로_줄에_폰이_두개_이상이면_점수의_절반으로_계산한다() {
        // given
        board.put(new Position(File.A, Rank.FOUR), new Queen(Team.BLACK));
        board.put(new Position(File.B, Rank.FOUR), new Queen(Team.WHITE));
        board.put(new Position(File.A, Rank.FIVE), new Bishop(Team.BLACK));
        board.put(new Position(File.B, Rank.FIVE), new Bishop(Team.WHITE));
        board.put(new Position(File.A, Rank.SIX), new Rook(Team.BLACK));
        board.put(new Position(File.B, Rank.SIX), new Rook(Team.WHITE));
        board.put(new Position(File.A, Rank.SEVEN), new BlackPawn());
        board.put(new Position(File.A, Rank.THREE), new BlackPawn());

        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        GameScore gameScore = chessBoard.getGameScore();

        // when & then
        assertThat(gameScore.calculateScore(Team.BLACK)).isEqualTo(18);
    }
}
