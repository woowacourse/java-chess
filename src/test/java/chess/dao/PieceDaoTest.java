package chess.dao;

import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecesFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import chess.dto.ChessRequestDto;
import chess.dto.MoveRequestDto;
import chess.repository.ChessRepositoryImpl;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class PieceDaoTest {
    private static Round round;
    private PieceDao pieceDao;

    @BeforeEach
    public void setUp() {
        round = new Round(StateFactory.initialization(PiecesFactory.whitePieces()),
                StateFactory.initialization(PiecesFactory.blackPieces()),
                CommandFactory.initialCommand("start"));
        pieceDao = new PieceDao();
    }

    public Map<String, String> getBoard() {
        Map<Position, Piece> chessBoard = round.getBoard();
        Map<String, String> filteredChessBoard = new HashMap<>();
        for (Map.Entry<Position, Piece> chessBoardStatus : chessBoard.entrySet()) {
            if (chessBoardStatus.getValue() != null) {
                filteredChessBoard.put(chessBoardStatus.getKey().toString(), chessBoardStatus.getValue().getPiece());
            }
        }
        return filteredChessBoard;
    }

    @Order(1)
    @DisplayName("데이터베이스 연결을 확인한다.")
    @Test
    public void connect() {
        Connection con = pieceDao.getConnection();
        assertNotNull(con);
    }

    @Order(2)
    @DisplayName("piece_status 테이블에 레코드를 삽입한다.")
    @Test
    public void initializePieceStatus() {
        ChessRepositoryImpl chessRepository = new ChessRepositoryImpl();
        chessRepository.initializePieceStatus(getBoard());
    }

    @Order(3)
    @DisplayName("piece_status 테이블에서 모든 레코드를 읽어온다.")
    @Test
    public void showAllPieces() {
        List<ChessRequestDto> pieces = pieceDao.showAllPieces();
        assertThat(pieces).hasSize(32);
    }

    @Order(4)
    @DisplayName("source에서 target으로 기물을 이동한다.")
    @Test
    public void movePiece() {
        MoveRequestDto moveRequestDto = new MoveRequestDto("a2", "a4");
        pieceDao.movePiece(moveRequestDto);
    }

    @Order(5)
    @DisplayName("piece_status 테이블의 모든 레코드를 삭제한다.")
    @Test
    public void removeAllPieces() {
        pieceDao.removeAllPieces();
    }

    @Order(6)
    @DisplayName("target 위치의 기물을 삭제한다.")
    @Test
    public void removePiece() {
        MoveRequestDto moveRequestDto = new MoveRequestDto("a6", "a7");
        pieceDao.removePiece(moveRequestDto);
    }
}
