package chess.view;

import chess.domain.dto.BoardDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.ResultDto;

import java.util.List;

public class OutputView {

    public void printBoard(BoardDto dto) {
        List<List<PieceDto>> pieces = dto.getPieces();
        for (int i = pieces.size() - 1; i >= 0; i--) {
            printRank(pieces.get(i));
        }
        System.out.println();
    }

    private void printRank(List<PieceDto> pieceRespons) {
        pieceRespons.forEach(this::printPiece);
        System.out.println();
    }

    private void printPiece(PieceDto pieceDto) {
        System.out.print(formatByColor(pieceDto.getPieceName(), pieceDto.getPieceColor()));
    }

    private String formatByColor(String pieceType, String pieceColor) {
        if ("BLACK".equals(pieceColor)) {
            return pieceType.toUpperCase();
        }
        return pieceType;
    }

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 결과 보기 : status");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printCommandError(String error) {
        System.out.println(error);
    }

    public void printResult(ResultDto resultDto) {
        System.out.println(resultDto.getWinner());
        System.out.println("흰색 점수 : " + resultDto.getWhiteScore());
        System.out.println("검은색 점수 : " + resultDto.getBlackScore());
    }
}
