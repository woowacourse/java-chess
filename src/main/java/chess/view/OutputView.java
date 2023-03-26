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
        System.out.println("> 체스 게임에 입장합니다");
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

    public void printLoginMessage() {
        System.out.println();
        System.out.println("로그인을 할 경우 \"login Id passWord\" 를 영어와 숫자로만 입력해주세요.");
        System.out.println("회원 가입을 원할 경우 \"signUp id passWord userName\"을 영어와 숫자로만 입력해주세요");
        System.out.println();
    }

    public void ensureLoginMessage() {
        System.out.println("회원 가입 혹은 로그인 명령을 내려주세요");
    }

    public void printInvalidCommand() {
        System.out.println("올바르지 않은 커맨드 입력입니다.");
    }

    public void printCurrentPlayRoom(List<String> roomNames) {
        System.out.println();
        System.out.println("새로운 게임을 만들려면 create 방이름 을 입력하세요(방이름은 한글만 가능합니다.)");
        System.out.println("게임을 이어가려면 resume 방이름 을 입력하세요(방이름은 한글만 가능합니다.)");
        System.out.println();
        if (roomNames.size() == 0) {
            System.out.println("진행 중이던 게임이 없습니다.");
            return;
        }
        System.out.println("현재 진행 중인 게임 목록");
        System.out.println("-------------------");
        for (String roomName : roomNames) {
            System.out.println(roomName);
        }
    }
}
