package utils;

import domain.Event;
import domain.EventType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static domain.EventType.*;

public class GitHubActivityFormatter {

    public String format(Event event){
        EventType eventType = fromName(event.type());

        return switch (eventType){
            case PUSH_EVENT -> this.formatPushEvent(event);
            case ISSUE_EVENT -> this.formatIssueEvent(event);
            case WATCH_EVENT -> this.formatWatchEvent(event);
            case FORK_EVENT -> this.formatForkEvent(event);
            case DELETE_EVET -> this.formatDeleteEvent(event);
            case CREATE_EVENT -> this.formatCreateEvent(event);
            case GOLLUM_EVENT -> this.formatGollumEvent(event);
            case MEMBER_EVENT -> this.formatMemberEvent(event);
            case PUBLIC_EVENT -> this.formatPublicEvent(event);
            case RELEASE_EVENT -> this.formatReleaseEvent(event);
            case DISCUSSION_EVENT -> this.formatDiscussionEvent(event);
            case PULL_REQUEST_EVENT -> this.formatPullRequestEvent(event);
            case ISSUE_COMMENT_EVENT -> this.formatIssueCommentEvent(event);
            case COMMIT_COMMENT_EVENT -> this.formatCommitCommentEvent(event);
            case PULL_REQUEST_REVIEW_EVENT -> this.formatPullRequestReviewEvent(event);
            case PULL_REQUEST_REVIEW_COMMENT_EVENT ->this.formatPullRequestReviewCommentEvent(event);
            default -> throw new IllegalStateException("Unexpected value: " + eventType);
        };
    }

    private String formatIssueEvent(Event event) {
        String action = event.payload().get("action").toString();
        return String.format("%s a issue in %s", this.uppercaseFirstChar(action), event.repo().name());
    }

    private String formatPushEvent(Event event) {

        return String.format("Pushed 1 commit to %s", event.repo().name());
    }

    private String formatCommitCommentEvent(Event event){
        return String.format("Commented on a commit in the %s repository", event.repo().name());
    }

    private String formatCreateEvent(Event event){
        String ref = event.payload().get("ref").toString();
        String refType = event.payload().get("ref_type").toString();
        return String.format("created a %s named %s in the %s repository", refType, ref, event.repo().name());
    }

    private String formatDeleteEvent(Event event){
        String ref = event.payload().get("ref").toString();
        String refType = event.payload().get("ref_type").toString();
        return String.format("Deleted a %s named %s in the %s repository", refType, ref, event.repo().name());
    }
    private String formatDiscussionEvent(Event event){
        String action = event.payload().get("action").toString();
        return String.format("%s a discussion in the %s repository",this.uppercaseFirstChar(action) , event.repo().name());
    }

    private String formatForkEvent(Event event){
        return "Forked a repository";
    }

    private String formatGollumEvent(Event event){
        return "Edited, create or updated a Wiki repository";
    }

    private String formatIssueCommentEvent(Event event){
        String action = event.payload().get("action").toString();
        return String.format("%s a issue in %s repository",this.uppercaseFirstChar(action) , event.repo().name());
    }

    private String formatMemberEvent(Event event){
        String action = event.payload().get("action").toString();
        return String.format("%s a a new member in %s repository",this.uppercaseFirstChar(action), event.repo().name());
    }

    private String formatPublicEvent(Event event){
        return String.format("The private repository %s was made public", event.repo().name());
    }

    private String formatPullRequestEvent(Event event){
        String action = event.payload().get("action").toString();
        return String.format("A pull request was %s in %s repository",action, event.repo().name());
    }

    private String formatPullRequestReviewEvent(Event event){
        String action = event.payload().get("action").toString();
        return String.format("A revision was %s in repository %s",action, event.repo().name());
    }

    private String formatPullRequestReviewCommentEvent(Event event){
        return String.format("A comment was made on a specific line of a pull request's diff. Repository %s", event.repo().name());
    }

    private String formatReleaseEvent(Event event){
        String action = event.payload().get("action").toString();
        return String.format("The release was %s to the %s repository." ,action ,event.repo().name());
    }

    private String formatWatchEvent(Event event){
        return String.format("The repository %s has been favorited.", event.repo().name());
    }

    private String uppercaseFirstChar(String action) {
        return action.substring(0, 1).toUpperCase() + action.substring(1).toLowerCase();
    }
}
