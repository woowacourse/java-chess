# 학습로그 1-1

# [Java] Functional Interface - 3

## 내용
- Consumer 타입은 인자로 받아 리턴하지 않고 소진한다.
- Command 명령어를 Consumer<인자타입> 를 받아 함수 표현식을 accept()로 실행가능하다. if 분기문을 줄일 수 있다.
- Function<인자타입, 반환타입> 값으로 표현식을 받아 apply()로 실행가능하다.

# [Java] StreamAPI - 3

## 내용
- reduce(BinaryOperator<T> accumulator) => Optional<T>
- reduce(T identity, BinaryOperator<T> accumulator) => T 
- reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner) => <U> U
- 리듀스는 스트림의 최종연산이다. 요소를 하나씩 줄여가며 누적 연산을 한다. 
- 2번째 리듀스를 사용하여 연산해주었다. <초기값, 연산내용> 을 받아서 초기값을 기준으로 누적연산이 가능하다.
```java
class BlackSet {
    private Score subtractWhenOnSameLine(Map<Character, Integer> pawnCount){
        return pawnCount.values().stream()
        .filter(number->number>1)
        .map(number->new Score(MINUS_HALF_POINT*number))
        .reduce(Score.ZERO,Score::add);
    }
}
```

## 링크
[javadocs StreamAPI](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)


# 학습로그 1-2

# [Java] Function Interface - 2

## 내용
- UnaryOperator<T> 는 T타입을 받아서 T타입을 리턴한다. Funtion Interface와 비슷하지만 인자로 들어오는 타입과 반환하는 타입이 같다는 특징이 있다.

# [OOP] Setter - 3

## 내용
- setXXX라는 set 명칭말고 메서드에 의도를 들어내어 이름을 지으면 setter의 단점을 보완할 수 있다.
- setter 자체는 의도를 가지기 어렵다.

# [Java] UnsupportedOperationException - 2

## 내용
- 지원하지 않는 연산 예외처리
- 런타임 예외
- 대표적으로 Collections.unmodifiableList 에서 add할 경우

## 링크
[javadocs UnsupportedOperationException](https://docs.oracle.com/javase/8/docs/api/java/lang/UnsupportedOperationException.html)


# [Java] Enum - 3

## 내용
- Enum에는 기본적으로 static <T extends Enum<T>> valueOf(String value) 가 정의되어 있다. 따라서 valueOf(String s)의 정적팩토리 메서드가 만들어지지 않는다.
- 이외에도 clone(), compareTo(E o), equals, toString, names, finalize 등이 기본적으로 정의되어 있다.

## 링크 
[javadocs Enum](https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html)