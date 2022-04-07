package chess.service;

import chess.dao.ChessGameDao;
import chess.dao.PieceDao;
import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.MoveResult;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import chess.dto.ColorDto;
import chess.dto.MoveDto;
import chess.dto.ScoreDto;
import java.util.Map;
import spark.Response;

public class ChessGameService {

    private final ChessGameDao chessGameDao;
    private final PieceDao pieceDao;

    public ChessGameService() {
        this.chessGameDao = new ChessGameDao();
        this.pieceDao = new PieceDao();
    }

    public BoardDto start() {
        chessGameDao.save("chess", "white");
        Board board = BoardFactory.createBoard();
        pieceDao.save(BoardDto.from(board).getBoard());
        return BoardDto.from(board);
    }

    public BoardDto move(Response res, MoveDto moveDto) {
        Color turn = getTurn();
        Board board = BoardFactory.createBoardBy(pieceDao.findAllByGameName("chess"));

        MoveResult moveResult = board.move(moveDto.getFrom(), moveDto.getTo(), turn);
        BoardDto updateBoard = BoardDto.from(board);

        if (moveResult == MoveResult.ENDED) {
            res.redirect("/end");
        }
        if (moveResult == MoveResult.SUCCESS) {
            pieceDao.movePiece(updateBoard.getBoard(), "chess");
            chessGameDao.updateTurn(turn.nextColor().getValue(), "chess");
        }
        return updateBoard;
    }

    private Color getTurn() {
        return chessGameDao.findByName("chess")
                .orElseThrow(() -> new IllegalArgumentException("체스 게임 명이 올바르지 않습니다."))
                .getValue();
    }

    public ColorDto turn() {
        return ColorDto.from(getTurn());
    }

    public ScoreDto status() {
        Map<Position, Piece> boardInfo = pieceDao.findAllByGameName("chess");
        Board board = BoardFactory.createBoardBy(boardInfo);
        return ScoreDto.from(board.getScore());
    }

    public Color end() {
        Color winnerColor = getTurn();
        pieceDao.deleteByGameName("chess");
        chessGameDao.deleteByName("chess");
        return winnerColor;
    }
}
