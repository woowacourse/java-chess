package chess.domain.chessround;

import chess.application.chessround.ChessRoundService;
import chess.domain.chessround.dto.ChessPieceDTO;
import chess.domain.chessround.dto.ChessPlayerDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class tempTest {
    @Test
    void name() {
        ChessRoundService chessRoundService = ChessRoundService.getInstance();
        ChessPlayerDTO chessPlayerDTO = chessRoundService.fetchWhitePlayer();

        for (ChessPieceDTO chessPieceDTO : chessPlayerDTO.getChessPieceDTOs()) {
            List<String> rowPieces = pieces.get(chessPieceDTO.getRow() - 1);
            rowPieces.set(chessPieceDTO.getColumn() - 1, chessPieceDTO.getName());
            pieces.set(chessPieceDTO.getRow() - 1, rowPieces);

            System.out.println("********************************************\n" + Arrays.toString(rowPieces.toArray()));
        }
    }
}
