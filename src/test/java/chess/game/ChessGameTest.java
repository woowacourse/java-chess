package chess.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.board.ChessBoard;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.piece.Bishop;
import chess.piece.BlackPawn;
import chess.piece.EmptyPiece;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.piece.Team;

class ChessGameTest {

    private Map<Position, Piece> board;

    @BeforeEach
    void setUp() {
        board = new HashMap<>();
        for (final File file : File.values()) {
            for (final Rank rank : Rank.values()) {
                board.put(new Position(file, rank), new EmptyPiece());
            }
        }
    }

    @Test
    void 왕이_죽으면_현재_진행상태는_false를_반환한다() {
        // given
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        ChessGame chessGame = new ChessGame(chessBoard);

        // when & then
        assertThat(chessGame.isProcessing()).isFalse();
    }

    @Test
    void 퀸만_존재할_때_해당되는_팀의_점수를_반환한다() {
        // given
        board.put(new Position(File.E, Rank.EIGHT), new Queen(Team.BLACK));
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        ChessGame chessGame = new ChessGame(chessBoard);

        // when & then
        assertThat(chessGame.calculateScore(Team.BLACK)).isEqualTo(9);
    }

    @Test
    void 룩만_존재할_때_해당되는_팀의_점수를_반환한다() {
        // given
        board.put(new Position(File.E, Rank.EIGHT), new Rook(Team.BLACK));
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        ChessGame chessGame = new ChessGame(chessBoard);

        // when & then
        assertThat(chessGame.calculateScore(Team.BLACK)).isEqualTo(5);
    }

    @Test
    void 비숍만_존재할_때_해당되는_팀의_점수를_반환한다() {
        // given
        board.put(new Position(File.E, Rank.EIGHT), new Bishop(Team.BLACK));
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        ChessGame chessGame = new ChessGame(chessBoard);

        // when & then
        assertThat(chessGame.calculateScore(Team.BLACK)).isEqualTo(3);
    }

    @Test
    void 나이트만_존재할_때_해당되는_팀의_점수를_반환한다() {
        // given
        board.put(new Position(File.E, Rank.EIGHT), new Knight(Team.BLACK));
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        ChessGame chessGame = new ChessGame(chessBoard);

        // when & then
        assertThat(chessGame.calculateScore(Team.BLACK)).isEqualTo(2.5);
    }

    @Test
    void 폰만_존재할_때_해당되는_팀의_점수를_반환한다() {
        // given
        board.put(new Position(File.E, Rank.EIGHT), new BlackPawn() {
        });
        ChessBoard chessBoard = ChessBoard.createBoardByRule(board);
        ChessGame chessGame = new ChessGame(chessBoard);

        // when & then
        assertThat(chessGame.calculateScore(Team.BLACK)).isEqualTo(1);
    }
}
