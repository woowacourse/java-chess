package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Article;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameStatus gameStatus;
    private Color turn = Color.WHITE;

    public ChessGame() {
        this.chessBoard = new ChessBoard(createBoard());
        this.gameStatus = GameStatus.READY;
    }

    public void play(Position from, Position to) {
        validateTurn(chessBoard.findByPiece(from), turn);

        chessBoard.movePiece(from, to);
        if (chessBoard.isKingAlive()) {
            gameStatus = GameStatus.END;
        }
        turn = turn.oppositeColor();
    }

    private void validateTurn(Article article, Color turn) {
        if (article.getColor() != turn) {
            throw new IllegalArgumentException("해당 기물의 차례가 아닙니다.");
        }
    }

    public Map<Color, Double> getStatus() {
        return chessBoard.getColorsTotalScore();
    }

    public Color getWinner() {
        return chessBoard.getWinner();
    }

    private Map<Position, Article> createBoard() {
        Map<Position, Article> squares = new HashMap<>();

        initNotPawnSquares(squares, Rank.ONE, Color.WHITE);
        initPawnPieces(squares, Rank.TWO, Color.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Color.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Color.BLACK);

        return squares;
    }

    private void initPawnPieces(Map<Position, Article> squares, Rank rank, Color color) {
        for (File file : File.values()) {
            squares.put(new Position(file, rank), new Pawn(color));
        }
    }

    private void initNotPawnSquares(Map<Position, Article> squares, Rank rank, Color color) {
        squares.put(new Position(File.A, rank), new Rook(color));
        squares.put(new Position(File.B, rank), new Knight(color));
        squares.put(new Position(File.C, rank), new Bishop(color));
        squares.put(new Position(File.D, rank), new Queen(color));
        squares.put(new Position(File.E, rank), new King(color));
        squares.put(new Position(File.F, rank), new Bishop(color));
        squares.put(new Position(File.G, rank), new Knight(color));
        squares.put(new Position(File.H, rank), new Rook(color));
    }

    public void start() {
        gameStatus = GameStatus.PLAYING;
    }

    public boolean isReady() {
        return gameStatus == GameStatus.READY;
    }

    public boolean isPlaying() {
        return gameStatus == GameStatus.PLAYING;
    }

    public boolean isEnd() {
        return gameStatus == GameStatus.END;
    }

    public Map<Position, Article> getBoard() {
        return chessBoard.getPieces();
    }
}
