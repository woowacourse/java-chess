package chess.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.dao.ChessDao;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.SquareDto;

public class ChessService {

    private final ChessDao chessDao;
    private ChessGame chessGame;

    public ChessService() {
        this.chessDao = new ChessDao();
        this.chessGame = new ChessGame();
    }

    public void startGame() {
        List<SquareDto> squares = chessDao.getBoardSquares();
        if (squares.isEmpty()) {
            initializeGameBoard(squares);
            return;
        }
        startGameWithStoredBoard(squares);
    }

    private void initializeGameBoard(List<SquareDto> squares) {
        chessGame.start(BoardFactory.getInitialPieces(), Color.WHITE);
        BoardDto boardDto = new BoardDto(chessGame.getBoard().getValue());
        squares.addAll(boardDto.getSquares());
        chessDao.insertBoardSquares(squares);
        setState(Color.WHITE.name());
    }

    private void setState(String color) {
        chessDao.deleteAllState();
        chessDao.insertState(color);
    }

    private void startGameWithStoredBoard(List<SquareDto> squares) {
        Map<Position, Piece> pieces = getPieces(squares);
        Color turnColor = Color.get(chessDao.getState());
        chessGame.start(pieces, turnColor);
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
        List<SquareDto> squares = chessDao.getBoardSquares();
        if (squares.isEmpty()) {
            BoardDto boardDto = new BoardDto(BoardFactory.getInitialPieces());
            squares.addAll(boardDto.getSquares());
            chessDao.insertBoardSquares(squares);
        }
        return new BoardDto(squares);
    }

    public void movePiece(String source, String target) {
        Piece sourcePiece = chessGame.getBoard().getValue().get(Position.valueOf(source));
        chessGame.movePiece(source, target);
        setState(chessGame.getTurn().name());
        chessDao.updateBoardSquare(source, null, null);
        chessDao.updateBoardSquare(target, sourcePiece.getClass().getSimpleName(), sourcePiece.getColorName());
    }

    public boolean isGameFinish() {
        return chessGame.isFinish();
    }

    public Map<Color, Double> getGameScores() {
        return chessGame.calculateScore();
    }

    public Color getWinner() {
        return chessGame.judgeWinner();
    }

    public void deleteCurrentGame() {
        chessDao.deleteBoard();
        chessDao.deleteAllState();
        this.chessGame = new ChessGame();
    }
}
