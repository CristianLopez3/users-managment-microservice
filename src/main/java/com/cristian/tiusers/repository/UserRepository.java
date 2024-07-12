package com.cristian.tiusers.repository;

import com.cristian.tiusers.dto.UserProjectionDto;
import com.cristian.tiusers.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            """
            SELECT u.id, u.name,  u.lastname, u.telephone, u.state, u.department.name FROM User u
            JOIN u.company c
            WHERE c.name = :company
            """
    )
    Page<UserProjectionDto> findUsersByCompany(@Param("company") String company, Pageable pageable);



    @Query(
            """
            SELECT u FROM User u
            JOIN u.company c
            WHERE c.id = :companyId
            """
    )
    Page<User> findUsersByCompanyId(@Param("companyId") Long companyId, Pageable pageable);

}
