package com.spiny.spiny_demo.Repository;



import com.spiny.spiny_demo.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    List<Area> findByPincode(int pincode);
}
