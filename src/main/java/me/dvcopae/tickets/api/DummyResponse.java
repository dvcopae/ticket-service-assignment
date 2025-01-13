package me.dvcopae.tickets.api;

public class DummyResponse implements Response {

  private final int statusCode;
  private final String body;
  private final String contentType;

  public DummyResponse(int statusCode, String body, String contentType) {
    this.statusCode = statusCode;
    this.body = body;
    this.contentType = contentType;
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  @Override
  public String getBody() {
    return body;
  }

  @Override
  public String getContentType() {
    return contentType;
  }

  @Override
  public String toString() {
    return "DummyResponse{"
        + "statusCode="
        + statusCode
        + ", body='"
        + body
        + '\''
        + ", contentType='"
        + contentType
        + '\''
        + '}';
  }
}
