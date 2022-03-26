package chess.domain;

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

public class ChessGame {
    private ChessBoard board;
    private Player player = Player.White;

    public Map<Position, Piece> start() {
        board = new ChessBoard();
        initBlack();
        initWhite();

        return board.getBoard();
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

    public void move(Position source, Position target) {
        validatePosition(source);
        board.move(source, target);
        player = player.change();
    }

    private void validatePosition(Position source) {
        board.validateExist(source);
        checkMyPiece(board.getPiece(source));
    }

    private void checkMyPiece(Piece sourcePiece) {
        if (!player.isMyPiece(sourcePiece)) {
            throw new IllegalArgumentException("자신의 말만 움직일 수 있습니다.");
        }
    }
}
