import chess.domain.Player;
import chess.domain.chesspieces.Empty;
import chess.domain.chesspieces.King;
import chess.domain.chesspieces.Queen;
import javafx.beans.binding.When;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class PieceTest {
    @DisplayName("기물과 빈칸의 Player 비교")
    @ParameterizedTest
    @CsvSource({"WHITE", "BLACK"})
    void test(String playerType) {
        King king = new King(Player.valueOf(playerType));
        Empty empty = Empty.getInstance();

        assertThat(king.isSamePlayer(empty)).isFalse();
    }

    @DisplayName("서로 같은 Player인지 검사")
    @ParameterizedTest
    @CsvSource({"WHITE", "BLACK"})
    void test2(String playerType) {
        King king = new King(Player.valueOf(playerType));
        Queen queen = new Queen(Player.valueOf(playerType));

        assertThat(king.isSamePlayer(queen)).isTrue();
    }

    @DisplayName("서로 다른 Player인지 검사")
    @ParameterizedTest
    @CsvSource({"WHITE, BLACK", "BLACK, WHITE"})
    void test3(String playerType1, String playerType2) {
        King king = new King(Player.valueOf(playerType1));
        Queen queen = new Queen(Player.valueOf(playerType2));
        assertThat(king.isSamePlayer(queen)).isFalse();
    }
}
