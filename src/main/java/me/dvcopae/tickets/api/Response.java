package me.dvcopae.tickets.api;

public interface Response {

  int getStatusCode();

  String getBody();

  String getContentType();
}
