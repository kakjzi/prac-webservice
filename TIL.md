
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
public interface PostsRepository extends JpaRepository<Posts, Long>
``` 
* <Entity class, PK type>
* Entity 와 EntityRepository 함께 위치해야함.
---
MockMVC ?

웹 어플리케이션을 애플리케이션 서버에 배포하지 않고 테스트용 MVC환경을 만들어 요청 및 전송, 응답기능을 제공해주는 유틸리티 클래스다.

---
Junit vs assertj 참고영상
* https://www.youtube.com/watch?v=zLx_fI24UXM&t=408s