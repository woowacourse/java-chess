package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.chessboard.Numbering;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessPieceGeneratorTest {

    ChessPieceGenerator chessPieceGenerator = ChessPieceGenerator.getInstance();

    @Test
    void 체스말_종류와_놓여질_위치로_체스말을_생성한다() {
        //given
        ChessPieceType chessPieceType = ChessPieceType.BISHOP;
        Numbering blackCampNumber = Numbering.SEVEN;

        //when
        ChessPiece chessPiece = chessPieceGenerator.generate(chessPieceType, blackCampNumber);
        ChessPieceType generatedChessPieceType = chessPiece.getChessPieceType();
        boolean isBlackCamp = chessPiece.getCamp().equals(Camp.BLACK);

        //then
        assertAll(
                () -> assertThat(generatedChessPieceType).isEqualTo(chessPieceType),
                () -> assertThat(isBlackCamp).isTrue()
        );
    }
}
