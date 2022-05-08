package com.kakjzi.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
/**
 * <Entity class, PK type>
 * Entity 와 EntityRepository 함께 위치해야함.
 */
