class Request {
    private final int from;
    private final int to;
    public Request(int from, int to) {
        this.from = from;
        this.to= to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}