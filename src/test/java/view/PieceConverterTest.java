package view;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.piece.King;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.TeamColor;

class PieceConverterTest {

    @ParameterizedTest(name = "{index} : Piece 별로 출력이 잘 바뀌는지 테스트 (결과 : {1})")
    @MethodSource("parametersProvider")
    void convert(Piece piece, String expected) {
        String actual = PieceConverter.of(piece);
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> parametersProvider() {
        return Stream.of(
                arguments(new King(TeamColor.BLACK), "K"),
                arguments(new King(TeamColor.WHITE), "k"),
                arguments(new Queen(TeamColor.BLACK), "Q"),
                arguments(new Queen(TeamColor.WHITE), "q")
        );
    }

}
