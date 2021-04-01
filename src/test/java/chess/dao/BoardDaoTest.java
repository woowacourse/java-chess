package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.dto.RoomNamesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BoardDaoTest {

    private BoardDao boardDao;

    @BeforeEach
    public void setup() {
        boardDao = new BoardDao();
    }

    @Test
    public void connection() {
        Connection con = boardDao.getConnection();
        assertNotNull(con);
    }

    @Test
    void findRoomNames() throws SQLException {
        List<RoomNamesDto> expectedNames = Arrays.asList(new RoomNamesDto("a"),
            new RoomNamesDto("Hello"),
            new RoomNamesDto("HI"));
        List<RoomNamesDto> computedNames = boardDao.findRoomNames();

        assertEquals(expectedNames, computedNames);
    }

    @Test
    void findInitialBoardPieceAtPosition() throws Exception {
        Piece expectedBlackRookPiece = new Piece(PieceKind.ROOK, PieceColor.BLACK);
        Piece computedBlackRookPiece = boardDao.findInitialBoardPieceAtPosition("a8");
        assertEquals(expectedBlackRookPiece, computedBlackRookPiece);

        Piece expectedWhitePawnPiece = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece computedWhitePawnPiece = boardDao.findInitialBoardPieceAtPosition("d2");
        assertEquals(expectedWhitePawnPiece, computedWhitePawnPiece);

        Piece expectedBlackKingPiece = new Piece(PieceKind.KING, PieceColor.BLACK);
        Piece computedBlackKingPiece = boardDao.findInitialBoardPieceAtPosition("e8");
        assertEquals(expectedBlackKingPiece, computedBlackKingPiece);
    }

    @Test
    void addRoom() throws SQLException {
        String name = "chess1";
        boardDao.addRoom(name, PieceColor.WHITE);
    }

    @Test
    void savePlayingBoard() throws SQLException {
        String name = "chess1";
        Map<Position, Piece> board = playingBoard();
        boardDao.savePlayingBoard(name, board, PieceColor.WHITE);
    }

    @Test
    void findPlayingBoardByRoom() throws SQLException {
        String name = "chess1";
        String position = "a8";
        Map<Position, Piece> board = playingBoard();
        Piece expectedPieceA8 = board.get(Position.from(position));
        Piece computedPieceA8 = boardDao.findPlayingBoardByRoom(name, position);

        assertEquals(expectedPieceA8, computedPieceA8);
    }

    @Test
    void deleteExistingBoard() throws SQLException {
        String name = "chess1";
        boardDao.deleteExistingBoard(name);
    }

    @Test
    void deleteRoom() throws SQLException {
        String name = "chess1";
        boardDao.deleteRoom(name);
    }

    private Map<Position, Piece> playingBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.from("a8"), new Piece(PieceKind.PAWN, PieceColor.BLACK));
        board.put(Position.from("a7"), new Piece(PieceKind.PAWN, PieceColor.WHITE));
        return board;
    }
}