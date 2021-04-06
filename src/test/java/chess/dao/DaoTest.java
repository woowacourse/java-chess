package chess.dao;

import chess.domain.board.Board;
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
        Map<Position, Piece> initialBoard = initialBoardDao.findInitialBoard();

        Piece expectedBlackRookPiece = new Piece(PieceKind.ROOK, PieceColor.BLACK);
        Piece computedBlackRookPiece = initialBoard.get(Position.from("a8"));
        assertEquals(expectedBlackRookPiece, computedBlackRookPiece);

        Piece expectedWhitePawnPiece = new Piece(PieceKind.PAWN, PieceColor.WHITE);
        Piece computedWhitePawnPiece = initialBoard.get(Position.from("d2"));
        assertEquals(expectedWhitePawnPiece, computedWhitePawnPiece);

        Piece expectedBlackKingPiece = new Piece(PieceKind.KING, PieceColor.BLACK);
        Piece computedBlackKingPiece = initialBoard.get(Position.from("e8"));
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
        backupBoardDao.savePlayingBoard(name, new Board(playingBoard()), PieceColor.WHITE);
    }

    @Test
    void findPlayingBoardByRoom() {
        BackupBoardDao backupBoardDao = new BackupBoardDao();
        String name = "chess1";

        Map<Position, Piece> board = playingBoard();
        Map<Position, Piece> computedBoard = backupBoardDao.findPlayingBoardByRoom(name);

        assertEquals(board, computedBoard);
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