package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record Event(
     long id,
     String type,
     Actor actor,
     Repository repo,
     Map<String, Object> payload,
     boolean isPublic,
     @JsonProperty("created_at")
     String createdAt
){
}
