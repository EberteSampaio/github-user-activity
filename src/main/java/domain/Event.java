package domain;

import java.time.LocalDateTime;
import java.util.Map;

public record Event(
     long id,
     String type,
     Actor actor,
     Repository repo,
     Map<String, Object> payload,
     boolean isPublic,
     LocalDateTime createdAt
){
}
