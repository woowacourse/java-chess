package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.dto.BoardSaveDto;
import chess.domain.dto.SavePieceDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BoardSaveDtoTest {

    @Test
    void Test() {
        Board board = BoardGenerator.makeBoard();
        BoardSaveDto from = BoardSaveDto.from(board);

        Map<String, HashMap<String, SavePieceDto>> data = from.getData();

        SavePieceDto a = data.get("A").get("ONE");

        Assertions.assertThat(a.getPieceColor()).isEqualTo("WHITE");
        Assertions.assertThat(a.getPieceType()).isEqualTo("ROOK");
    }
}
