package chess.domain.game.state;

import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.position.File;
import chess.domain.piece.position.Position;
import chess.domain.piece.position.Rank;
import chess.domain.piece.property.Color;

public class StartedGame implements GameState {

    private final ChessBoard board = new ChessBoard();

    @Override
    public GameState start() {
        initBlack();
        initWhite();

        return new RunningGame(board, Player.WHITE);
    }

    @Override
    public GameState move(Position source, Position target) {
        throw new UnsupportedOperationException("start를 해야만 move를 할 수 있습니다.");
    }

    @Override
    public Map<Color, Double> status() {
        throw new UnsupportedOperationException("start를 해야만 status를 할 수 있습니다.");
    }

    @Override
    public GameState end() {
        return new EndGame();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private void initBlack() {
        initBlackRankEight();
        initBlackRankSeven();
    }

    private void initBlackRankEight() {
        board.putPiece(Position.of(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        board.putPiece(Position.of(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        board.putPiece(Position.of(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        board.putPiece(Position.of(File.D, Rank.EIGHT), new Queen(Color.BLACK));
        board.putPiece(Position.of(File.E, Rank.EIGHT), new King(Color.BLACK));
        board.putPiece(Position.of(File.F, Rank.EIGHT), new Bishop(Color.BLACK));
        board.putPiece(Position.of(File.G, Rank.EIGHT), new Knight(Color.BLACK));
        board.putPiece(Position.of(File.H, Rank.EIGHT), new Rook(Color.BLACK));
    }

    private void initBlackRankSeven() {
        board.putPiece(Position.of(File.A, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.B, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.C, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.D, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.E, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.F, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.G, Rank.SEVEN), new Pawn(Color.BLACK));
        board.putPiece(Position.of(File.H, Rank.SEVEN), new Pawn(Color.BLACK));
    }

    private void initWhite() {
        initWhiteRankOne();
        initWhiteRankTwo();
    }

    private void initWhiteRankTwo() {
        board.putPiece(Position.of(File.A, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.B, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.C, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.D, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.E, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.F, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.G, Rank.TWO), new Pawn(Color.WHITE));
        board.putPiece(Position.of(File.H, Rank.TWO), new Pawn(Color.WHITE));
    }

    private void initWhiteRankOne() {
        board.putPiece(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        board.putPiece(Position.of(File.B, Rank.ONE), new Knight(Color.WHITE));
        board.putPiece(Position.of(File.C, Rank.ONE), new Bishop(Color.WHITE));
        board.putPiece(Position.of(File.D, Rank.ONE), new Queen(Color.WHITE));
        board.putPiece(Position.of(File.E, Rank.ONE), new King(Color.WHITE));
        board.putPiece(Position.of(File.F, Rank.ONE), new Bishop(Color.WHITE));
        board.putPiece(Position.of(File.G, Rank.ONE), new Knight(Color.WHITE));
        board.putPiece(Position.of(File.H, Rank.ONE), new Rook(Color.WHITE));
    }
}
