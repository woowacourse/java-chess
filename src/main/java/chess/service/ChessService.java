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
import chess.model.piece.Piece;
import chess.model.position.Position;

import java.util.Map;

public class ChessService {
    private final PieceDao pieceDao;
    private final TurnDao turnDao;
    private ChessGame chessGame;

    public ChessService() {
        this.pieceDao = new PieceDao();
        this.turnDao = new TurnDao();
    }

    public WebBoardDto start() {
        Board board = initBoard();
        initTurn();
        chessGame = new ChessGame(board);

        return WebBoardDto.from(board);
    }

    private Board initBoard() {
        Map<Position, Piece> board = pieceDao.findAll();

        if (board.size() == 0) {
            pieceDao.init(BoardFactory.create());
        }

        return new Board(pieceDao.findAll());
    }

    private void initTurn() {
        String turn = turnDao.findOne();

        if (turn.equals("")) {
            turnDao.init();
        }
    }

    public WebBoardDto move(MoveDto moveDto) {
        Position source = Position.from(moveDto.getSource());
        Position target = Position.from(moveDto.getTarget());
        Turn turn = Turn.from(turnDao.findOne());
        try {
            chessGame.move(source, target, turn);
            String originalSourcePiece = pieceDao.findByPosition(moveDto.getSource());
            pieceDao.updateByPosition(moveDto.getTarget(), originalSourcePiece);
            pieceDao.updateByPosition(moveDto.getSource(), "none-.");
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        turnDao.update(turn.change().getThisTurn());
        if (chessGame.isKingDead()) {
            turnDao.update(turn.finish());
        }

        return WebBoardDto.from(chessGame.getBoard());
    }

    public String getTurn() {
        return turnDao.findOne();
    }

    public boolean isKingDead() {
        return chessGame.isKingDead();
    }

    public GameResult getResult() {
        return chessGame.getWinningResult();
    }

    public void exitGame() {
        pieceDao.deleteAll();
        turnDao.deleteAll();
    }
}
