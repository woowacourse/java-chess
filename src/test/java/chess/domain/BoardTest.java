package chess.domain;

import chess.domain.dto.PieceResponse;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardTest {

    @Test
    void 첫_뻔쨰_줄_테스트() {
        //given
        Board board = new Board();

        //when
        var result = board.getPiecePosition().get(0);
        //then

        var check = result.stream().map(PieceResponse::getPieceType).collect(Collectors.toList());
        assertThat(check)
                .containsExactly("r", "n", "b", "q", "k", "b", "n", "r")
                .hasSize(8);
    }

    @Test
    void 두_뻔쨰_줄_테스트() {
        //given
        Board board = new Board();

        //when
        var result = board.getPiecePosition().get(1);
        //then

        var check = result.stream().map(PieceResponse::getPieceType).collect(Collectors.toList());
        assertThat(check)
                .containsOnly("p")
                .hasSize(8);
    }
}
