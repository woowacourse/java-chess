package view.dto;

import java.util.List;
import model.chessboard.ChessBoard;

public class InfoMapper {

    private InfoMapper() {
        throw new AssertionError("정적 유틸 클래스는 생성자를 호출할 수 없습니다.");
    }

    public static List<PieceInfo> toPieceInfoMapper(ChessBoard chessBoard) {
        return chessBoard.getChessBoard()
                .entrySet()
                .stream()
                .map(entry -> new PieceInfo(
                        entry.getKey()
                                .file(),
                        entry.getKey()
                                .rank(),
                        entry.getValue()
                                .getRole()
                                .getClass()
                                .getSimpleName(),
                        entry.getValue()
                                .getColor()
                                .name())
                )
                .toList();
    }
}
