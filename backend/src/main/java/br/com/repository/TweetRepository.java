package br.com.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.entities.Tweet;


@Repository
public interface TweetRepository extends JpaRepository <Tweet, Long>{

}
