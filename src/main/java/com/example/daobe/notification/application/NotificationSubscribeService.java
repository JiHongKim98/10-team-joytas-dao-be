package com.example.daobe.notification.application;

import com.example.daobe.notification.domain.NotificationEmitter;
import com.example.daobe.notification.domain.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
public class NotificationSubscribeService {

    private static final String DETERMINE = "_";
    private static final Long DEFAULT_TIMEOUT = 44 * 1000L;  // 기본 지속 시간 44초

    private final EmitterRepository emitterRepository;

    public SseEmitter subscribeNotification(Long userId) {
        String emitterId = userId + DETERMINE + System.currentTimeMillis();
        NotificationEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.sendToClient(DummyPayload.of());
        return emitter.getSingleEmitter();
    }

    // Nested
    public record DummyPayload(
            String message
    ) {

        public static DummyPayload of() {
            return new DummyPayload("connect success");
        }
    }
}
