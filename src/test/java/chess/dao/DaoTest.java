package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;
import chess.dto.RoomNamesDto;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.dao.DbConnection.getConnection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DaoTest {

    @Test
    public void connection() {
        Connection con = getConnection();
        assertNotNull(con);
    }

    @Test
    void findRoomNames() {
        RoomDao roomDao = new RoomDao();
        List<RoomNamesDto> expectedNames = Arrays.asList(new RoomNamesDto("a"),
            new RoomNamesDto("Hello"),
            new RoomNamesDto("HI"));
        List<RoomNamesDto> computedNames = roomDao.findRoomNames();

        assertEquals(expectedNames, computedNames);
    }

    @Test
    void findInitialBoardPieceAtPosition() {
        InitialBoardDao initialBoardDao = new InitialBoardDao();
        Piece expectedBlackRookPiece = new Piece(PieceKind.ROOK, PieceColor.BLACK);
        Piece computedBlackRookPiece = initialBoardDao.findInitialBoardPieceAtPosition("a8");
        assertEquals(expectedBlackRookPiece, computedBlackRookPiece);

        Piece expectedWhitePawnPiece = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece computedWhitePawnPiece = initialBoardDao.findInitialBoardPieceAtPosition("d2");
        assertEquals(expectedWhitePawnPiece, computedWhitePawnPiece);

        Piece expectedBlackKingPiece = new Piece(PieceKind.KING, PieceColor.BLACK);
        Piece computedBlackKingPiece = initialBoardDao.findInitialBoardPieceAtPosition("e8");
        assertEquals(expectedBlackKingPiece, computedBlackKingPiece);
    }

    @Test
    void addRoom() {
        RoomDao roomDao = new RoomDao();
        String name = "chess1";
        roomDao.addRoom(name, PieceColor.WHITE);
    }

    @Test
    void savePlayingBoard() {
        BackupBoardDao backupBoardDao = new BackupBoardDao();
        String name = "chess1";
        Map<Position, Piece> board = playingBoard();
        backupBoardDao.savePlayingBoard(name, board, PieceColor.WHITE);
    }

    @Test
    void findPlayingBoardByRoom() {
        BackupBoardDao backupBoardDao = new BackupBoardDao();
        String name = "chess1";
        String position = "a8";
        Map<Position, Piece> board = playingBoard();
        Piece expectedPieceA8 = board.get(Position.from(position));
        Piece computedPieceA8 = backupBoardDao.findPlayingBoardByRoom(name, position);

        assertEquals(expectedPieceA8, computedPieceA8);
    }

    @Test
    void deleteExistingBoard() {
        BackupBoardDao backupBoardDao = new BackupBoardDao();
        String name = "chess1";
        backupBoardDao.deleteExistingBoard(name);
    }

    @Test
    void deleteRoom() {
        RoomDao roomDao = new RoomDao();
        String name = "chess1";
        roomDao.deleteRoom(name);
    }

    private Map<Position, Piece> playingBoard() {
        Map<Position, Piece> board = new HashMap<>();
        board.put(Position.from("a8"), new Piece(PieceKind.PAWN, PieceColor.BLACK));
        board.put(Position.from("a7"), new Piece(PieceKind.PAWN, PieceColor.WHITE));
        return board;
    }
}