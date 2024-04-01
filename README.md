# Kidari
Backend Test for Kidari Studio

## 개발환경
> Java 17  
> SpringBoot 3.2.4  
> Gradle  
> H2

## 실행방법
1. gradlew build
2. cd build/libs
3. java -jar api-0.0.1.SNAMPSHOT.jar

## Swagger URL
> http://localhost:8080/swagger-ui/index.html

## 데이터 설계
+ Hexagonal Architecture 설계 도입
+ 사번에 대한 별도 요구사항이 없어, 강연목록과 신청내역 2개 데이터만 사용
+ 명확한 요구사항에 대해서 단위 테스트 작성
  + 단순 데이터 저장/조회 및 클래스 유효성 검사에 대해서는 테스트 작성하지 않음

## 기타
+ Custom Error Handling 적용
+ Web/App Request에 validation으로 클래스 사전 조건 설정
+ jqwik 라이브러리를 사용하여 랜덤 테스트 케이스 적용
  + 현 시점을 기준으로 -10~10일을 더한 임의의 날짜에 대해 현 시점 노출 여부 테스트
  + 임의의 강연신청 목록에 대해 최빈값 Top 3 집계 테스트