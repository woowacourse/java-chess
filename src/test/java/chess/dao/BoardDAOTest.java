package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.board.Board;
import chess.domain.board.Pieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.dto.WebBoardDTO;
import chess.dto.WebPiecesDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardDAOTest {

    private final BoardDAO boardDAO = BoardDAO.instance();

    @Test
    @DisplayName("요청시 마다 같은 인스턴스인지 확인")
    void instance() {
        assertThat(boardDAO).isSameAs(BoardDAO.instance());
    }

    @Test
    @DisplayName("새로운 보드 생성 테스트")
    void createBoard() {
        Board board = new Board();
        board.init();
        WebBoardDTO webBoardDTO = new WebBoardDTO(board);
        WebPiecesDTO webPiecesDTO = new WebPiecesDTO(board.pieces());
        assertThatCode(() -> boardDAO.createBoard(webBoardDTO, webPiecesDTO)).doesNotThrowAnyException();

    }

    @Test
    @DisplayName("유저이름으로 방정보 가져오기")
    void searchBoard() throws SQLException {
        String playerName = "player1";
        List<WebBoardDTO> webBoardDTOS = boardDAO.searchBoard(playerName);
        assertThat(webBoardDTOS).hasSize(3);
    }

    @Test
    @DisplayName("보드정보 가져오기")
    void joinBoard() throws SQLException {
        int boardId = 1;
        Board board = boardDAO.joinBoard(boardId);
        String source = "c7";
        String target = "c5";
        board.movePiece(source, target);
        WebBoardDTO webBoardDTO = new WebBoardDTO(board, boardId);
        WebPiecesDTO webPiecesDTO = new WebPiecesDTO(board.pieces(), source, target);
        assertThat(boardDAO.updateBoard(webBoardDTO, webPiecesDTO)).isTrue();
    }
}