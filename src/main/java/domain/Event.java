package domain;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private EventType type;
    private Actor actor;
    private Repository repo;
    private Payload payload;
    private Boolean isPublic;
    private LocalDateTime createdAt;
}
