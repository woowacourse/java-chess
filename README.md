# 우아한 테크 코스 레벨1

## 7주차 미션 - 체스 게임

###구현 기능 목록
1. [입력] 이동할 말의 좌표를 입력
	* [예외처리] 가고 싶은 위치에 갈 수 있는지 검증
	* [예외처리] 해당 말이 목표 위치로 갈수 있는지 검증
	* [예외처리] 좌표를 벗어난 값을 입력했는지 검증
	* [예외처리] 가고 싶은 경로 중간에 말이 있는지 검증
	* [예외처리] Pawn Piece 검증
		* 폰이 첫 시작인지 검증
		* 폰이 대각선으로만 공격하는지 검증

2. [실행] 말 별로 이동 방향 구현
	* King -> up, down, left, right, up-right, up-left, down-right, down-left
	* Queen -> up, down, left, right, up-right, up-left, down-right, down-left
	* Rook -> up, down, left, right
	* Bishiop -> up-right, up-left, down-right, down-left
	* Knight -> double-up-right, double-up-left, double-down-right, double-down-left, double-right-up, double-right-down, double-left-up, double-left-down
	* Pawn -> up, double-up, up-right, up-left
	
3. [실행] 말 별로 이동 경로 구현
	* King -> 1칸
	* Queen -> 무한대
	* Rook -> 무한대
	* Bishop -> 무한대
	* Knight -> 1칸
	* Pawn -> 1칸 or 2칸

4. [출력] 말들 별로 위치 좌표 출력
	* 이동한 말을 보드에 삽입 및 해당 좌표 삭제
	
5. [결과] 팀별로 말들의 존재 유무를 통한 점수 계산
	* King -> GameOver
	* Queen -> 9점
	* Rook -> 5점
	* Bishop -> 3점
	* Knight -> 2.5점
	* Pawn -> 1점 (같은 열에 있는 경우 그 열에 있는 폰은 전부 0.5점)

6. [출력] 팀별 계산된 점수 출력 및 이긴팀 출력