package ge.tsu.gameencyclopedia.game;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findAll(Pageable pageable);

    List<Game> findByTitleContainingIgnoreCase(String title);

    List<Game> findByDeveloperContainingIgnoreCase(String developer);

    List<Game> findByGenreContainingIgnoreCase(String genre);

    // Fixed JPQL query
    @Query(value = "SELECT * FROM GAMES g WHERE " +
            "LOWER(g.TITLE) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(g.DEVELOPER) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(g.GENRE) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(g.DESCRIPTION) LIKE LOWER(CONCAT('%', :query, '%'))",
            nativeQuery = true)
    List<Game> searchByAnyField(@Param("query") String query);
}