package chess.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.InitialPiece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao();

    @DisplayName("새로운 게임을 sql에 저장한다.")
    @Test
    void 새_게임_저장() {
        ChessGame chessGame = new ChessGame(new ChessBoard(InitialPiece.getPiecesWithPosition()));

        assertDoesNotThrow(() -> chessGameDao.save(chessGame));
    }

}