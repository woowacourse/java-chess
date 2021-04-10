package chess.service;

import chess.domain.board.Board;
import chess.domain.dao.ChessGameDAO;
import chess.domain.dao.PieceDAO;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.EndGameDto;
import chess.domain.dto.MessageDto;
import chess.domain.dto.RoomIdDto;
import chess.domain.game.*;
import chess.domain.piece.MovePositionInfo;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

import java.util.List;
import java.util.Optional;

public class ChessGameService {
    private final PieceDAO pieceDAO;
    private final ChessGameDAO chessGameDAO;

    public ChessGameService(final PieceDAO pieceDAO, final ChessGameDAO chessGameDAO) {
        this.pieceDAO = pieceDAO;
        this.chessGameDAO = chessGameDAO;
    }

    public Object createNewChessGame(String roomID) {
        Optional<ChessGameEntity> existChessGame = chessGameDAO.findRoomID(roomID);

        if(existChessGame.isPresent()) {
            return new MessageDto("같은 이름의 게임이 존재합니다.");
        }

        chessGameDAO.create(roomID);
        List<Piece> pieces = PieceFactory.createPieces();
        pieceDAO.saveAll(roomID, pieces);
        ChessGame chessGame = new ChessGame(new Board(pieces), roomID);
        chessGame.start();

        return new ChessBoardDto(chessGame);
    }

    public Object moveChessByRoomID(MovePositionInfo mpi) {
        String roomID = mpi.getRoomID();
        ChessGame chessGame = new ChessGame(pieceDAO.loadPiecesByRoomID(roomID), roomID);
        checkState(chessGame);
        try{
            chessGame.move(mpi.getSource(), mpi.getTarget());
        }catch(Exception e) {
            return new MessageDto("이동할 수 없습니다.");
        }

        if(chessGame.isFinished()) {
            endChessGame(roomID);
            return new EndGameDto("왕을 잡아 게임이 종료됩니다.");
        }
        pieceDAO.updatePieces(mpi.getRoomID(), chessGame);
        chessGameDAO.update(chessGame);

        return new ChessBoardDto(chessGame);
    }

    public EndGameDto endChessGame(String roomID) {
        Board board = pieceDAO.loadPiecesByRoomID(roomID);
        ChessGame chessGame = new ChessGame(board, roomID);
        chessGame.changeState(new End(chessGame));

        pieceDAO.deleteGameByRoomID(roomID);
        chessGameDAO.deleteGameByRoomID(roomID);

        return new EndGameDto(chessGame);
    }

    public ChessBoardDto chessGameStatus(String roomID) {
        Board board = pieceDAO.loadPiecesByRoomID(roomID);
        ChessGame chessGame = new ChessGame(board, roomID);

        return new ChessBoardDto(chessGame);
    }

    public RoomIdDto chessGames() {
        List<String> roomIDs = chessGameDAO.chessGameNames();

        return new RoomIdDto(roomIDs);
    }

    public ChessBoardDto loadChessGameByRoomID(String roomID) {
        Board board = pieceDAO.loadPiecesByRoomID(roomID);
        ChessGame chessGame = new ChessGame(board, roomID);
        checkState(chessGame);

        return new ChessBoardDto(chessGame);
    }

    private void checkState(ChessGame chessGame) {
        chessGame.changeState(new WhiteTurn(chessGame));
        if("black".equals(chessGameDAO.loadGameTurnByRoomID(chessGame.getRoomID()))) {
            chessGame.changeState(new BlackTurn(chessGame));
        }
    }
}
