package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Status;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static chess.domain.piece.ChessPiece.WHITE_PAWN;
import static chess.domain.player.PlayerColor.BLACK;
import static chess.domain.player.PlayerColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class ChessResultTest {

    @Test
    @DisplayName("초기 board score 계산")
    void calculateScore() {
        Board board = Board.createEmpty().placeInitialPieces();
        ChessResult chessResult = ChessResult.from(board);
        Map<PlayerColor, Score> scores = chessResult.getResult();
        Map<PlayerColor, Score> expected = new HashMap<>();
        expected.put(BLACK, Score.from(38));
        expected.put(WHITE, Score.from(38));

        assertThat(scores).isEqualTo(expected);
    }

    @Test
    @DisplayName("같은 player, 같은 column의 pawn이 여러개 있는 경우 score 계산")
    void calculateScoreWhiteSameColumnPawn() {
        Map<Position, GamePiece> map = new TreeMap<>(new TreeMap<>(Board.createEmpty().getBoard()));
        map.put(Position.from("d5"), WHITE_PAWN.getGamePiece());
        map.put(Position.from("d6"), WHITE_PAWN.getGamePiece());
        map.put(Position.from("f3"), WHITE_PAWN.getGamePiece());
        map.put(Position.from("f4"), WHITE_PAWN.getGamePiece());
        map.put(Position.from("f6"), WHITE_PAWN.getGamePiece());
        map.put(Position.from("h3"), WHITE_PAWN.getGamePiece());
        ChessResult chessResult = ChessResult.from(Board.from(map, Status.initialStatus()));

        assertThat(chessResult.getResult().get(WHITE)).isEqualTo(Score.from(3.5));
    }
}