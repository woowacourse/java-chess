package chess.web.controller;

import chess.domain.Board;
import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.domain.generator.EmptyBoardGenerator;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.state.State;
import chess.domain.state.StateType;
import chess.web.dao.BoardDao;
import chess.web.dao.PieceDao;
import chess.web.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.Request;
import spark.Response;

public class WebChessController {

    private static final int BOARD_START_INDEX = 0;
    private static final int BOARD_END_INDEX = 7;

    private final BoardDao boardDao;
    private final PieceDao pieceDao;
    private ChessGame chessGame;

    public WebChessController(ChessGame chessGame, BoardDao boardDao, PieceDao pieceDao) {
        this.chessGame = chessGame;
        this.boardDao = boardDao;
        this.pieceDao = pieceDao;
    }

    public Map<String, Object> indexModel() {
        List<PieceDto> pieceDtos = pieceDao.selectAll();
        Board board = getBoardFromDtos(pieceDtos);
        State state = boardDao.selectState().newState(new ChessBoard(board));
        chessGame = new ChessGame(state);

        Map<String, Object> model = new HashMap<>();
        model.put("pieces", pieceDtos);
        model.put("black-score", chessGame.score(Color.BLACK));
        model.put("white-score", chessGame.score(Color.WHITE));
        return model;
    }

    private Board getBoardFromDtos(List<PieceDto> pieceDtos) {
        Board board = new Board(new EmptyBoardGenerator().generate().getBoard());
        for (PieceDto pieceDto : pieceDtos) {
            board.place(new Position(pieceDto.getPosition()),
                    PieceType.from(pieceDto.getPieceType()).newPiece(Color.from(pieceDto.getColor())));
        }
        return board;
    }

    public void movePiece(Request req, Response res) {
        chessGame.move(req.queryParams("source"), req.queryParams("target"));
        if (chessGame.isFinished()) {
            res.redirect("/winner");
        }

        Position source = new Position(req.queryParams("source"));
        Position target = new Position(req.queryParams("target"));
        updateChessGame(source, target);
    }

    private void updateChessGame(Position source, Position target) {
        boardDao.update(chessGame.getStateType());
        pieceDao.update(new PieceDto(chessGame.board().findPiece(target), target));
        pieceDao.update(new PieceDto(chessGame.board().findPiece(source), source));
    }

    public void startChess() {
        chessGame.start();

        Board board = chessGame.board();
        initChessBoard(board);

        boardDao.save(chessGame.getStateType());
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

    public Map<String, Object> getWinnerModel() {
        endChessGame();
        initEmptyChess();

        Map<String, Object> model = new HashMap<>();
        model.put("black-score", chessGame.score(Color.WHITE));
        model.put("white-score", chessGame.score(Color.BLACK));
        model.put("winner", chessGame.result().toString());
        return model;
    }

    private void endChessGame() {
        if (!chessGame.isFinished()) {
            chessGame.end();
        }
    }

    private void initEmptyChess() {
        boardDao.update(StateType.READY);
        pieceDao.deleteAll();
    }

    public Map<String, Object> getExceptionModel(Exception exception) {
        Map<String, Object> model = new HashMap<>();
        model.put("error-message", exception.getMessage());
        model.put("pieces", pieceDao.selectAll());
        model.put("black-score", chessGame.score(Color.WHITE));
        model.put("white-score", chessGame.score(Color.BLACK));
        return model;
    }

    public boolean isReady() {
        return chessGame.getStateType() == StateType.READY;
    }
}
