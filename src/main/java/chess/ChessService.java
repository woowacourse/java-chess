package chess;

import chess.dao.BoardDao;
import chess.dao.ConnectionManager;
import chess.domain.Board;
import chess.model.piece.Bishop;
import chess.model.piece.Color;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import chess.model.status.Ready;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ChessService {

    private final BoardDao boardDao;


    public ChessService() {
        this.boardDao = new BoardDao(new ConnectionManager());
    }
//
//    public Board init() {
//        final List<File> files = Arrays.asList(File.values());
//
//        initMajorPieces(Color.WHITE, Rank.ONE, files);
//        initMajorPieces(Color.BLACK, Rank.EIGHT, files);
//        initPawns(Color.WHITE, Rank.TWO, files);
//        initPawns(Color.BLACK, Rank.SEVEN, files);
//        initEmpty();
//    }
//
//
//    private void initMajorPieces(Color color, Rank rank, List<File> files) {
//        List<Piece> majorPiecesLineup = majorPiecesLineup(color);
//        for (int i = 0; i < majorPiecesLineup.size(); i++) {
//
//            board.put(Square.of(files.get(i), rank), majorPiecesLineup.get(i));
//        }
//    }
//
//    private List<Piece> majorPiecesLineup(final Color color) {
//        return List.of(
//                new Rook(color),
//                new Knight(color),
//                new Bishop(color),
//                new Queen(color),
//                new King(color),
//                new Bishop(color),
//                new Knight(color),
//                new Rook(color)
//        );
//    }
}
