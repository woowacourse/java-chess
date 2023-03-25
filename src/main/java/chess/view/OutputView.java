package chess.view;

import chess.view.resposne.PieceResponse;
import chess.view.resposne.StatusResponse;
import java.util.List;

public class OutputView {

    public void printInitialMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 로그인 : login 유저id - 예. login user1");
        System.out.println("> 게임 목록 : games");
        System.out.println("> 진행중인 게임 선택 : join 게임번호 - 예. join 1");
        System.out.println("> 게임 생성 : create");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(List<List<PieceResponse>> piecePosition) {
        for (int i = piecePosition.size() - 1; i >= 0; i--) {
            printRank(piecePosition.get(i));
        }
        System.out.println();
    }

    public void printStatus(StatusResponse statusResponse) {
        System.out.println("현재 점수");
        System.out.println("흰색 : " + statusResponse.getWhiteScore());
        System.out.println("검정색 : " + statusResponse.getBlackScore());
    }

    private void printRank(List<PieceResponse> pieceResponses) {
        pieceResponses.forEach(this::printPiece);
        System.out.println();
    }

    private void printPiece(PieceResponse pieceResponse) {
        System.out.print(pieceResponse.getPiece());
    }

    public void printError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void printLoginSuccess() {
        System.out.println("로그인에 성공했습니다");
        System.out.println("현재 유저의 게임들을 보려면 games 를 입력해주세요");

    }

    public void printGames(List<Integer> games) {
        System.out.println("현재 유저의 게임들");
        games.forEach(System.out::println);
        System.out.println("게임을 시작하려면 join 게임번호 를 입력해주세요");
        System.out.println("게임을 생성하려면 create 를 입력해주세요");
    }

    public void printCreateGameSuccess() {
        System.out.println("게임을 생성했습니다");
        System.out.println("게임을 시작하려면 start 를 입력해주세요");
    }

    public void printJoinGameSuccess(String boardStatus) {
        System.out.println("게임에 참여했습니다");
        System.out.println("현재 게임 상태");
        System.out.println(boardStatus);
    }
}
