package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.dto.BoardSaveDto;
import chess.domain.dto.PieceDto;
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

        Map<String, HashMap<String, PieceDto>> data = from.getData();

        PieceDto a = data.get("A").get("ONE");
        System.out.println(a.getPieceColor());
        System.out.println(a.getPieceType());
    }
}
