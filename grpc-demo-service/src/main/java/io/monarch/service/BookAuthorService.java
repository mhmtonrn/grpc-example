package io.monarch.service;

import io.grpc.stub.StreamObserver;
import io.monarch.Author;
import io.monarch.Book;
import io.monarch.BookAuthorServiceGrpc;
import io.monarch.repository.MockData;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BookAuthorService extends BookAuthorServiceGrpc.BookAuthorServiceImplBase {

  @Override
  public void getAuthor(Author request, StreamObserver<Author> responseObserver) {
    MockData.getAuthorsFromTempDb()
        .stream()
        .filter(author -> author.getAuthorId() == request.getAuthorId())
        .findFirst()
        .ifPresent(responseObserver::onNext);
    responseObserver.onCompleted();
  }

  @Override
  public void getBooksByAuthor(Author request, StreamObserver<Book> responseObserver) {
    MockData.getBooksFromTempDb()
        .stream()
        .filter(book -> book.getAuthorId() == request.getAuthorId())
        .forEach(responseObserver::onNext);
    responseObserver.onCompleted();
  }
}
