package chess.domain;

import chess.domain.game.BoardInitializer;
import chess.domain.game.Game;
import chess.domain.pieces.*;
import chess.domain.position.Position;
import chess.machine.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.pieces.Color.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameTest {

    @Test
    @DisplayName("출발지와 목적지가 같으면 예외를 발생한다")
    void thrown_sourceEqualsTarget() {
        Game game = new Game(new BoardInitializer());
        assertThatThrownBy(() -> game.move("a2", "a2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발지와 목적지가 동일할 수 없습니다.");
    }

    @Test
    @DisplayName("수를 번갈아가면서 두어야 한다")
    void turnNotChanged_throwException() {
        Game game = new Game(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("a5"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("a4"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        game.move("a5", "a6");
        assertThatThrownBy(() -> game.move("a4", "a5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지금은 검은말의 턴입니다.");
    }

    @Test
    @DisplayName("각 진영의 점수를 계산한다")
    void calculate_eachColorsScore() {
        Game game = new Game(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("g1"), new Piece(WHITE, new Rook()));
            pieces.put(Position.of("c3"), new Piece(WHITE, new Queen()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new Knight()));
            pieces.put(Position.of("a1"), new Piece(WHITE, new Bishop()));
            pieces.put(Position.of("h2"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertAll(
                () -> assertThat(game.calculateScore(WHITE)).isEqualTo(20.5),
                () -> assertThat(game.calculateScore(BLACK)).isEqualTo(0)
        );
    }

    @Test
    @DisplayName("같은 세로줄에 같은 색의 폰이 복수 존재하면 폰은 0.5점이다.")
    void calculate_pawnScore() {
        Game game = new Game(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("d4"), new Piece(WHITE, new King()));
            pieces.put(Position.of("a1"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("a5"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("a4"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("b4"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        assertThat(game.calculateScore(WHITE)).isEqualTo(2);
    }

    @Test
    @DisplayName("킹이 1개만 남으면 게임이 종료되고 최종 승자를 반환한다.")
    void calculate_finalWinner_byKing() {
        Game game = new Game(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("a1"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("a5"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("a4"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("b4"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        Map<Result, Color> result = game.calculateFinalWinner();
        assertThat(result.get(Result.WIN)).isEqualTo(BLACK);
    }

    @Test
    @DisplayName("게임이 종료되고 킹이 2개이면 점수로 승패를 결정한다.")
    void calculate_finalWinner_byScore() {
        Game game = new Game(() -> {
            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(Position.of("g7"), new Piece(BLACK, new King()));
            pieces.put(Position.of("a7"), new Piece(WHITE, new King()));
            pieces.put(Position.of("a1"), new Piece(BLACK, new Pawn()));
            pieces.put(Position.of("a5"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("a4"), new Piece(WHITE, new Pawn()));
            pieces.put(Position.of("b4"), new Piece(WHITE, new Pawn()));
            return pieces;
        });

        Map<Result, Color> result = game.calculateFinalWinner();
        assertThat(result.get(Result.WIN)).isEqualTo(WHITE);
    }
}
