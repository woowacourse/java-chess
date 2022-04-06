package chess.service;

import chess.model.ChessGame;
import chess.model.GameResult;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dao.PieceDao;
import chess.model.dao.TurnDao;
import chess.model.dto.MoveDto;
import chess.model.dto.WebBoardDto;
import chess.model.position.Position;

public class ChessService {
    private ChessGame chessGame;
    private final PieceDao pieceDao;
    private final TurnDao turnDao;
    private Turn turn;

    public ChessService() {
        this.pieceDao = new PieceDao();
        this.turnDao = new TurnDao();
    }

    public WebBoardDto start() {
        Board board = BoardFactory.create();
        pieceDao.init(board);
        turnDao.init();
        chessGame = new ChessGame(board);

        return WebBoardDto.from(board);
    }

    public WebBoardDto move(MoveDto moveDto) {
        Position source = Position.from(moveDto.getSource());
        Position target = Position.from(moveDto.getTarget());
        Turn turn = Turn.from(turnDao.findOne());
        try {
            chessGame.move(source, target, turn);
        } catch(Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        turn = turn.change();
        if (chessGame.isKingDead()) {
            turn = turn.finish();
        }

        return WebBoardDto.from(chessGame.getBoard());
    }

    public String getTurn() {
        return turn.getThisTurn();
    }

    public boolean isKingDead() {
        return chessGame.isKingDead();
    }

    public GameResult getResult() {
        return chessGame.getWinningResult();
    }
}
