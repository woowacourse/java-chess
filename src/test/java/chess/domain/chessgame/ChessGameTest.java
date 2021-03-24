package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    @DisplayName("강제종료의 경우 게임이 끝나는지")
    void isGameOver() {
        ChessGame game = new ChessGame();
        game.endGame();
        assertThat(game.isGameOver()).isTrue();
    }

    @Test
    @DisplayName("왕이 잡혔을 때 게임이 끝나는지 검증")
    void checkGameOver() {
        Map<Position, Piece> chessBoard = new TreeMap<>(new Board().unwrap());
        chessBoard.put(new Position("e", "7"), new Queen(Team.WHITE));
        ChessGame game = new ChessGame(new Board(chessBoard));
        game.move(new Position("e", "7"), new Position("e", "8"));
        assertThat(game.isGameOver()).isTrue();
    }
}