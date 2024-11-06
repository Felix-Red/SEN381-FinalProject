package DataLayer;

public class FeedbackEntry {
    private String satisfactionRating;
    private String comments;
    private String customerName;

    public FeedbackEntry(String satisfactionRating, String comments, String customerName) {
        this.satisfactionRating = satisfactionRating;
        this.comments = comments;
        this.customerName = customerName;
    }

    public String getSatisfactionRating() {
        return satisfactionRating;
    }

    public String getComments() {
        return comments;
    }

    public String getCustomerName() {
        return customerName;
    }
}
