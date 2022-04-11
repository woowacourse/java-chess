package chess.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.dao.BoardDao;
import chess.dao.StateDao;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Winner;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.SquareDto;

public class ChessService {

    private final BoardDao boardDao;
    private final StateDao stateDao;

    public ChessService() {
        this.boardDao = new BoardDao();
        this.stateDao = new StateDao();
    }

    public void startInitializedGame() {
        ChessGame chessGame = new ChessGame();
        chessGame.start(BoardFactory.getInitialPieces(), Color.WHITE);
        initializeGameBoard();
        stateDao.updateState(chessGame.getState(), Color.WHITE.name());
    }

    private void initializeGameBoard() {
        BoardDto boardDto = new BoardDto(BoardFactory.getInitialPieces());
        List<SquareDto> squares = boardDto.getSquares();
        for (SquareDto square : squares) {
            initializeBoardSquares(square);
        }
    }

    private void initializeBoardSquares(final SquareDto square) {
        final Position position = square.getPosition();
        final String fileName = position.getFile().name().toLowerCase();
        final int rankName = position.getRank().getValue();
        if (square.getPiece() == null) {
            boardDao.updateBoardSquare(fileName + rankName, null, null);
            return;
        }
        final Piece piece = square.getPiece();
        boardDao.updateBoardSquare(fileName + rankName, piece.representative(), piece.getColorName());
    }

    private Map<Position, Piece> getPieces(List<SquareDto> squares) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (SquareDto square : squares) {
            if (square.getPiece() != null) {
                pieces.put(square.getPosition(), square.getPiece());
            }
        }
        return pieces;
    }

    public BoardDto getBoard() {
        return new BoardDto(boardDao.getBoardSquares());
    }

    public void movePiece(String source, String target) {
        ChessGame chessGame = new ChessGame();
        chessGame.start(getPieces(getBoard().getSquares()), Color.get(stateDao.getState().getColor()));
        Piece sourcePiece = chessGame.getBoard().findPiece(Position.valueOf(source));
        chessGame.movePiece(source, target);
        boardDao.updateBoardSquare(source, null, null);
        boardDao.updateBoardSquare(target, sourcePiece.representative(), sourcePiece.getColorName());
        if (chessGame.isFinish()) {
            stateDao.updateState(chessGame.getState(), Color.NONE.name());
            return;
        }
        stateDao.updateState(chessGame.getState(), chessGame.getTurn().name());
    }

    public boolean isGameFinish() {
        return stateDao.getState().isFinish();
    }

    public boolean isGameWaiting() {
        return stateDao.getState().isWaiting();
    }

    public Map<Color, Double> getGameScores() {
        Score score = new Score(getPieces(getBoard().getSquares()));
        return score.getAllTeamScore();
    }

    public Color getWinner() {
        Winner winner = new Winner(getPieces(getBoard().getSquares()));
        return winner.color();
    }
}
