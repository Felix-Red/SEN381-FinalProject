package DataLayer;

public class Request {
    private int requestId;
    private int clientId;
    private String requestDetails;
    private int technicianId;

    public Request(int requestId, int clientId, String requestDetails, int technicianId) {
        this.requestId = requestId;
        this.clientId = clientId;
        this.requestDetails = requestDetails;
        this.technicianId = technicianId;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getClientId() {
        return clientId;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public int getTechnicianId() {
        return technicianId;
    }
}
