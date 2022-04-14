package chess.service;

import chess.dao.FakeBoardDAO;
import chess.dao.FakeGameDAO;
import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessServiceTest {

    private ChessService chessService;

    @BeforeEach
    void setUp() {
        chessService = new ChessService(new FakeBoardDAO(), new FakeGameDAO());
    }

    @Test
    @DisplayName("gameId로 pieces 검색")
    void findPieces() {
        List<ChessDTO> chessDTOS = new ArrayList<>();
        chessDTOS.add(new ChessDTO("white", "pawn", "a2"));
        chessService.savePieces(chessDTOS, new GameIdDTO(100));
        Map<String, Piece> pieces = chessService.findPieces(new GameIdDTO(100));

        assertThat(pieces.get("a2").getPiece()).isEqualTo("pawn");
        assertThat(pieces.get("a2").getColor()).isEqualTo("white");
    }

    @Test
    @DisplayName("piece 저장 확인")
    void savePiece() {
        List<ChessDTO> chessDTOS = new ArrayList<>();
        chessDTOS.add(new ChessDTO("white", "pawn", "a2"));
        chessService.savePieces(chessDTOS, new GameIdDTO(100));

        assertThat(chessService.findPieces(new GameIdDTO(100)).size()).isEqualTo(1);
    }

    @Test
    @DisplayName("piece 삭제 확인")
    void deletePiece() {
        List<ChessDTO> chessDTOS = new ArrayList<>();
        chessDTOS.add(new ChessDTO("white", "pawn", "a2"));
        chessService.savePieces(chessDTOS, new GameIdDTO(100));
        chessService.deletePiece("a2", new GameIdDTO(100));

        assertThat(chessService.findPieces(new GameIdDTO(100)).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("게임 Id 검색")
    void findGameId() {
        chessService.saveGame("green", "lawn", "white");
        GameIdDTO gameIdByUser = chessService.findGameIdByUser("green", "lawn");

        assertThat(gameIdByUser.getId()).isOne();
    }

    @Test
    @DisplayName("게임 초기화 확인")
    void findTurn() {
        chessService.saveGame("green", "lawn", "blank");
        GameIdDTO gameIdByUser = chessService.findGameIdByUser("green", "lawn");
        chessService.initGame(new ChessGame(), gameIdByUser.getId());
        Map<String, Piece> pieces = chessService.findPieces(new GameIdDTO(gameIdByUser.getId()));

        assertThat(pieces.size()).isEqualTo(32);
    }

    @Test
    @DisplayName("게임 삭제 확인")
    void deleteGame() {
        chessService.saveGame("green", "lawn", "white");
        GameIdDTO gameIdByUser = chessService.findGameIdByUser("green", "lawn");
        chessService.deleteGame(new GameIdDTO(gameIdByUser.getId()));

        assertThat(chessService.findGameIdByUser("green", "lawn").getId()).isZero();
    }

    @Test
    @DisplayName("game turn 업데이트 확인 white -> blank(초기화 상태)")
    void updateGameTurn() {
        chessService.saveGame("green", "lawn", "white");
        GameIdDTO gameIdByUser = chessService.findGameIdByUser("green", "lawn");
        chessService.updateTurn(new GameIdDTO(gameIdByUser.getId()), "blank");

        chessService.initGame(new ChessGame(), gameIdByUser.getId());
        assertThat(chessService.findPieces(new GameIdDTO(gameIdByUser.getId())).size()).isEqualTo(32);
    }
}
