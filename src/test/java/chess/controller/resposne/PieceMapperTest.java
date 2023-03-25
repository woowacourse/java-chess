package chess.controller.resposne;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import chess.view.resposne.PieceMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class PieceMapperTest {

    @ParameterizedTest
    @CsvSource(value = {"PAWN, p", "ROOK, r", "KNIGHT, n", "BISHOP, b", "QUEEN, q", "KING, k"})
    void getPieceName(PieceType pieceType, String expected) {
        //given
        Piece piece = PieceFactory.getInstance(pieceType, Color.WHITE);
        //when
        String result = PieceMapper.getPieceName(piece);

        //then
        assertEquals(result, expected);
    }
}
