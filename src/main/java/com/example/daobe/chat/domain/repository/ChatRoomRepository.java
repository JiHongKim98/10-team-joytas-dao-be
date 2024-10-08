package com.example.daobe.chat.domain.repository;

import com.example.daobe.chat.domain.ChatRoom;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("""
            SELECT cr FROM ChatRoom cr
            WHERE cr.objet.id = :objetId
            """)
    Optional<ChatRoom> findChatRoomTokenByObjetId(@Param("objetId") Long objetId);
}
