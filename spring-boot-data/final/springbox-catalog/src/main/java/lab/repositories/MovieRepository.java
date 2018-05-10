package lab.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import lab.domain.Movie;
import java.util.List;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Long> {

	// eg. http://localhost:9000/movies/search/findByTitle?title=Toy%20Story%20(1995)

	// see here for more methods
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

	List<Movie> findByTitle( @Param("title") String title );

	List<Movie> findByTitleLike( @Param("title") String title );

	List<Movie> findByTitleContaining( @Param("title") String title );

	List<Movie> findByTitleStartingWith( @Param("title") String title );

	@Query("select m from Movie m where m.title like '%199%'")
	List<Movie> findNinetiesMovies();

	@Query("select m from Movie m where m.title like '%198%'")
	List<Movie> findEightiesMovies();


}