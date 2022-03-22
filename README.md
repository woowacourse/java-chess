# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능 요구 사항
### 입력
- 게임 시작 기능 (start를 입력 받는다.)
- 게임 종료 기능 (end를 입력 받는다.)
  - start,end 이외의 입력값에 대한 검증 해주는 기능 추가
  - 이외의 문자열을 입력한 경우 예외 발생

### 도메인
- 체스말을 생성하는 기능
  - 추상클래스를 선언하여, 말들 마다 움직임 전략을 다르게 가져간다.
- 체스판을 초기화하는 기능
  - 위치를 표현해주는 클래스
  - 위치와 말은 Map으로 관리

