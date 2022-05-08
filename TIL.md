
* 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 같다.
* 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없고
* 빌더는 어느 필드에 어떤 값을 채워야할지 명확하게 인지할 수 있음.
---

### @RequiredArgsConstructor 
* 선언된 'final' 필드 생성자를 생성!

### @AllArgsConstructor
* 클래스에 존재하는 모든 필드에 대한 생성자를 자동 생성

### @NoArgsConstructor
* 기본 생성자를 생성합니다.
---


* update 기능에서 DB에 쿼리를 날리는 부분이 없습니다.
* -> JPA 영속성 컨텍스트 때문 (영속성 컨텍스트란? 엔티티를 영구 저장하는 환경을 말함)
* JPA 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 DB에서 데이터를 가져오면 이 데이터는 '영속성 컨텍스트'가 유지된 상태입니다.
* 트랜잭션이 끝나는 시점에서 해당 테이블에 변경분을 반영합니다.
* -> Entity 값만 변경하면 별도로 update 쿼리를 날릴 필요없이 '더티체킹'을 해줌.
---
```java
public interface PostsRepository extends JpaRepository<Posts, Long> {}
``` 
* <Entity class, PK type>
* Entity 와 EntityRepository 함께 위치해야함.
---
### MockMVC ?

웹 어플리케이션을 애플리케이션 서버에 배포하지 않고 테스트용 MVC환경을 만들어 요청 및 전송, 응답기능을 제공해주는 유틸리티 클래스다.

---
### Junit vs assertj 참고영상
* https://www.youtube.com/watch?v=zLx_fI24UXM&t=408s

---
### LocalDate 

Java 8이 나오기 전까지 사용한 Date, Calendar 클래스의 문제점
* 불변 객체가 아님
  * 멀티스레드 환경에서 문제 발생
* Calendar의 월 값 설계가 잘못됨.
  * Calendar.OCTOBER 숫자 값 '9'

_Hibernate 5.2.10 이상 사용권고 (LocalDate가 DB 매핑되지 않는 이슈)_

---
### @MappedSuperclass
* JPA Entity 클래스들이 [BaseTimeEntity](./src/main/java/com/kakjzi/book/springboot/domain/BaseTimeEntity.java)을 상속할 경우 필드들도 컬럼으로 인식하도로 합니다.

### @EntityListeners(AuditingEntityListener.class)
* BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.

### @CreatedDate & @LastModifiedDate
* Entity가 생성 / 변경되어 저장될 때 시간이 자동 저장됩니다.