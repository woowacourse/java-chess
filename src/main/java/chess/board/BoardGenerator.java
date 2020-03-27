package chess.board;

import chess.board.piece.*;

public class BoardGenerator {

    public BoardGenerator() {
    }

    public ChessBoard create() {
        ChessBoard chessBoard = ChessBoard.empty();
        addTopAndBottom(chessBoard, Rank.EIGHT, Team.BLACK);
        addTopAndBottom(chessBoard, Rank.ONE, Team.WHITE);
        addPawn(chessBoard, Rank.SEVEN, Team.BLACK);
        addPawn(chessBoard, Rank.TWO, Team.WHITE);
        addBlank(chessBoard);
        return chessBoard;
    }

    private void addTopAndBottom(ChessBoard chessBoard, Rank rank, Team team) {
        chessBoard.put(new Tile(Coordinate.of(File.A, rank), Pieces.findBy(Rook.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.B, rank), Pieces.findBy(Knight.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.C, rank), Pieces.findBy(Bishop.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.D, rank), Pieces.findBy(Queen.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.E, rank), Pieces.findBy(King.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.F, rank), Pieces.findBy(Bishop.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.G, rank), Pieces.findBy(Knight.class, team)));
        chessBoard.put(new Tile(Coordinate.of(File.H, rank), Pieces.findBy(Rook.class, team)));
    }

    private void addPawn(ChessBoard chessBoard, Rank rank, Team team) {
        for (File file : File.values()) {
            chessBoard.put(new Tile(Coordinate.of(file, rank), Pieces.findBy(NotMovedPawn.class, team)));
        }
    }


    private void addBlank(ChessBoard chessBoard) {
        for (File file : File.values()) {
            for (int rank = 3; rank <= 6; rank++) {
                chessBoard.put(new Tile(Coordinate.of(file, Rank.findByValue(rank)), Pieces.findBy(Blank.class, Team.NOTHING)));
            }
        }
    }
}
