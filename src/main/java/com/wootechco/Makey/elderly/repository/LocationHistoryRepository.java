package com.wootechco.Makey.elderly.repository;

import com.wootechco.Makey.elderly.domain.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long> {
    List<LocationHistory> findByPersonId(Long personId);
}
