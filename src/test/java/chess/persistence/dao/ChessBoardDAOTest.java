package chess.persistence.dao;

import chess.domain.direction.core.Square;
import chess.domain.piece.*;
import chess.domain.piece.core.Piece;
import chess.domain.piece.core.Team;
import chess.persistence.dto.ChessBoardDTO;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.piece.core.Team.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardDAOTest {
    @Test
    void 보드상황판_라운드_별로_데이터_저장하는_테스트() {
        assertThat(ChessBoardDAO.getInstance().addBoardStatus(boardStatusDTO())).isEqualTo(14);
    }

    @Test
    void 보드상황판_게임아이디_별로_데이터_가져오기_테스트() {
        assertThat(ChessBoardDAO.getInstance().findByBoardStatus(boardStatusDTO())).isEqualTo(boardStatusDTO());
    }

    @Test
    void 보드상황판_Max_라운드_가져오기_테스트() {
        ChessBoardDTO testDTO = new ChessBoardDTO();
        testDTO.setRoundNo(7);

        assertThat(ChessBoardDAO.getInstance().findMaxRoundByGameId(1)).isEqualTo(testDTO);
    }

    private ChessBoardDTO boardStatusDTO() {
        Map<Square, Piece> testBoard = Maps.newHashMap();

        testBoard.put(Square.of(1, 0), new King(BLACK));
        testBoard.put(Square.of(2, 0), new Rook(BLACK));
        testBoard.put(Square.of(0, 1), new Pawn(BLACK));
        testBoard.put(Square.of(2, 1), new Pawn(BLACK));
        testBoard.put(Square.of(3, 1), new Bishop(BLACK));
        testBoard.put(Square.of(1, 2), new Pawn(BLACK));
        testBoard.put(Square.of(4, 2), new Queen(BLACK));

        testBoard.put(Square.of(5, 4), new Knight(Team.WHITE));
        testBoard.put(Square.of(6, 4), new Queen(Team.WHITE));
        testBoard.put(Square.of(5, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(7, 5), new Pawn(Team.WHITE));
        testBoard.put(Square.of(5, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(6, 6), new Pawn(Team.WHITE));
        testBoard.put(Square.of(4, 7), new Rook(Team.WHITE));

        ChessBoardDTO testDTO = new ChessBoardDTO();
        testDTO.setGameId(4);
        testDTO.setRoundNo(7);
        testDTO.setBoard(testBoard);

        return testDTO;
    }


}