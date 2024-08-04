import java.util.Scanner;

public class InputView {
	
	Scanner sc = new Scanner(System.in);
	
	public void startMessage() {
		System.out.println("> 체스 게임을 시작합니다.");
		System.out.println("> 게임 시작 : start");	
		System.out.println("> 게임 종료 : end");	
		System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");	
	}
	
	public String start() {
		String start = sc.nextLine();
		
		if (start.equals("start")) {
            return "start";
        }
        
        if (start.equals("end")) {
            return "end";
        }
        
        return start;
	}


}
