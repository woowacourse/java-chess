package chess.domain;

import chess.domain.board.Chessboard;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.validateMove.SourceMoveValidator;
import chess.domain.validateMove.ValidateData;
import chess.room.Move;
import chess.room.Room;
import chess.room.RoomDao;

import java.sql.SQLException;

public class ChessGame {
    private static final String CANT_MOVE_MESSAGE = "이동할 수 없는 위치입니다";
    private static final String NOT_YOUR_PIECE_MESSAGE = "해당 위치에는 당신의 Piece가 없습니다.";
    private final Chessboard chessboard;
    private Camp turn;
    private Room room;

    public ChessGame() {
        chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
        turn = Camp.WHITE;
    }

    public void move(Square source, Square target) throws SQLException {
        validateTurn(source);
        moveOnePiece(source, target);
        room.updateMove(source, target);
    }

    private void moveOnePiece(Square source, Square target) {
        if (canMove(source, target)) {
            chessboard.swapPiece(source, target);
            turn = turn.getOpposite();
            return;
        }
        throw new IllegalArgumentException(CANT_MOVE_MESSAGE);
    }

    private void validateTurn(Square square) {
        Piece pieceAtSquare = chessboard.getPieceAt(square);

        if (pieceAtSquare.isOpposite(turn)) {
            throw new IllegalArgumentException(NOT_YOUR_PIECE_MESSAGE);
        }
    }

    private boolean canMove(Square source, Square target) {

        if (source.equals(target)) {
            return false;
        }
        return new SourceMoveValidator().validate(new ValidateData(source, target, chessboard));
    }

    public Chessboard getChessboard() {
        return chessboard;
    }

    public Camp getTurn() {
        return turn;
    }

    public boolean canKeepGoing() throws SQLException {
        if (getChessboard().isKingSurvive()) {
            return true;
        }
        room.deleteRoom();
        return false;
    }

    public boolean isRoom(String name) throws SQLException {
        try {
            this.room = RoomDao.FindByName(name);
            return true;
        } catch (SQLException e) {
            this.room = RoomDao.addRoom(name);
            return false;
        }
    }

    public void resumeNotation() throws SQLException {
        for (Move note : room.getNotation()) {
            moveOnePiece(note.getSource(), note.getTarget());
        }
    }

    public void deleteNotation() throws SQLException {
        room.deleteNotation();
    }
}
