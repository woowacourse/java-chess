package chess.service;

import chess.model.ChessGame;
import chess.model.GameResult;
import chess.model.Turn;
import chess.model.board.Board;
import chess.model.board.BoardFactory;
import chess.model.dao.BoardDao;
import chess.model.dto.MoveDto;
import chess.model.dto.WebBoardDto;
import chess.model.position.Position;

public class ChessService {
    private ChessGame chessGame;
    private BoardDao boardDao;
    private Turn turn;

    public WebBoardDto start() {
        Board board = BoardFactory.create();
        chessGame = new ChessGame(board);
        turn = Turn.init();

        return WebBoardDto.from(board);
    }

    public WebBoardDto move(MoveDto moveDto) {
        Position source = Position.from(moveDto.getSource());
        Position target = Position.from(moveDto.getTarget());
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
