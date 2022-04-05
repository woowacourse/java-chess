package chess.service;

import chess.controller.ChessGame;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.Color;
import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import spark.Response;

public class ChessGameService {

    private final ChessGame chessGame;
    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameService() {
        this.chessGame = new ChessGame();
        this.chessGameDao = new ChessGameDao();
        this.pieceDao = new PieceDao();
    }

    public BoardDto start() {
        chessGame.start();
        chessGameDao.save("chess", "start");
        pieceDao.save(chessGame.getBoard().getValue());
        return BoardDto.from(chessGame.getBoard());
    }

    public BoardDto move(Response res, MoveDto moveDto) {
        chessGame.move(moveDto);
        if (chessGame.isEnded()) {
            res.redirect("/end");
        }
        pieceDao.movePiece(chessGame.getBoard().getValue(), "chess");
        chessGameDao.updateTurn(chessGame.getTurn().getValue(), "chess");
        return BoardDto.from(chessGame.getBoard());
    }

    public TurnDto turn(){
        return TurnDto.from(chessGame.getTurn());
    }

    public ScoreDto status(){
        return chessGame.status();
    }
    public Color end(){
        chessGame.end();
        chessGameDao.deleteByName("chess");
        pieceDao.deleteByGameName("chess");
        return chessGame.getWinner();
    }
}
