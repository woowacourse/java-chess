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

        return new RunningGame(board, Player.White);
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
        board.putPiece(Position.of(File.a, Rank.Eight), new Rook(Color.Black));
        board.putPiece(Position.of(File.b, Rank.Eight), new Knight(Color.Black));
        board.putPiece(Position.of(File.c, Rank.Eight), new Bishop(Color.Black));
        board.putPiece(Position.of(File.d, Rank.Eight), new Queen(Color.Black));
        board.putPiece(Position.of(File.e, Rank.Eight), new King(Color.Black));
        board.putPiece(Position.of(File.f, Rank.Eight), new Bishop(Color.Black));
        board.putPiece(Position.of(File.g, Rank.Eight), new Knight(Color.Black));
        board.putPiece(Position.of(File.h, Rank.Eight), new Rook(Color.Black));
    }

    private void initBlackRankSeven() {
        board.putPiece(Position.of(File.a, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.b, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.c, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.d, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.e, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.f, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.g, Rank.Seven), new Pawn(Color.Black));
        board.putPiece(Position.of(File.h, Rank.Seven), new Pawn(Color.Black));
    }

    private void initWhite() {
        initWhiteRankOne();
        initWhiteRankTwo();
    }

    private void initWhiteRankTwo() {
        board.putPiece(Position.of(File.a, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.b, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.c, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.d, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.e, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.f, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.g, Rank.Two), new Pawn(Color.White));
        board.putPiece(Position.of(File.h, Rank.Two), new Pawn(Color.White));
    }

    private void initWhiteRankOne() {
        board.putPiece(Position.of(File.a, Rank.One), new Rook(Color.White));
        board.putPiece(Position.of(File.b, Rank.One), new Knight(Color.White));
        board.putPiece(Position.of(File.c, Rank.One), new Bishop(Color.White));
        board.putPiece(Position.of(File.d, Rank.One), new Queen(Color.White));
        board.putPiece(Position.of(File.e, Rank.One), new King(Color.White));
        board.putPiece(Position.of(File.f, Rank.One), new Bishop(Color.White));
        board.putPiece(Position.of(File.g, Rank.One), new Knight(Color.White));
        board.putPiece(Position.of(File.h, Rank.One), new Rook(Color.White));
    }
}
