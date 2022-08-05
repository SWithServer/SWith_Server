package com.example.demo.src.repository;

import com.example.demo.src.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {

    @Query("select a from Announcement a where a.groupInfo.groupIdx in (:groupIdx) order by a.createdAt DESC")
    List<Announcement> findByGroupInfo_GroupIdxOrderByCreatedAtDesc(Long groupIdx);

    @Query("select a from Announcement a where a.groupInfo.groupIdx = :groupIdx and a.status = 0")
    List<Announcement> findByGroupIdx(Long groupIdx);
}