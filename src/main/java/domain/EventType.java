package domain;


import java.util.Arrays;

public enum EventType {
    COMMIT_COMMENT_EVENT("CommitCommentEvent"),
    CREATE_EVENT("CreateEvent"),
    DELETE_EVET("DeleteEvent"),
    DISCUSSION_EVENT("DiscussionEvent"),
    FORK_EVENT("ForkEvent"),
    GOLLUM_EVENT("GollumEvent"),
    ISSUE_COMMENT_EVENT("IssueCommentEvent"),
    ISSUES_EVENT("IssuesEvent"),
    MEMBER_EVENT("MemberEvent"),
    PUBLIC_EVENT("PublicEvent"),
    PULL_REQUEST_EVENT("PullRequestEvent"),
    PULL_REQUEST_REVIEW_EVENT("PullRequestReviewEvent"),
    PULL_REQUEST_REVIEW_COMMENT_EVENT("PullRequestReviewCommentEvent"),
    PUSH_EVENT("PushEvent"),
    RELEASE_EVENT("ReleaseEvent"),
    WATCH_EVENT("WatchEvent"),
    UNKNOWN_EVENT("UnknownEvent");


    private String name;

    EventType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EventType fromName(String name) {
        return Arrays.stream(EventType.values())
                .filter(v -> v.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(EventType.UNKNOWN_EVENT);
    }
}
