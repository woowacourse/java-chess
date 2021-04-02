package chess.dao;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import chess.dto.PieceRequestDto;
import chess.repository.ChessRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChessDaoTest {
    private static Command command;
    private static Round round;
    private ChessDao chessDao;

    @BeforeEach
    public void setUp() {
        command = CommandFactory.initialCommand("start");
        round = new Round(StateFactory.initialization(PieceFactory.whitePieces()),
                StateFactory.initialization(PieceFactory.blackPieces()), command);
        chessDao = new ChessDao();
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

    @DisplayName("데이터베이스 연결을 확인한다.")
    @Test
    public void connect() {
        Connection con = chessDao.getConnection();
        assertNotNull(con);
    }

    @DisplayName("piece_status 테이블에 레코드를 삽입한다.")
    @Test
    public void initializePieceStatus() throws Exception {
        ChessRepositoryImpl chessRepository = new ChessRepositoryImpl();
        chessRepository.initializePieceStatus(getBoard());
    }

    @DisplayName("turn 테이블에 레코드를 삽입한다.")
    @Test
    public void initializeTurn() throws Exception {
        chessDao.initializeTurn();
    }
}
