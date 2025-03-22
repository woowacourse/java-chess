package view;

import chess.Color;
import chess.Position;
import java.util.Scanner;
import util.PositionConvertor;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public Position getStartPosition(Color turn){
        System.out.printf("%s의 차례입니다. 이동하고 싶은 말의 위치를 선택해주세요. 예) A4\n",turn.getName());
        return PositionConvertor.convertInputToPosition(scanner.nextLine().trim());
    }

    public Position getEndPosition(){
        System.out.println("이동하려는 위치를 입력해주세요.");
        return PositionConvertor.convertInputToPosition(scanner.nextLine().trim());
    }
}
