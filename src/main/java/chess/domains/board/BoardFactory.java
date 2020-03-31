package chess.domains.board;

import chess.domains.piece.Piece;
import chess.domains.position.Position;
import chess.domains.position.Row;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static chess.domains.piece.Piece.COLUMN_SIZE;

public class BoardFactory {
    private static Set<PlayingPiece> board;

    static {
        List<Piece> whitePieces = Piece.getWhitePieces();
        List<Piece> blackPieces = Piece.getBlackPieces();
        List<Piece> blankPieces = Piece.getBlankPieces();

        Set<PlayingPiece> playingPieces = new HashSet<>();

        playingPieces.addAll(createPlayingPiecesByRow(Row.ONE, whitePieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.TWO, whitePieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.EIGHT, blackPieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.SEVEN, blackPieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.THREE, blankPieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.FOUR, blankPieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.FIVE, blankPieces));
        playingPieces.addAll(createPlayingPiecesByRow(Row.SIX, blankPieces));

        board = playingPieces;
    }

    public static Set<PlayingPiece> getBoard() {
        return board;
    }

    private static Set<PlayingPiece> createPlayingPiecesByRow(Row row, List<Piece> pieces) {
        List<Position> aRow = Position.fromRow(row);

        Set<PlayingPiece> playingPieces = new HashSet<>();
        for (int i = 0; i < COLUMN_SIZE; i++) {
            PlayingPiece piece = new PlayingPiece(aRow.get(i), pieces.get(i));
            playingPieces.add(piece);
        }

        return playingPieces;
    }
}
