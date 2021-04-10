package chess.service;

import chess.domain.board.Board;
import chess.domain.dao.ChessGameDAO;
import chess.domain.dao.PieceDAO;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.EndGameDto;
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

    public ChessBoardDto createNewChessGame(String roomID) {
        Optional<ChessGameEntity> existChessGame = chessGameDAO.findRoomID(roomID);

        if(existChessGame.isPresent()) {
            throw new IllegalArgumentException();
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
        Board board = pieceDAO.loadPiecesByRoomID(roomID);
        ChessGame chessGame = new ChessGame(board, roomID);
        chessGame.changeState(new WhiteTurn(chessGame));
        if("black".equals(chessGameDAO.loadGameStatusByRoomID(roomID))) {
            chessGame.changeState(new BlackTurn(chessGame));
        }

        chessGame.move(mpi.getSource(), mpi.getTarget());

        if(chessGame.isFinished()) {
            return new EndGameDto("왕을 잡아 게임이 종료됩니다.");
        }
        pieceDAO.updatePieces(mpi.getRoomID(), chessGame);
        chessGameDAO.update(chessGame);

        return new ChessBoardDto(chessGame);
    }

    public Object endChessGame(String roomID) {
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
        System.out.println("-----------------------");
        System.out.println(chessGame.getBlackScore());
        System.out.println(chessGame.getWhiteScore());

        return new ChessBoardDto(chessGame);
    }
}
