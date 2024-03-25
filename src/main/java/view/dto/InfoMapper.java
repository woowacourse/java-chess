package view.dto;

import model.chessboard.ChessBoard;
import model.piece.Piece;
import model.position.File;
import model.position.Position;
import model.position.Rank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InfoMapper {

    private InfoMapper() {
        throw new AssertionError("정적 유틸 클래스는 생성자를 호출할 수 없습니다.");
    }

    public static List<PieceInfo> toPieceInfo(final ChessBoard chessBoard) {
        List<PieceInfo> pieceInfos = new ArrayList<>();
        Map<Position, Piece> pieces = chessBoard.getChessBoard();
        for (Rank rank : Rank.values()) {
            fillRow(pieces, rank, pieceInfos);
        }
        return pieceInfos;
    }

    private static void fillRow(Map<Position, Piece> pieces, Rank rank, List<PieceInfo> pieceInfos) {
        for (File file : File.values()) {
            Piece piece = pieces.get(Position.of(file, rank));
            PieceInfo pieceInfo = new PieceInfo(file, rank, piece.role().status(), piece.color());
            pieceInfos.add(pieceInfo);
        }
    }
}
