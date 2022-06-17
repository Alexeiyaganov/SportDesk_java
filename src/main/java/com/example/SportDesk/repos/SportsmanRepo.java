package com.example.SportDesk.repos;

import com.example.SportDesk.domain.Sportsman;
import com.example.SportDesk.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface SportsmanRepo extends CrudRepository<Sportsman, Long> {

    @Query("select  new com.example.SportDesk.domain.Sportsman("+
            " s.name, " +
            " s.lastname, " +
            " s.author"+
            ") "+
            "from Sportsman s "+
            "where s.author = :author " +
            "group by s")
    Page<Sportsman> findByUser(Pageable pageable, @Param("author") User author);

}
