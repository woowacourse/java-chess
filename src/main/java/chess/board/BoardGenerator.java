package chess.board;

import chess.board.piece.Pieces;

public class BoardGenerator {

    public BoardGenerator() {
    }

    public ChessBoard create() {
        ChessBoard chessBoard = ChessBoard.empty();
        addRankEight(chessBoard);
        addRankSeven(chessBoard);
        addBlank(chessBoard);
        addRankTwo(chessBoard);
        addRankOne(chessBoard);
        return chessBoard;
    }

    private void addRankEight(ChessBoard chessBoard) {
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.EIGHT), Pieces.BLACK_ROOK.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.B, Rank.EIGHT), Pieces.BLACK_KNIGHT.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.C, Rank.EIGHT), Pieces.BLACK_BISHOP.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.D, Rank.EIGHT), Pieces.BLACK_QUEEN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.E, Rank.EIGHT), Pieces.BLACK_KING.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.F, Rank.EIGHT), Pieces.BLACK_BISHOP.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.G, Rank.EIGHT), Pieces.BLACK_KNIGHT.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.H, Rank.EIGHT), Pieces.BLACK_ROOK.getPiece()));
    }

    private void addRankOne(ChessBoard chessBoard) {
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.ONE), Pieces.WHITE_ROOK.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.B, Rank.ONE), Pieces.WHITE_KNIGHT.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.C, Rank.ONE), Pieces.WHITE_BISHOP.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.D, Rank.ONE), Pieces.WHITE_QUEEN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.E, Rank.ONE), Pieces.WHITE_KING.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.F, Rank.ONE), Pieces.WHITE_BISHOP.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.G, Rank.ONE), Pieces.WHITE_KNIGHT.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.H, Rank.ONE), Pieces.WHITE_ROOK.getPiece()));
    }

    private void addRankSeven(ChessBoard chessBoard) {
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.B, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.C, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.D, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.E, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.F, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.G, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.H, Rank.SEVEN), Pieces.BLACK_PAWN.getPiece()));
    }

    private void addRankTwo(ChessBoard chessBoard) {
        chessBoard.put(new Tile(Coordinate.of(File.A, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.B, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.C, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.D, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.E, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.F, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.G, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
        chessBoard.put(new Tile(Coordinate.of(File.H, Rank.TWO), Pieces.WHITE_PAWN.getPiece()));
    }

    private void addBlank(ChessBoard chessBoard) {
        for (int rankValue = 3; rankValue <= 6; rankValue++) {
            Rank rank = Rank.findByValue(rankValue);
            chessBoard.put(new Tile(Coordinate.of(File.A, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.B, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.C, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.D, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.E, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.F, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.G, rank), Pieces.BLANK.getPiece()));
            chessBoard.put(new Tile(Coordinate.of(File.H, rank), Pieces.BLANK.getPiece()));
        }
    }

}
