package chess.application.chessround;

import chess.application.chessround.dto.ChessPieceDTO;
import chess.application.chessround.dto.ChessPointDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessPlayerDAOTest {
    private ChessPlayerDAO chessPlayerDAO = ChessPlayerDAO.getInstance();

    @Test
    void Player의_말을_추가하고_조회하는_테스트() {
        int row = 9;
        int column = 9;
        String name = "K";
        boolean isWhiteTeam = true;
        ChessPointDTO chessPointDTO = new ChessPointDTO(row, column);
        ChessPieceDTO chessPieceDTO = new ChessPieceDTO(row, column, name);

        chessPlayerDAO.insertChessPiece(chessPieceDTO, isWhiteTeam);

        assertThat(chessPlayerDAO.getChessPiece(chessPointDTO, isWhiteTeam)).isEqualTo(chessPieceDTO);

        chessPlayerDAO.deleteChessPiece(chessPointDTO, isWhiteTeam);
    }
}