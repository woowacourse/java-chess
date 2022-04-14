package chess.web.service;

import chess.domain.Board;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.generator.EmptyBoardGenerator;
import chess.domain.generator.InitBoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.state.StateType;
import chess.web.dao.BoardStateDao;
import chess.web.dao.BoardStateDaoImpl;
import chess.web.dao.PieceDao;
import chess.web.dao.PieceDaoImpl;
import chess.web.dto.PieceDto;
import java.util.List;

public class ChessService {

    private static final int BOARD_START_INDEX = 0;
    private static final int BOARD_END_INDEX = 7;

    private final BoardStateDao boardStateDao;
    private final PieceDao pieceDao;

    public ChessService() {
        this.boardStateDao = new BoardStateDaoImpl();
        this.pieceDao = new PieceDaoImpl();
    }

    public void start() {
        boardStateDao.save(StateType.WHITE_TURN);

        ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());
        initChessBoard(chessBoard.getBoard());
    }

    private void initChessBoard(Board board) {
        for (int rankIndex = BOARD_START_INDEX; rankIndex <= BOARD_END_INDEX; rankIndex++) {
            initOneRank(board, rankIndex);
        }
    }

    private void initOneRank(Board board, int rankIndex) {
        for (int fileIndex = BOARD_START_INDEX; fileIndex <= BOARD_END_INDEX; fileIndex++) {
            Position position = new Position(fileIndex, rankIndex);
            Piece piece = board.findPiece(position);
            pieceDao.save(new PieceDto(piece, position));
        }
    }

    public void end() {
        boardStateDao.deleteAll();
        pieceDao.deleteAll();
    }

    public void move(ChessGame chessGame, Position source, Position target) {
        updateState(chessGame);
        pieceDao.update(new PieceDto(chessGame.board().findPiece(target), target));
        pieceDao.update(new PieceDto(chessGame.board().findPiece(source), source));
    }

    public void updateState(ChessGame chessGame) {
        boardStateDao.update(chessGame.getStateType());
    }

    public ChessGame getChessGame() {
        Board board = getBoardFromDtos(getPieces());
        ChessBoard chessBoard = new ChessBoard(board);
        return new ChessGame(getStateType().newState(chessBoard));
    }

    private Board getBoardFromDtos(List<PieceDto> pieceDtos) {
        Board board = new Board(new EmptyBoardGenerator().generate().getBoard());
        for (PieceDto pieceDto : pieceDtos) {
            board.place(new Position(pieceDto.getPosition()),
                    PieceType.from(pieceDto.getPieceType()).newPiece(Color.from(pieceDto.getColor())));
        }
        return board;
    }

    private StateType getStateType() {
        return boardStateDao.selectState();
    }

    public List<PieceDto> getPieces() {
        return pieceDao.selectAll();
    }

    public double getScore(Color color) {
        return getChessGame().score(color);
    }

    public boolean isNotRunning() {
        if (getStateType() == null) {
            return true;
        }
        return !getChessGame().isRunning();
    }
}
