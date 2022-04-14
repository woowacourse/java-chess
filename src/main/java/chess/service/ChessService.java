package chess.service;

import java.util.Map;

import chess.dao.BoardDao;
import chess.dao.StateDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Running;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class ChessService {

    private final BoardDao boardDao;
    private final StateDao stateDao;

    public ChessService() {
        this.boardDao = new BoardDao();
        this.stateDao = new StateDao();
    }

    public void startInitializedGame() {
        final ChessGame chessGame = new ChessGame();
        chessGame.start(BoardFactory.getInitialPieces(), Color.WHITE);
        initializeGameBoard();
        stateDao.updateState(chessGame.getState(), chessGame.getTurn().name());
    }

    private void initializeGameBoard() {
        boardDao.deleteAll();
        for (Map.Entry<Position, Piece> entry : BoardFactory.getInitialPieces().entrySet()) {
            final String fileName = entry.getKey().getFile().name().toLowerCase();
            final int rankName = entry.getKey().getRank().getValue();
            boardDao.insertBoardSquare(
                fileName + rankName,
                entry.getValue().representative(),
                entry.getValue().getColorName()
            );
        }
    }

    public Map<Position, Piece> getPieces() {
        return boardDao.getBoardSquares();
    }

    public void movePiece(final String source, final String target) {
        final ChessGame chessGame = createChessGameWithState();
        Piece sourcePiece = chessGame.getBoard().findPiece(Position.valueOf(source));
        chessGame.movePiece(source, target);
        boardDao.deleteBoardSquare(target);
        boardDao.updateBoardSquare(source, target, sourcePiece.representative(), sourcePiece.getColorName());
        if (chessGame.isFinish()) {
            stateDao.updateState(chessGame.getState());
            return;
        }
        stateDao.updateState(chessGame.getState(), chessGame.getTurn().name());
    }

    private ChessGame createChessGameWithState() {
        final GameState state = new Running(new Board(getPieces()), Color.get(stateDao.getState().getColor()));
        return new ChessGame(state);
    }

    public boolean isGameFinish() {
        return stateDao.getState().isFinish();
    }

    public boolean isGameWaiting() {
        return stateDao.getState().isWaiting();
    }

    public Map<Color, Double> getGameScores() {
        final Score score = new Score(boardDao.getBoardSquares());
        return score.getAllTeamScore();
    }

    public Color getWinner() {
        final Winner winner = new Winner(boardDao.getBoardSquares());
        return winner.color();
    }
}
