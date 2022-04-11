package chess.dao;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.dto.ChessDTO;
import chess.dto.GameDTO;
import chess.dto.GameIdDTO;
import chess.dto.TurnDTO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ChessBoardDAOTest {

    private ChessGameDAO chessGameDAO;
    private ChessBoardDAO chessBoardDAO;
    private ChessDTO chessDTO;
    private int gameId;

    @BeforeEach
    void setUp() {
        chessGameDAO = new ChessGameDAO();
        GameDTO gameDTO = new GameDTO("green", "lawn");
        chessGameDAO.saveGame(gameDTO, new TurnDTO("white"));

        chessBoardDAO = new ChessBoardDAO();
        chessDTO = new ChessDTO("white", "pawn", "a2");

        gameId = chessGameDAO.findGameIdByUser(gameDTO);
    }

    @AfterEach
    void delete() {
        chessGameDAO.deleteGame(new GameIdDTO(gameId));
    }

    @Test
    @DisplayName("보드 테이블에 기물 저장")
    void saveGame() {
        assertThatNoException().isThrownBy(() -> chessBoardDAO.savePieces(List.of(chessDTO), new GameIdDTO(gameId)));
    }

    @Test
    @DisplayName("전체 기물 find")
    void findAllPiece() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        List<ChessDTO> testDTOs = new ArrayList<>();

        Map<String, Piece> board = chessGame.getChessBoard().toModel();
        for (String position : board.keySet()) {
            testDTOs.add(new ChessDTO(board.get(position).getColor(),
                    board.get(position).getPiece(), position));
        }
        chessBoardDAO.savePieces(testDTOs, new GameIdDTO(gameId));

        assertThat(chessBoardDAO.findAllPiece(new GameIdDTO(gameId)).size()).isEqualTo(32);
    }

    @Test
    @DisplayName("기물 삭제")
    void deletePiece() {
        chessBoardDAO.savePieces(List.of(chessDTO), new GameIdDTO(gameId));

        assertThatNoException().isThrownBy(() -> chessBoardDAO.deletePiece("a2", new GameIdDTO(gameId)));
    }
}
