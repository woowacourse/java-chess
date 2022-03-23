package chess.domain;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Night;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class ChessGame {
    private final Map<Position, Piece> board = new HashMap<>();

    public Map<Position, Piece> start() {
        initBlack();
        initWhite();

        return board;
    }

    private void initBlack() {
        initBlackRankEight();
        initBlackRankSeven();

    }

    private void initBlackRankEight() {
        board.put(Position.of(File.a, Rank.Eight), new Rook(Position.of(File.a, Rank.Eight), Color.Black));
        board.put(Position.of(File.b, Rank.Eight), new Night(Position.of(File.b, Rank.Eight), Color.Black));
        board.put(Position.of(File.c, Rank.Eight), new Bishop(Position.of(File.c, Rank.Eight), Color.Black));
        board.put(Position.of(File.d, Rank.Eight), new Queen(Position.of(File.d, Rank.Eight), Color.Black));
        board.put(Position.of(File.e, Rank.Eight), new King(Position.of(File.e, Rank.Eight), Color.Black));
        board.put(Position.of(File.f, Rank.Eight), new Bishop(Position.of(File.f, Rank.Eight), Color.Black));
        board.put(Position.of(File.g, Rank.Eight), new Night(Position.of(File.g, Rank.Eight), Color.Black));
        board.put(Position.of(File.h, Rank.Eight), new Rook(Position.of(File.h, Rank.Eight), Color.Black));
    }

    private void initBlackRankSeven() {
        board.put(Position.of(File.a, Rank.Seven), new Pawn(Position.of(File.a, Rank.Seven), Color.Black));
        board.put(Position.of(File.b, Rank.Seven), new Pawn(Position.of(File.b, Rank.Seven), Color.Black));
        board.put(Position.of(File.c, Rank.Seven), new Pawn(Position.of(File.c, Rank.Seven), Color.Black));
        board.put(Position.of(File.d, Rank.Seven), new Pawn(Position.of(File.d, Rank.Seven), Color.Black));
        board.put(Position.of(File.e, Rank.Seven), new Pawn(Position.of(File.e, Rank.Seven), Color.Black));
        board.put(Position.of(File.f, Rank.Seven), new Pawn(Position.of(File.f, Rank.Seven), Color.Black));
        board.put(Position.of(File.g, Rank.Seven), new Pawn(Position.of(File.g, Rank.Seven), Color.Black));
        board.put(Position.of(File.h, Rank.Seven), new Pawn(Position.of(File.h, Rank.Seven), Color.Black));
    }

    private void initWhite() {
        initWhiteRankOne();
        initWhiteRankTwo();
    }

    private void initWhiteRankTwo() {
        board.put(Position.of(File.a, Rank.Two), new Pawn(Position.of(File.a, Rank.Two), Color.White));
        board.put(Position.of(File.b, Rank.Two), new Pawn(Position.of(File.b, Rank.Two), Color.White));
        board.put(Position.of(File.c, Rank.Two), new Pawn(Position.of(File.c, Rank.Two), Color.White));
        board.put(Position.of(File.d, Rank.Two), new Pawn(Position.of(File.d, Rank.Two), Color.White));
        board.put(Position.of(File.e, Rank.Two), new Pawn(Position.of(File.e, Rank.Two), Color.White));
        board.put(Position.of(File.f, Rank.Two), new Pawn(Position.of(File.f, Rank.Two), Color.White));
        board.put(Position.of(File.g, Rank.Two), new Pawn(Position.of(File.g, Rank.Two), Color.White));
        board.put(Position.of(File.h, Rank.Two), new Pawn(Position.of(File.h, Rank.Two), Color.White));
    }

    private void initWhiteRankOne() {
        board.put(Position.of(File.a, Rank.One), new Rook(Position.of(File.a, Rank.One), Color.White));
        board.put(Position.of(File.b, Rank.One), new Night(Position.of(File.b, Rank.One), Color.White));
        board.put(Position.of(File.c, Rank.One), new Bishop(Position.of(File.c, Rank.One), Color.White));
        board.put(Position.of(File.d, Rank.One), new Queen(Position.of(File.d, Rank.One), Color.White));
        board.put(Position.of(File.e, Rank.One), new King(Position.of(File.e, Rank.One), Color.White));
        board.put(Position.of(File.f, Rank.One), new Bishop(Position.of(File.f, Rank.One), Color.White));
        board.put(Position.of(File.g, Rank.One), new Night(Position.of(File.g, Rank.One), Color.White));
        board.put(Position.of(File.h, Rank.One), new Rook(Position.of(File.h, Rank.One), Color.White));
    }
}
