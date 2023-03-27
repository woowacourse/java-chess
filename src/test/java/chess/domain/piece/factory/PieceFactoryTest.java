package chess.domain.piece.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import domain.piece.Camp;
import domain.piece.Piece;
import domain.piece.factory.PieceFactory;
import domain.piece.type.Pawn;
import domain.piece.type.restricted.King;
import domain.piece.type.restricted.Knight;
import domain.piece.type.unrestricted.Bishop;
import domain.piece.type.unrestricted.Queen;
import domain.piece.type.unrestricted.Rook;

class PieceFactoryTest {

    @ParameterizedTest(name="PieceFactory를 통해 Piece를 생성한다.")
    @MethodSource("provideGenerateResults")
    void generatePiece(PieceFactory pieceFactory, Camp camp, Piece expexted) {
        Piece result = pieceFactory.generatePiece(camp);

        assertThat(result).isEqualTo(expexted);
    }

    @ParameterizedTest(name = "piece의 이름을 통해 해당하는 팩토리를 찾는다.")
    @MethodSource("provideFindResults")
    void find(String pieceName, PieceFactory expected) {
        PieceFactory result = PieceFactory.find(pieceName);

        assertThat(result).isEqualTo(expected);
    }

    public static Stream<Arguments> provideGenerateResults() {
        return Stream.of(
                Arguments.of(PieceFactory.PAWN, Camp.WHITE, new Pawn(Camp.WHITE)),
                Arguments.of(PieceFactory.KING, Camp.BLACK, new King(Camp.BLACK)),
                Arguments.of(PieceFactory.QUEEN, Camp.WHITE, new Queen(Camp.WHITE)),
                Arguments.of(PieceFactory.ROOK, Camp.BLACK, new Rook(Camp.BLACK)),
                Arguments.of(PieceFactory.BISHOP, Camp.BLACK, new Bishop(Camp.BLACK)),
                Arguments.of(PieceFactory.KNIGHT, Camp.WHITE, new Knight(Camp.WHITE))
        );
    }

    public static Stream<Arguments> provideFindResults() {
        return Stream.of(
                Arguments.of("PAWN", PieceFactory.PAWN),
                Arguments.of("KING", PieceFactory.KING),
                Arguments.of("QUEEN", PieceFactory.QUEEN),
                Arguments.of("ROOK", PieceFactory.ROOK),
                Arguments.of("BISHOP", PieceFactory.BISHOP),
                Arguments.of("KNIGHT", PieceFactory.KNIGHT)
        );
    }
}
