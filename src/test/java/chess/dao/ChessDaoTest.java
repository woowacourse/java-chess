package chess.dao;

import chess.domain.command.Command;
import chess.domain.command.CommandFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.player.Round;
import chess.domain.position.Position;
import chess.domain.state.StateFactory;
import chess.dto.ChessRequestDto;
import chess.dto.MoveRequestDto;
import chess.repository.ChessRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("piece_status 테이블에서 모든 레코드를 읽어온다.")
    @Test
    public void showAllPieces() throws Exception {
        List<ChessRequestDto> pieces = chessDao.showAllPieces();
        assertThat(pieces).hasSize(32);
    }

    @DisplayName("turn 테이블에서 현재 턴을 읽어온다.")
    @Test
    public void showTurn() throws Exception {
        System.out.println(round.currentPlayerName());
    }

    @DisplayName("source에서 target으로 기물을 이동한다.")
    @Test
    public void movePiece() throws Exception {
        MoveRequestDto moveRequestDto = new MoveRequestDto("a2", "a4");
        chessDao.movePiece(moveRequestDto);
    }

    @DisplayName("piece_status 테이블의 모든 레코드를 삭제한다.")
    @Test
    public void removeAllPieces() throws Exception {
        chessDao.removeAllPieces();
    }

    @DisplayName("turn 테이블의 레코드를 삭제한다.")
    @Test
    public void removeTurn() throws Exception {
        chessDao.removeTurn();
    }

    @DisplayName("target 위치의 기물을 삭제한다.")
    @Test
    public void removePiece() throws Exception {
        MoveRequestDto moveRequestDto = new MoveRequestDto("a6", "a7");
        chessDao.removePiece(moveRequestDto);
    }
}
